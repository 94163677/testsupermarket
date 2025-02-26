package org.personal.supermarket.service;

import java.util.List;

import org.personal.supermarket.entity.Discount;

public interface DiscountService {

	/**
	 * 获取当前超市生效的折扣列表
	 * @return 折扣列表
	 */
	List<Discount> getCurrentDiscount();
}
