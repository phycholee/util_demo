package com.llf.demo.log.domain;

import java.util.Map;

public class GenericBaseEvent<T> {

    //频道基本信息封装
    /**
     * 频道 appId
     */
    public long appId;

    /**
     * 顶级频道ID
     */
    public long topSid;

    /**
     * 子频道ID
     */
    public long subSid;

    public long uid;

    public long serverId;

    public long clientType;

    /**
     * @deprecated ipv6不能获取到，应该使用GenericBaseEventUtil.getIp
     */
    @Deprecated
    public long userIp;

    public long serviceProxyIp;

    public Map<String, String> mobileExtend;

    public Map<String, String> mobileExtendInfo;

    public Map<String, String> mobileExtendInfo2;

    /**
     * 客户端事件时间
     */
    private long time;

    /**
     * 服务端收到时间
     */
    private long serverTime;

    /**
     * 事件类型
     */
    private String type;

    /**
     * 事件唯一id
     */
    private String busiId;

    /**
     * 业务数据
     */
    private T data;

    public GenericBaseEvent(){}

    public long getAppId() {
        return this.appId;
    }

    public void setAppId(final long appId) {
        this.appId = appId;
    }

    public long getTopSid() {
        return this.topSid;
    }

    public void setTopSid(final long topSid) {
        this.topSid = topSid;
    }

    public long getSubSid() {
        return this.subSid;
    }

    public void setSubSid(final long subSid) {
        this.subSid = subSid;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(final long uid) {
        this.uid = uid;
    }

    public long getServerId() {
        return this.serverId;
    }

    public void setServerId(final long serverId) {
        this.serverId = serverId;
    }

    public long getClientType() {
        return this.clientType;
    }

    public void setClientType(final long clientType) {
        this.clientType = clientType;
    }

    /**
     * @deprecated ipv6不能获取到，应该使用GenericBaseEventUtil.getIp
     */
    @Deprecated
    public long getUserIp() {
        return this.userIp;
    }

    public void setUserIp(final long userIp) {
        this.userIp = userIp;
    }

    public long getServiceProxyIp() {
        return this.serviceProxyIp;
    }

    public void setServiceProxyIp(final long serviceProxyIp) {
        this.serviceProxyIp = serviceProxyIp;
    }

    public Map<String, String> getMobileExtend() {
        return this.mobileExtend;
    }

    public void setMobileExtend(final Map<String, String> mobileExtend) {
        this.mobileExtend = mobileExtend;
    }

    public Map<String, String> getMobileExtendInfo() {
        return this.mobileExtendInfo;
    }

    public void setMobileExtendInfo(final Map<String, String> mobileExtendInfo) {
        this.mobileExtendInfo = mobileExtendInfo;
    }

    public Map<String, String> getMobileExtendInfo2() {
        return this.mobileExtendInfo2;
    }

    public void setMobileExtendInfo2(final Map<String, String> mobileExtendInfo2) {
        this.mobileExtendInfo2 = mobileExtendInfo2;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    public long getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(final long serverTime) {
        this.serverTime = serverTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getBusiId() {
        return this.busiId;
    }

    public void setBusiId(final String busiId) {
        this.busiId = busiId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
