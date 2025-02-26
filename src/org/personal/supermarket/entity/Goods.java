package org.personal.supermarket.entity;

import java.math.BigDecimal;

/**
 * 商品类
 */
public class Goods {
	//商品名称
	protected String name;
	
	//商品单价，这里不考虑多币种，默认是人民币
	protected BigDecimal price;

	public Goods(String name, double price){
		this.name = name;
		this.price = new BigDecimal(price);
	}
	
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}
		if(!other.getClass().equals(this.getClass())){
			return false;
		}
		return name.equals(((Goods)other).getName());
	}
}
