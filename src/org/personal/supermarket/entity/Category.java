package org.personal.supermarket.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类（类别）
 * 与商品是多对多的关系
 */
public class Category {
	//分类名称
	private String name;
	//分类下对应的商品
	private List<Goods> goodsList;
	
	public Category(String name){
		this.name = name;
		this.goodsList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
}
