package com.llf.demo.common;

/**
 * @author: Oliver.li
 * @Description: 返回json数据类
 * @date: 2018/5/21 15:25
 */
public class JsonData {

    public final static int SUCCESS = 0;

    public final static int FAIL = -1;

    public final static int ERROR = -2;

    public final static String SUCCESS_MSG = "请求成功";

    public final static String FAIL_MSG = "请求失败";

    public final static String ERROR_MSG = "系统错误";

    private int code;

    private String msg;

    private Object data;

    public JsonData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonData success(){
        return success(null);
    }

    public static JsonData success(Object data){
        return success(SUCCESS_MSG, data);
    }

    public static JsonData success(String msg, Object data){
        return new JsonData(SUCCESS, msg, data);
    }

    public static JsonData fail(){
        return fail(null);
    }

    public static JsonData fail(String msg){
        return fail(msg, null);
    }

    public static JsonData fail(String msg, Object data){
        return new JsonData(FAIL, msg, data);
    }

    public static JsonData error(){
        return error(null);
    }

    public static JsonData error(String msg){
        return error(msg, null);
    }

    public static JsonData error(String msg, Object data){
        return new JsonData(ERROR, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
