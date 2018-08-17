package com.llf.demo.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Oliver.li
 * @Description: ip工具类
 * @date: 2018/8/17 17:52
 */
public class IpUtil {

    /**
     * 根据HttpServletRequest获取ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = null;

        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("Proxy-Client-Ip");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("WL-Proxy-Client-Ip");
            }

            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getRemoteAddr();
            }

            //多个代理的情况，第一个为真实IP，通过","分割
            if (ipAddress != null && ipAddress.length() > 15){
                int index = ipAddress.indexOf(",");
                if (index > 0){
                    ipAddress = ipAddress.substring(0, index);
                }
            }

        } catch (Exception e){
            ipAddress = "";
        }

        return ipAddress;
    }

}
