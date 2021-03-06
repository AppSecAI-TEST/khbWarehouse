package com.yeepay.g3.core.activity.entity;

import java.util.Arrays;
import java.util.Date;

import com.yeepay.g3.utils.persistence.EntityVersion;

public class ActivityAppAd implements EntityVersion<Long>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3464929211110052665L;
	private Long id;
    /**编码**/
    private String code;
    /**url **/
    private String url;
    /**有效开始时间 **/
    private Date validTimeStart;
    /**有效结束时间 **/
    private Date validTimeEnd;
    /**创建时间 **/
    private Date createTime;
    /**更新事件 **/
    private Date updateTime;
    /**状态 **/
    private String status;
    /**版本 **/
    private Long version;
    /**图片 **/
    private byte[] img;
    /**广告名称 **/
    private String name;
    /**创建人 **/
    private String cretor;
    /**修改人**/
    private String updator;

    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCretor() {
		return cretor;
	}

	public void setCretor(String cretor) {
		this.cretor = cretor;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getValidTimeStart() {
        return validTimeStart;
    }

    public void setValidTimeStart(Date validTimeStart) {
        this.validTimeStart = validTimeStart;
    }

    public Date getValidTimeEnd() {
        return validTimeEnd;
    }

    public void setValidTimeEnd(Date validTimeEnd) {
        this.validTimeEnd = validTimeEnd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

	@Override
	public String toString() {
		return "ActivityAppAd [id=" + id + ", code=" + code + ", url=" + url
				+ ", validTimeStart=" + validTimeStart + ", validTimeEnd="
				+ validTimeEnd + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", status=" + status + ", version=" + version
				+ ", name=" + name
				+ ", cretor=" + cretor + ", updator=" + updator + "]";
	}
}