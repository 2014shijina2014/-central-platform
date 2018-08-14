package com.central.sms.service;

import com.central.sms.model.SmsCode;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 短信验证码service
 */
public interface SmsCodeService {

	SmsCode generateCode(String phone);

	String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second);
}
