package com.xinnet.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.xinnet.annotation.NotEmpty;

public class Order  implements Serializable {
    private Integer id;

    @NotEmpty
    private String waterNum;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private BigDecimal amout;
    
    public Order(String waterNum,Integer userId,BigDecimal amout){
    	this.waterNum = waterNum;
    	this.userId = userId;
    	this.amout = amout;
    }
    public Order(){
    	
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