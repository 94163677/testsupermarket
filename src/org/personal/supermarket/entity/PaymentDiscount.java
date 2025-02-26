package org.personal.supermarket.entity;

import java.math.BigDecimal;

import org.personal.supermarket.tools.AmountTools;

/**
 * 满减折扣
 */
public class PaymentDiscount extends Discount {
	//满减的满价
	private BigDecimal overAmount;
	//满减的折扣金额
	private BigDecimal discoutAmount;
	//是否重复满减（true 每满足满价均减，false 只减一次）
	private boolean isRepeated;
	
	@Override
	public boolean isMatchDiscount(Order order){
		return true;//满减促销始终返回符合
	}
	
	@Override
	public void calculateOrderAmount(Order order) {
		//空列表检查
		if(order == null || order.getItemList() == null || order.getItemList().size() <= 0){
			return;
		}
		//重新计算订单的满减前价格
		BigDecimal amount = BigDecimal.ZERO;
		for(OrderItem item : order.getItemList()){
			amount = amount.add(item.getPayAmount());
		}
		order.setTotalAmount(amount);
		
		if(isRepeated){
			//如果每满减，则需要用商品总价除以满减金额，去掉小数点后的数字取整。
			int times = amount.divide(overAmount, 0, BigDecimal.ROUND_DOWN).intValue();
			if(times > 0){
				BigDecimal totalDiscountAmount = AmountTools.multiply(
						discoutAmount, new BigDecimal(times));
				order.setPayAmount(amount.subtract(totalDiscountAmount));
			}
		}else{
			//如果只减一次，则直接比较商品总价和满减金额。
			if(amount.compareTo(overAmount) >= 0){
				order.setPayAmount(amount.subtract(discoutAmount));
			}
		}
	}

	public BigDecimal getOverAmount() {
		return overAmount;
	}

	public void setOverAmount(BigDecimal overAmount) {
		this.overAmount = overAmount;
	}

	public BigDecimal getDiscoutAmount() {
		return discoutAmount;
	}

	public void setDiscoutAmount(BigDecimal discoutAmount) {
		this.discoutAmount = discoutAmount;
	}

	public boolean isRepeated() {
		return isRepeated;
	}

	public void setRepeated(boolean isRepeated) {
		this.isRepeated = isRepeated;
	}
}
