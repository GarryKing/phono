package name.elegant.phono.client.dataobject.admin;

import java.util.Date;

/**
 * Author: Garry King
 * Date: 12-12-13 ÉÏÎç12:42
 * E-mail:flyhzq@sina.com
 */
public class AccessLogDO {

    private long accessId;

    private String ip;

    private Date accessTime;

    private int requestType;

    private String requestUrl;

    private int statusCode;

    private long returnSize;

    private String accessUtil;

    private int logFrom;

    private String reserveString_1;

    private String reserveString_2;

    private String reserveString_3;

    private int reserveInt_1;

    private Date createTime;

    private Date modifyTime;

    public long getAccessId() {
        return accessId;
    }

    public void setAccessId(long accessId) {
        this.accessId = accessId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getReturnSize() {
        return returnSize;
    }

    public void setReturnSize(long returnSize) {
        this.returnSize = returnSize;
    }

    public String getAccessUtil() {
        return accessUtil;
    }

    public void setAccessUtil(String accessUtil) {
        this.accessUtil = accessUtil;
    }

    public int getLogFrom() {
        return logFrom;
    }

    public void setLogFrom(int logFrom) {
        this.logFrom = logFrom;
    }

    public String getReserveString_1() {
        return reserveString_1;
    }

    public void setReserveString_1(String reserveString_1) {
        this.reserveString_1 = reserveString_1;
    }

    public String getReserveString_2() {
        return reserveString_2;
    }

    public void setReserveString_2(String reserveString_2) {
        this.reserveString_2 = reserveString_2;
    }

    public String getReserveString_3() {
        return reserveString_3;
    }

    public void setReserveString_3(String reserveString_3) {
        this.reserveString_3 = reserveString_3;
    }

    public int getReserveInt_1() {
        return reserveInt_1;
    }

    public void setReserveInt_1(int reserveInt_1) {
        this.reserveInt_1 = reserveInt_1;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "AccessLogDO{" +
                "accessId=" + accessId +
                ", ip='" + ip + '\'' +
                ", accessTime=" + accessTime +
                ", requestType=" + requestType +
                ", requestUrl='" + requestUrl + '\'' +
                ", statusCode=" + statusCode +
                ", returnSize=" + returnSize +
                ", accessUtil='" + accessUtil + '\'' +
                ", logFrom=" + logFrom +
                ", reserveString_1='" + reserveString_1 + '\'' +
                ", reserveString_2='" + reserveString_2 + '\'' +
                ", reserveString_3='" + reserveString_3 + '\'' +
                ", reserveInt_1=" + reserveInt_1 +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
