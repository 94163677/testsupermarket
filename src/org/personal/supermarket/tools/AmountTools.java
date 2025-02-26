package org.personal.supermarket.tools;

import java.math.BigDecimal;

public class AmountTools {
	//本代码能接受的计算机计算误差值
	public static final BigDecimal MAX_COMPARE_NUM = new BigDecimal(0.000001d);
	/**
	 * 金额的乘法工具类
	 * 默认币种是人民币，保留2位小数
	 * @param amountA
	 * @param amountB
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal amountA, BigDecimal amountB){
		if(amountA == null || amountB == null){
			return BigDecimal.ZERO;
		}
		return amountA.multiply(amountB).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 比较两个金额是否相等，因为计算机计算误差的存在，需要计算两个金额相减是否小于误差值
	 * 小于误差值才认为相等
	 * @param amountA
	 * @param amountB
	 * @return
	 */
	public static boolean isEqual(BigDecimal amountA, BigDecimal amountB){
		BigDecimal sub = amountA.subtract(amountB);
		if(sub.compareTo(BigDecimal.ZERO) < 0){
			sub = sub.abs();
		}
		if(sub.compareTo(MAX_COMPARE_NUM) <= 0){
			return true;
		}
		return false;
	}
}
