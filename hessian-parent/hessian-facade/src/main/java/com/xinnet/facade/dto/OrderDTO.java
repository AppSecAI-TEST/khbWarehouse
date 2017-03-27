package com.xinnet.facade.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDTO  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5221578646464062257L;

	/**
	 * 
	 */

	private Integer id;

    private String waterNum;
    private Integer userId;
    private BigDecimal amout;
    
    public OrderDTO(String waterNum,Integer userId,BigDecimal amout){
    	this.waterNum = waterNum;
    	this.userId = userId;
    	this.amout = amout;
    }
    public OrderDTO(){
    	
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(String waterNum) {
        this.waterNum = waterNum == null ? null : waterNum.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmout() {
        return amout;
    }

    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", waterNum=" + waterNum + ", userId="
				+ userId + ", amout=" + amout + "]";
	}
}