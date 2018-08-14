package com.central.sms.service;

import java.util.Map;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.central.model.common.PageResult;
import com.central.sms.model.Sms;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 短信service 
 */
public interface SmsService {

	void save(Sms sms, Map<String, String> params);

	void update(Sms sms);

	Sms findById(Long id);

	PageResult<Sms> findSms(Map<String, Object> params);

	/**
	 * 发送短信
	 */
	SendSmsResponse sendSmsMsg(Sms sms);
}
