package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Description 优惠券使用范围实体类
 * @author zhenping.zhou
 * @CreateTime 2016年3月24日 下午4:49:41
 * @version 1.0
 */
public class ActivityCouponLevelDTO implements Serializable {
    
	private static final long serialVersionUID = 2154322236357200036L;

	private Long id;

    private Long version;

    /** 优惠券信息ID **/
    private Long couponId;

    /** 频道编码 **/
    private String channelCode;

    /** 大栏目编码 **/
    private String bigColumnCode;

    /** 小栏目编码 **/
    private String smallColumnCode;

    private Date createTime;

    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getBigColumnCode() {
		return bigColumnCode;
	}

	public void setBigColumnCode(String bigColumnCode) {
		this.bigColumnCode = bigColumnCode;
	}

	public String getSmallColumnCode() {
		return smallColumnCode;
	}

	public void setSmallColumnCode(String smallColumnCode) {
		this.smallColumnCode = smallColumnCode;
	}

	@Override
	public String toString() {
		return "ActivityCouponLevelDTO:[id="+id+";version="+version+";couponId="+couponId+";channelCode="+channelCode+";bigColumnCode="+bigColumnCode+";smallColumnCode="+smallColumnCode+"]";
	}
	
}