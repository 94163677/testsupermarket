package org.personal.supermarket.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单
 */
public class Order{
	//订单商品列表
	private List<OrderItem> itemList;
	//订单涉及的折扣列表
	private List<Discount> discountList;
	//商品总价
	private BigDecimal totalAmount;
	//付款价格（最终价）
	private BigDecimal payAmount;
	
	public Order(){
		super();
		discountList = new ArrayList<>();
		totalAmount = BigDecimal.ZERO;
		payAmount = BigDecimal.ZERO;
	}
	
	public List<OrderItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public List<Discount> getDiscountList() {
		return discountList;
	}
	public void setDiscountList(List<Discount> discountList) {
		this.discountList = discountList;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
}
