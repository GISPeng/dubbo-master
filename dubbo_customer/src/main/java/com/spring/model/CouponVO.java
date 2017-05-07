package com.spring.model;

import java.util.List;

public class CouponVO implements Cloneable{
	private String couponId;
	private String shopEntityId;
	private List<Customer> customers;
	private int sourceId;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getShopEntityId() {
		return shopEntityId;
	}
	public void setShopEntityId(String shopEntityId) {
		this.shopEntityId = shopEntityId;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	
	public CouponVO clone(){
		CouponVO c = null;
		try {
			c = (CouponVO) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
