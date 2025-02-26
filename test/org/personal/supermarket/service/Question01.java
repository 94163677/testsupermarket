package org.personal.supermarket.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.personal.supermarket.entity.CartItem;
import org.personal.supermarket.entity.Category;
import org.personal.supermarket.entity.Discount;
import org.personal.supermarket.entity.Goods;
import org.personal.supermarket.entity.Order;
import org.personal.supermarket.service.impl.OrderServiceImpl;
import org.personal.supermarket.tools.AmountTools;

public class Question01 {

	@Test
	public void test() {
		List<Goods> goodsList = getGoods();
		DiscountService discountService = getDiscountService(null);
		OrderService orderService = getOrderService(discountService);
		
		//苹果跟草莓都有，3*8 + 13*4 = 76
		List<CartItem> list1 = Arrays.asList(
				new CartItem(goodsList.get(0), 3),
				new CartItem(goodsList.get(1), 4)
				);
		Order order1 = orderService.createOrder(list1);
		assertTrue("代码金额计算错误1", AmountTools.isEqual(order1.getPayAmount(), new BigDecimal(76)));
		
		//苹果有，草莓没有,3*8 = 24
		List<CartItem> list2 = Arrays.asList(
				new CartItem(goodsList.get(0), 3),
				new CartItem(goodsList.get(1), 0)
				);
		Order order2 = orderService.createOrder(list2);
		assertTrue("代码金额计算错误2", AmountTools.isEqual(order2.getPayAmount(), new BigDecimal(24)));
		
		//苹果没有，草莓有,13*4 =52
		List<CartItem> list3 = Arrays.asList(
				new CartItem(goodsList.get(0), 0),
				new CartItem(goodsList.get(1), 4)
				);
		Order order3 = orderService.createOrder(list3);
		assertTrue("代码金额计算错误3", AmountTools.isEqual(order3.getPayAmount(), new BigDecimal(52)));
		
	}

	
	
	private static List<Goods> getGoods(){
		return Arrays.asList(
				new Goods("苹果", 8d),
				new Goods("草莓", 13d)
				);
	}
	
	private static DiscountService getDiscountService(List<Category> categoryList){
		return new DiscountService(){
			@Override
			public List<Discount> getCurrentDiscount(){
				return new ArrayList<>();
			}
		};
	}
	
	private static OrderService getOrderService(DiscountService service){
		OrderServiceImpl impl = new OrderServiceImpl();
		impl.setDiscountService(service);
		return impl;
	}
}
