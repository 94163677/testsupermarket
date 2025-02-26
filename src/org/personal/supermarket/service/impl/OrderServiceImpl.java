package org.personal.supermarket.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.personal.supermarket.entity.CartItem;
import org.personal.supermarket.entity.Discount;
import org.personal.supermarket.entity.Order;
import org.personal.supermarket.entity.OrderItem;
import org.personal.supermarket.service.DiscountService;
import org.personal.supermarket.service.OrderService;
import org.personal.supermarket.tools.AmountTools;

public class OrderServiceImpl implements OrderService {

	private DiscountService discountService;
	
	@Override
	public Order createOrder(List<CartItem> itemList) {
		Order order = new Order();
		//空列表检查
		if(itemList == null || itemList.size() <= 0){
			return order;
		}
		itemList = itemList.stream().filter(item -> item != null).collect(Collectors.toList());
		if(itemList == null || itemList.size() <= 0){
			return order;
		}
		//创建订单商品列表
		order.setItemList(createOrderItemList(itemList));
		//获取折扣列表
		order.setDiscountList(getOrderDiscountList(order));
		
		//按折扣计算总价
		for(Discount discount : order.getDiscountList()){
			discount.calculateOrderAmount(order);
		}
		
		//如果折扣里面没有计算总价和实付价，要重新计算
		if(order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0 
				|| order.getPayAmount().compareTo(BigDecimal.ZERO) <= 0){
			order.setTotalAmount(BigDecimal.ZERO);
			for(OrderItem item : order.getItemList()){
				order.setTotalAmount(order.getTotalAmount().add(item.getPayAmount()));
			}
			order.setPayAmount(order.getTotalAmount());
		}
		return order;
	}
	
	/**
	 * 获取本订单对应的折扣列表，并按折扣优先级排序
	 * @param order
	 * @return
	 */
	private List<Discount> getOrderDiscountList(Order order){
		List<Discount> fullList = discountService.getCurrentDiscount();
		List<Discount> list = new ArrayList<>(fullList.size());
		fullList.forEach(item ->{
			if(item.isMatchDiscount(order)){
				list.add(item);
			}
		});
		list.sort((item1, item2) -> {return item1.getPriority().compareTo(item2.getPriority());});
		return list;
	}
	
	/**
	 * 从购物车的商品列表，创建订单的商品列表
	 * @param itemList
	 * @return
	 */
	private List<OrderItem> createOrderItemList(List<CartItem> itemList){
		List<OrderItem> list = new ArrayList<>(itemList.size());
		itemList.forEach(item -> {
			list.add(createOrderItem(item));
		});
		return list;
	}

	/**
	 * 从购物车的商品项，创建订单的商品项
	 * @param item
	 * @return
	 */
	private OrderItem createOrderItem(CartItem item){
		OrderItem oitem = new OrderItem(item.getGoods(), item.getQuantity());

		oitem.setOrgAmount(AmountTools.multiply(item.getGoods().getPrice(), item.getQuantity()));
		oitem.setPayAmount(oitem.getOrgAmount());
		return oitem;
	}

	public DiscountService getDiscountService() {
		return discountService;
	}

	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}
}
