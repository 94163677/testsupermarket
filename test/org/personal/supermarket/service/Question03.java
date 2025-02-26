package org.personal.supermarket.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.personal.supermarket.entity.CartItem;
import org.personal.supermarket.entity.Category;
import org.personal.supermarket.entity.Discount;
import org.personal.supermarket.entity.Goods;
import org.personal.supermarket.entity.GoodsDiscount;
import org.personal.supermarket.entity.Order;
import org.personal.supermarket.service.impl.OrderServiceImpl;
import org.personal.supermarket.tools.AmountTools;

public class Question03 {

	@Test
	public void test() {
		List<Goods> goodsList = getGoods();
		DiscountService discountService = getDiscountService(goodsList);
		OrderService orderService = getOrderService(discountService);
		
		//苹果跟草莓跟芒果都有，3*8 + 13*4*0.8 + 20*5= 165.6
		List<CartItem> list1 = Arrays.asList(
				new CartItem(goodsList.get(0), 3),
				new CartItem(goodsList.get(1), 4),
				new CartItem(goodsList.get(2), 5)
				);
		Order order1 = orderService.createOrder(list1);
		assertTrue("代码金额计算错误1", AmountTools.isEqual(order1.getPayAmount(), new BigDecimal(165.6)));
		
		//没有草莓,3*8 + 20*5 = 124
		List<CartItem> list2 = Arrays.asList(
				new CartItem(goodsList.get(0), 3),
				new CartItem(goodsList.get(1), 0),
				new CartItem(goodsList.get(2), 5)
				);
		Order order2 = orderService.createOrder(list2);
		assertTrue("代码金额计算错误2", AmountTools.isEqual(order2.getPayAmount(), new BigDecimal(124)));
		
	}

	
	
	private static List<Goods> getGoods(){
		return Arrays.asList(
				new Goods("苹果", 8d),
				new Goods("草莓", 13d),
				new Goods("芒果", 20d)
				);
	}
	
	private static DiscountService getDiscountService(List<Goods> goods){
		return new DiscountService(){
			@Override
			public List<Discount> getCurrentDiscount(){
				GoodsDiscount gdiscount = new GoodsDiscount();
				gdiscount.setName("草莓8折促销");
				gdiscount.setDiscoutGoodsList(Arrays.asList(goods.get(1)));
				gdiscount.setDiscoutRate(new BigDecimal(0.8));
				gdiscount.setPriority(1);
				return Arrays.asList(gdiscount);
			}
		};
	}
	
	private static OrderService getOrderService(DiscountService service){
		OrderServiceImpl impl = new OrderServiceImpl();
		impl.setDiscountService(service);
		return impl;
	}
}
