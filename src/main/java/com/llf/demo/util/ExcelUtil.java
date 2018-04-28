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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: Oliver.li
 * @Description: Excel工具类
 * @date: 2018/4/28 11:23
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * export data to excel file
     * clazz must be entity class, use reflection to get fields
     * @param data
     * @param fileName
     * @param titles
     * @param response
     * @param clazz
     * @param <T>
     * @throws IOException
     */
    public static<T> void export(List<T> data, String fileName, String[] titles, HttpServletResponse response, Class clazz) throws IOException {
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
     * export data to excel file
     * @param data
     * @param fileName
     * @param titles
     * @param fields
     * @param response
     * @param <T>
     * @throws IOException
     */
    public static<T> void export(List<T> data, String fileName, String[] titles, String[] fields, HttpServletResponse response) throws IOException {
        Assert.state(data != null, "data cannot be empty!");

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet 1");
        sheet.setDefaultColumnWidth(20);

        //set titles
        Row row = sheet.createRow(0);
        for (int i = 0, length = titles.length; i < length; i++) {
            row.createCell(i).setCellValue(titles[i]);
        }

        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

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
                        cell.setCellValue((Date) value);
                        cell.setCellStyle(cellStyle);
                    } else {
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            } else {
                for (int i = 0, length = fields.length; i < length; i++) {
                    value = getFiledValue(fields[i], t);
                    cell = row.createCell(i);
                    if (value instanceof Date){
                        cell.setCellValue((Date) value);
                        cell.setCellStyle(cellStyle);
                    } else {
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            }
        }

        flush(fileName, wb, response);
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
     * get field's value
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
     * write data to response and flush out
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
     * set response header
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
