package org.personal.supermarket.entity;

import java.math.BigDecimal;

/**
 * 订单商品
 */
public class OrderItem extends CartItem {

	public OrderItem(Goods goods, double quantity){
		super(goods, quantity);
	}
	
	public OrderItem(Goods goods, BigDecimal quantity){
		super(goods, quantity);
	}
}
