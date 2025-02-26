package org.personal.supermarket.service;

import java.util.List;

import org.personal.supermarket.entity.CartItem;
import org.personal.supermarket.entity.Order;

public interface OrderService {

	/**
	 * 从购物车的商品列表，创建订单
	 * @param itemList 购物车商品列表
	 * @return 订单
	 */
	Order createOrder(List<CartItem> itemList);
}
