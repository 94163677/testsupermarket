package org.personal.supermarket.entity;

import java.math.BigDecimal;
import java.util.List;

import org.personal.supermarket.tools.AmountTools;

/**
 * 商品品类折扣
 */
public class CategoryDiscount extends Discount {

	//参与品类折扣的商品分类列表
	private List<Category> discoutCategoryList;
	//折扣
	private BigDecimal discoutRate;
	
	@Override
	public boolean isMatchDiscount(Order order){
		if(order == null || order.getItemList() == null || order.getItemList().size() <= 0){
			return false;
		}
		for(OrderItem item : order.getItemList()){
			for(Category category : discoutCategoryList){
				for(Goods goods : category.getGoodsList()){
					if(goods.equals(item.getGoods())){
						return true;
					}
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
			boolean isMatch = false;
			for(Category category : discoutCategoryList){
				for(Goods goods : category.getGoodsList()){
					if(goods.equals(item.getGoods())){
						isMatch = true;
						break;
					}
				}
				//只要有一个种类命中即可
				if(isMatch){
					break;
				}
			}
			//如果符合折扣要求，则计算折后价格
			if(isMatch){
				item.setPayAmount(AmountTools.multiply(item.getPayAmount(), discoutRate));
			}
		}
	}

	public List<Category> getDiscoutCategoryList() {
		return discoutCategoryList;
	}

	public void setDiscoutCategoryList(List<Category> discoutCategoryList) {
		this.discoutCategoryList = discoutCategoryList;
	}

	public BigDecimal getDiscoutRate() {
		return discoutRate;
	}

	public void setDiscoutRate(BigDecimal discoutRate) {
		this.discoutRate = discoutRate;
	}
}
