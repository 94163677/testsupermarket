package org.personal.supermarket.entity;

/**
 * 折扣抽象类
 */
public abstract class Discount {
	//折扣名称
	private String name;
	//折扣优先级，折扣计算金额时按优先级计算
	private Integer priority;
	
	//判断当前订单是否适用该折扣
	public abstract boolean isMatchDiscount(Order order);
	
	//根据折扣计算订单的金额
	public abstract void calculateOrderAmount(Order order);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null){
			return false;
		}
		if(!other.getClass().equals(this.getClass())){
			return false;
		}
		return name.equals(((Discount)other).getName());
	}
}
