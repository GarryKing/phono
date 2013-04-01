package name.elegant.phono.server.vo.amusing;

/**
 * User: zeqing.hzq
 * Date: 13-4-1
 * Time: ÏÂÎç5:40
 */
public class IpLogVO {

    private String ip;

    private String address;

    private int accessNum;

    public IpLogVO(String ip, String address, int accessNum) {
        this.ip = ip;
        this.address = address;
        this.accessNum = accessNum;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(int accessNum) {
        this.accessNum = accessNum;
    }
}
