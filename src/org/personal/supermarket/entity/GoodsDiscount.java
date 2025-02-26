package org.personal.supermarket.entity;

import java.math.BigDecimal;
import java.util.List;

import org.personal.supermarket.tools.AmountTools;

public class GoodsDiscount extends Discount {

	//参与折扣的商品列表
	private List<Goods> discoutGoodsList;
	//折扣
	private BigDecimal discoutRate;
	
	@Override
	public boolean isMatchDiscount(Order order) {
		if(order == null || order.getItemList() == null || order.getItemList().size() <= 0){
			return false;
		}
		for(OrderItem item : order.getItemList()){
			for(Goods goods : discoutGoodsList){
				if(goods.equals(item.getGoods())){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void calculateOrderAmount(Order order) {
		//空列表检查
		if(order == null || order.getItemList() == null || order.getItemList().size() <= 0){
			return;
		}
		for(OrderItem item : order.getItemList()){
			for(Goods goods : discoutGoodsList){
				//如果符合折扣要求，则计算折后价格
				if(goods.equals(item.getGoods())){
					item.setPayAmount(AmountTools.multiply(item.getPayAmount(), discoutRate));
				}
			}
		}
	}

	public List<Goods> getDiscoutGoodsList() {
		return discoutGoodsList;
	}

	public void setDiscoutGoodsList(List<Goods> discoutGoodsList) {
		this.discoutGoodsList = discoutGoodsList;
	}

	public BigDecimal getDiscoutRate() {
		return discoutRate;
	}

	public void setDiscoutRate(BigDecimal discoutRate) {
		this.discoutRate = discoutRate;
	}
}
