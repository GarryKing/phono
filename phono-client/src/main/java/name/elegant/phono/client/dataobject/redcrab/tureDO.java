package name.elegant.phono.client.dataobject.redcrab;

import java.util.Date;

/**
 * User: Administrator
 * Date: 13-4-17
 * Time: ÏÂÎç11:25
 */
public class tureDO {

    private Long picId;

    private String sourceUrl;

    private String description;

    private Date gmtCreate;

    private Date gmtModify;

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}
