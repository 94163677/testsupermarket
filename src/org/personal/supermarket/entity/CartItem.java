package org.personal.supermarket.entity;

import java.math.BigDecimal;

/**
 * 购物车商品
 */
public class CartItem {

	//购物车的商品
	private Goods goods;
	//商品对应的数量，有些商品（如水果）是称重的，因此用BigDecimal
	private BigDecimal quantity;
	//原价
	private BigDecimal orgAmount;
	//付款价格（折后价）
	private BigDecimal payAmount;
	
	public CartItem(Goods goods, double quantity){
		this.goods = goods;
		this.quantity = new BigDecimal(quantity);
	}
	
	public CartItem(Goods goods, BigDecimal quantity){
		this.goods = goods;
		this.quantity = quantity;
	}
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getOrgAmount() {
		return orgAmount;
	}

	public void setOrgAmount(BigDecimal orgAmount) {
		this.orgAmount = orgAmount;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
}
