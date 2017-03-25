/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午5:52:44
 */
package com.yeepay.g3.facade.activity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yeepay.g3.facade.activity.enums.PrizeGrantWayEnum;
import com.yeepay.g3.facade.activity.enums.PrizeStatusEnum;
import com.yeepay.g3.facade.activity.enums.PrizeTypeEnum;

/**
 * @author cdt
 * @date 2016-5-16
 * @time 下午5:52:44
 */
public class ActivityPrizeDTO extends ActivityResultDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1973910631271243849L;

	/**
	 * @author cdt
	 * @date 2016-5-16
	 * @time 下午5:52:59
	 */

	private Long id;

	private Long version;
    /** 奖品码 **/
    private String prizeCode;
    /** 奖品名称 **/
    private String prizeName;
    /** 奖品类型  **/
    private PrizeTypeEnum prizeType;
    /** 奖品发放方式 **/
    private PrizeGrantWayEnum grantWay;
    /** 奖品说明 **/
    private String prizeRemark;
    /**奖品状态**/
    private PrizeStatusEnum prizeStatus;
    /**	对应具体物品ID **/
    private Long relaGoodsId;
    /**	事件动作表ID **/
    private Long actionId;
    /** 奖品数量  **/
    private Long prizeTotalCount;
    /**已中奖数量 **/
    private Long prizeGrantCount;
    /**中奖概率 **/
    private BigDecimal prizeOdds;
    /**创建日期 **/
    private Date createTime;
    /**创建人 **/
    private String creator;
    /**审核操作日期 **/
    private Date checkedTime;
    /**审核操作人 **/
    private String checkor;
    
    /**奖品等级 **/
    private Integer prizeLevel; 
    
    public Integer getPrizeLevel() {
		return prizeLevel;
	}

	public void setPrizeLevel(Integer prizeLevel) {
		this.prizeLevel = prizeLevel;
	}

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

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode == null ? null : prizeCode.trim();
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public PrizeTypeEnum getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(PrizeTypeEnum prizeType) {
        this.prizeType = prizeType;
    }

    public PrizeGrantWayEnum getGrantWay() {
        return grantWay;
    }

    public void setGrantWay(PrizeGrantWayEnum grantWay) {
        this.grantWay = grantWay;
    }

    public String getPrizeRemark() {
        return prizeRemark;
    }

    public void setPrizeRemark(String prizeRemark) {
        this.prizeRemark = prizeRemark == null ? null : prizeRemark.trim();
    }

    public Long getRelaGoodsId() {
        return relaGoodsId;
    }

    public void setRelaGoodsId(Long relaGoodsId) {
        this.relaGoodsId = relaGoodsId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getPrizeTotalCount() {
        return prizeTotalCount;
    }

    public void setPrizeTotalCount(Long prizeTotalCount) {
        this.prizeTotalCount = prizeTotalCount;
    }

    public Long getPrizeGrantCount() {
        return prizeGrantCount;
    }

    public void setPrizeGrantCount(Long prizeGrantCount) {
        this.prizeGrantCount = prizeGrantCount;
    }

    public BigDecimal getPrizeOdds() {
        return prizeOdds;
    }

    public void setPrizeOdds(BigDecimal prizeOdds) {
        this.prizeOdds = prizeOdds;
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

    public Date getCheckedTime() {
        return checkedTime;
    }

    public void setCheckedTime(Date checkedTime) {
        this.checkedTime = checkedTime;
    }

    public String getCheckor() {
        return checkor;
    }

    public void setCheckor(String checkor) {
        this.checkor = checkor == null ? null : checkor.trim();
    }

	public PrizeStatusEnum getPrizeStatus() {
		return prizeStatus;
	}

	public void setPrizeStatus(PrizeStatusEnum prizeStatus) {
		this.prizeStatus = prizeStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityPrizeDTO [id=");
		builder.append(id);
		builder.append(", version=");
		builder.append(version);
		builder.append(", prizeCode=");
		builder.append(prizeCode);
		builder.append(", prizeName=");
		builder.append(prizeName);
		builder.append(", prizeType=");
		builder.append(prizeType);
		builder.append(", grantWay=");
		builder.append(grantWay);
		builder.append(", prizeRemark=");
		builder.append(prizeRemark);
		builder.append(", prizeStatus=");
		builder.append(prizeStatus);
		builder.append(", relaGoodsId=");
		builder.append(relaGoodsId);
		builder.append(", actionId=");
		builder.append(actionId);
		builder.append(", prizeTotalCount=");
		builder.append(prizeTotalCount);
		builder.append(", prizeGrantCount=");
		builder.append(prizeGrantCount);
		builder.append(", prizeOdds=");
		builder.append(prizeOdds);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", checkedTime=");
		builder.append(checkedTime);
		builder.append(", checkor=");
		builder.append(checkor);
		builder.append(", prizeLevel=");
		builder.append(prizeLevel);
		builder.append("]");
		return builder.toString();
	}
}
