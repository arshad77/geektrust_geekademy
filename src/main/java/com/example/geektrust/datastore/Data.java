package com.example.geektrust.datastore;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private List<String> programsList = null;
	private List<Integer> programsCount = null;
	private List<Float> programsAmount = null;
	private List<String> coupons = null;

	private Float subTotal = 0f;
	private Float total = 0f;
	private Float couponDiscount = 0f;
	private Float proDiscount = 0f;
	private Float enrollmentnFee = 0f;
	private Float proMemebershipFee = 0f;
	private String finalCoupon = "NA";
	private boolean proMembershipAdded = false;

	public Data() {
		programsList = new ArrayList<String>();
		programsCount = new ArrayList<Integer>();
		programsAmount = new ArrayList<Float>();
		coupons = new ArrayList<String>();
	}

	public List<String> getProgramsList() {
		return programsList;
	}

	public void addToProgramsList(String program) {
		this.programsList.add(program);
	}

	public List<Integer> getProgramsCount() {
		return programsCount;
	}

	public void addToProgramsCount(Integer count) {
		this.programsCount.add(count);
	}

	public List<Float> getProgramsAmount() {
		return programsAmount;
	}

	public void addToProgramsAmount(Float amount) {
		this.programsAmount.add(amount);
	}

	public List<String> getCoupons() {
		return coupons;
	}

	public void addToCoupons(String coupon) {
		this.coupons.add(coupon);
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void addToSubTotal(Float amount) {
		this.subTotal += amount;
	}

	public void subFromSubTotal(Float amount) {
		this.subTotal -= amount;
	}

	public Float getTotal() {
		return total;
	}

	public void addToTotal(Float amount) {
		this.total += amount;
	}

	public void setTotal(Float amount) {
		this.total = amount;
	}

	public void enableProMembership() {
		this.proMembershipAdded = true;
	}

	public String getFinalCoupon() {
		return finalCoupon;
	}

	public void setFinalCoupon(String coupon) {
		this.finalCoupon = coupon;
	}

	public Float getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(Float couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public Float getProDiscount() {
		return proDiscount;
	}

	public void addToProDiscount(Float proDiscount) {
		this.proDiscount += proDiscount;
	}

	public Float getEnrollmentnFee() {
		return enrollmentnFee;
	}

	public void setEnrollmentnFee(Float erollmentnFee) {
		this.enrollmentnFee = erollmentnFee;
	}

	public Float getProMemebershipFee() {
		return proMemebershipFee;
	}

	public void setProMemebershipFee(Float proMemebershipFee) {
		this.proMemebershipFee = proMemebershipFee;
	}

	public boolean isProMembershipAdded() {
		return proMembershipAdded;
	}
	
	

}
