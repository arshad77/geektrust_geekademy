package com.example.geektrust.controllers;

import java.util.Comparator;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.datastore.Data;

public class Billing {
	Data data = null;

	public Billing(Data data) {
		this.data = data;
	}

	public void calculateSubTotal() {
		for (int i = 0; i < data.getProgramsList().size(); i++) {
			data.addToSubTotal(data.getProgramsAmount().get(i) * data.getProgramsCount().get(i));
		}
	}

	public void handleProMembershipDiscounts() {
		data.setProMemebershipFee(Constants.PRO_MEMBERSHIP_FEE);
		data.addToSubTotal(data.getProMemebershipFee());

		for (int i = 0; i < data.getProgramsList().size(); i++) {
			float price = data.getProgramsAmount().get(i) * data.getProgramsCount().get(i);
			float discountRate = 0.0f;
			if (data.getProgramsList().get(i).equalsIgnoreCase("CERTIFICATION"))
				discountRate = Constants.PRO_MEMBERSHIP_CERTIFICATION_DISCOUNT;
			else if (data.getProgramsList().get(i).equalsIgnoreCase("DEGREE"))
				discountRate = Constants.PRO_MEMBERSHIP_DEGREE_DISCOUNT;
			else if (data.getProgramsList().get(i).equalsIgnoreCase("DIPLOMA"))
				discountRate = Constants.PRO_MEMBERSHIP_DIPLOMA_DISCOUNT;
			data.addToProDiscount(price * discountRate);
		}

		data.subFromSubTotal(data.getProDiscount());

	}

	public void addEnrollmentFee() {
		data.setEnrollmentnFee(Constants.ENROLLMENT_FEE);
		data.addToTotal(data.getEnrollmentnFee());
	}

	public void calculateTotalAfterCouponDiscount(String coupon, Float discountPrice) {
		data.setFinalCoupon(coupon);
		data.setCouponDiscount(discountPrice);
		data.setTotal(data.getSubTotal() - data.getCouponDiscount());
	}

	public void bill() {
		calculateSubTotal();

		if (data.isProMembershipAdded()) {
			handleProMembershipDiscounts();
		}

		Integer pgmCnt = data.getProgramsCount().stream().reduce(0, (a, b) -> a + b);
		if (pgmCnt >= Constants.COUPON_B4G1_MIN_PROGRAM_COUNT) {
			Float lowVal = data.getProgramsAmount().stream().min(Comparator.comparing(Float::valueOf)).get();
			calculateTotalAfterCouponDiscount("B4G1", lowVal);
		} else if (data.getSubTotal() >= Constants.COUPON_DEAL_G20_MIN_SUB_TOTAL
				&& data.getCoupons().contains("DEAL_G20")) {
			calculateTotalAfterCouponDiscount("DEAL_G20", data.getSubTotal() * Constants.COUPON_DEAL_G20_DISCOUNT);
		} else if (pgmCnt >= Constants.COUPON_DEAL_G5_MIN_PROGRAM_COUNT && data.getCoupons().contains("DEAL_G5")) {
			calculateTotalAfterCouponDiscount("DEAL_G5", data.getSubTotal() * Constants.COUPON_DEAL_G5_DISCOUNT);
		} else {
			data.setTotal(data.getSubTotal());
		}

		if (data.getTotal() < Constants.ENROLLMENT_FEE_EXCEPTION_REQUIREMENT_AMOUNT) {
			addEnrollmentFee();
		}
		
		printInvoice();
	}

	public void printInvoice() {
		System.out.printf("SUB_TOTAL %.2f\n", data.getSubTotal());
		System.out.printf("COUPON_DISCOUNT %s %.2f\n", data.getFinalCoupon(), data.getCouponDiscount());
		System.out.printf("TOTAL_PRO_DISCOUNT %.2f\n", data.getProDiscount());
		System.out.printf("PRO_MEMBERSHIP_FEE %.2f\n", data.getProMemebershipFee());
		System.out.printf("ENROLLMENT_FEE %.2f\n", data.getEnrollmentnFee());
		System.out.printf("TOTAL %.2f\n", data.getTotal());
	}
}
