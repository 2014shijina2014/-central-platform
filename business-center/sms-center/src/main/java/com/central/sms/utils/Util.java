package com.central.sms.utils;

import java.util.Random;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 短信验证码生成器
 */
public class Util {

	private static String[] NUMBERS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static Random RANDOM = new Random();

	/**
	 * 生成n位随机数值字符串
	 * 
	 * @param n
	 * @return
	 */
	public static String randomCode(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(NUMBERS[RANDOM.nextInt(NUMBERS.length)]);
		}

		return builder.toString();
	}
}
