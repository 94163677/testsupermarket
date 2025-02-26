package org.personal.supermarket.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 */
public class Cart {
	//购物车的商品列表
	private List<CartItem> itemList;
	
	public Cart(){
		itemList = new ArrayList<>();
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}
}
