package com.llf.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Oliver.li
 * @Description: Excel工具类
 *
 * 提供Excel导出方法，方便导出数据使用
 * 有三种方式
 *
 * 1.封装数据为普通实体类，需要传入导出列名数组，使用反射获取属性数组
 *
 * 2.封装数据为普通实体类或Map类，需要传入导出列名数组和属性数组。在使用Map类封装数据的情况下，效率最高
 *
 * 3.封装数据为普通实体类，实体类属性需要使用@ExcelColumn配置相关信息，可定制程度最高，后期可添加更多的配置项。效率最低。
 *
 * @date: 2018/4/28 11:23
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 不需要fields数组
     * 使用反射去获取实体类属性名称
     * @param data 需要导出的数据集合
     * @param fileName 导出文件名称
     * @param titles 列名数组
     * @param response HttpServletResponse
     * @param clazz 实体类Class
     * @param <T>
     * @throws IOException
     */
    public static<T> void export(List<T> data, String fileName, String[] titles, HttpServletResponse response, Class<T> clazz) throws IOException {
        Assert.state(data != null, "data cannot be empty!");
        Assert.state(fileName != null && !"".equals(fileName), "fileName cannot be empty!");
        Assert.state(titles != null && titles.length > 0, "titles cannot be empty!");
        Assert.state(clazz != null && clazz != Map.class, "clazz cannot be empty and cannot be Map.class!");

        Field[] declaredFields = clazz.getDeclaredFields();
        int length = declaredFields.length;
        String[] fields = new String[length];
        for (int i = 0; i < length; i++){
            fields[i] = declaredFields[i].getName();
        }

        logger.info("use reflection to get the fields: {}", Arrays.toString(fields));

        export(data, fileName, titles, fields, response);
    }

    /**
     * 导出excel数据
     * @param data 需要导出的数据集合
     * @param fileName 导出文件名称
     * @param titles 列名数组
     * @param fields 属性数组
     * @param response HttpServletResponse
     * @param <T>
     * @throws IOException
     */
    public static<T> void export(List<T> data, String fileName, String[] titles, String[] fields, HttpServletResponse response) throws IOException {
        Assert.state(data != null, "data cannot be empty!");
        Assert.state(fileName != null && !"".equals(fileName), "fileName cannot be empty!");
        Assert.state(titles != null && titles.length > 0, "titles cannot be empty!");
        Assert.state(fields != null && fields.length > 0, "fields cannot be empty!");

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet 1");
        sheet.setDefaultColumnWidth(20);

        //set titles
        Row row = sheet.createRow(0);
        for (int i = 0, length = titles.length; i < length; i++) {
            row.createCell(i).setCellValue(titles[i]);
        }

//        CellStyle cellStyle = wb.createCellStyle();
//        CreationHelper createHelper = wb.getCreationHelper();
//        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //write data
        logger.info("writing data to workbook");

        int rowNum = 1;
        Map<String, Object> map;
        Object value;
        Cell cell;
        for (T t : data){
            row = sheet.createRow(rowNum++);
            if (t instanceof Map){
                //noinspection unchecked
                map = (Map<String, Object>) t;
                for (int i = 0, length = fields.length; i < length; i++) {
                    value = map.get(fields[i]);
                    cell = row.createCell(i);
                    if (value instanceof Date){
                        cell.setCellValue(format.format(value));
                    } else {
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            } else {
                for (int i = 0, length = fields.length; i < length; i++) {
                    value = getFiledValue(fields[i], t);
                    cell = row.createCell(i);
                    if (value instanceof Date){
                        cell.setCellValue(format.format(value));
                    } else {
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            }
        }

        flush(fileName, wb, response);
    }

    /**
     * 实体类需要导出的属性需要使用@ExcelColumn注解去指定 name(导出列名)、width(宽度)、dateFormat(时间格式)
     * 使用反射去获取@ExcelColumn指定的配置
     * 没有使用@ExcelColumn的属性会被忽略
     * @param data 需要导出的数据集合
     * @param fileName 导出文件名称
     * @param response HttpServletResponse
     * @param clazz 实体类Class
     * @param <T>
     * @throws IOException
     */
    public static<T> void export(List<T> data, String fileName, HttpServletResponse response, Class<T> clazz) throws IOException {
        Assert.state(data != null, "data cannot be empty!");
        Assert.state(fileName != null && !"".equals(fileName), "fileName cannot be empty!");
        Assert.state(clazz != null && clazz != Map.class, "clazz cannot be empty and cannot be Map.class!");

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet 1");

        Field[] declaredFields = clazz.getDeclaredFields();
        List<String> fields = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        List<String> dateFormats = new ArrayList<>();
        List<Integer> columnWidths = new ArrayList<>();

        for (Field field : declaredFields) {
            ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
            if (excelColumn != null) {
                fields.add(field.getName());
                titles.add(excelColumn.name());
                dateFormats.add(excelColumn.dateFormat());
                columnWidths.add(excelColumn.width());
            }
        }

        //set titles and column width
        Row row = sheet.createRow(0);
        for (int i = 0, length = titles.size(); i < length; i++) {
            row.createCell(i).setCellValue(titles.get(i));
            sheet.setColumnWidth(i, columnWidths.get(i) * 256);
        }

        //write data
        logger.info("writing data to workbook");

        SimpleDateFormat format;
        Map<String, SimpleDateFormat> formatCache = new HashMap<>();
        String pattern;

        int rowNum = 1;
        Object value;
        Cell cell;
        for (T t : data) {
            row = sheet.createRow(rowNum++);
            for (int j = 0, length = fields.size(); j < length; j++) {
                value = getFiledValue(fields.get(j), t);
                cell = row.createCell(j);
                if (value instanceof Date) {
                    pattern = dateFormats.get(j);
                    format = formatCache.get(pattern);
                    if (format == null ) {
                        format = new SimpleDateFormat(pattern);
                        formatCache.put(pattern, format);
                    }
                    cell.setCellValue(format.format(value));
                } else {
                    cell.setCellValue(value == null ? "" : value.toString());
                }
            }
        }

        flush(fileName, wb, response);
    }

    /**
     * 读取excel输入流，转成map对象的集合
     * @param is excel输入流
     * @param fields 属性数组，按照标题顺序作为map的key
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> read(InputStream is, String[] fields) throws Exception {

        List<Map<String, Object>> list = new ArrayList<>();

        Workbook wb = new HSSFWorkbook(is);

        Sheet sheet = wb.getSheetAt(0);

        int rowSize = sheet.getPhysicalNumberOfRows();

        Row row;
        int cellSize;
        Map<String, Object> map;
        for (int i = 1; i < rowSize; i++){
            row = sheet.getRow(i);

            cellSize = row.getPhysicalNumberOfCells();
            map = new HashMap<>();
            for (int j = 0; j < cellSize; j++){
                map.put(fields[j], getCellValue(row.getCell(j)));
            }

            list.add(map);
        }

        return list;
    }

    /**
     * 根据不同cell类型获取值
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell){

        CellType cellTypeEnum = cell.getCellTypeEnum();

        Object value;

        switch (cellTypeEnum){
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;

            case FORMULA:
                value = cell.getStringCellValue();
                break;

            case NUMERIC:
                value = cell.getDateCellValue();
                break;

            default:
                value = null;
        }

        return value;
    }

    /**
     * create cell and set value and style
     * @param wb
     * @param row
     * @param column
     * @param halign
     * @param valign
     */
    private static void createCell(Workbook wb, Row row, int column, Object obj, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue(obj.toString());

        if (halign != null || valign != null) {
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(halign);
            cellStyle.setVerticalAlignment(valign);
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 根据属性名获取属性值
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getFiledValue(String fieldName, Object object){
        Class<?> clazz = object.getClass();

        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            Method method = pd.getReadMethod();

            return method.invoke(object);
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 写出文件
     * @param fileName
     * @param wb
     * @param response
     * @throws IOException
     */
    private static void flush(String fileName, Workbook wb, HttpServletResponse response) throws IOException {
        logger.info("flush out.");

        setResponseHeader(fileName, response);
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
    }

    /**
     * 设置导出excel的响应头
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    private static void setResponseHeader(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/octet-stream");
        String downloadName = new String(fileName.getBytes("utf-8"), "iso8859-1");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xls", downloadName));
    }
}
