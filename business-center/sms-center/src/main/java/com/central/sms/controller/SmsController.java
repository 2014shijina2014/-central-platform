package com.central.sms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.model.common.PageResult;
import com.central.model.common.utils.PhoneUtil;
import com.central.sms.model.Sms;
import com.central.sms.model.SmsCode;
import com.central.sms.service.SmsCodeService;
import com.central.sms.service.SmsService;

@RestController
public class SmsController {

	@Autowired
	private SmsCodeService smsCodeService;

	@Autowired
	private SmsService smsService;
	
	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @return
	 */
	@PostMapping(value = "/sms-anon/codes", params = { "phone" })
	public SmsCode sendSmsCode(String phone) {
		if (!PhoneUtil.checkPhone(phone)) {
			throw new IllegalArgumentException("手机号格式不正确");
		}

		SmsCode smsCode = smsCodeService.generateCode(phone);

		return smsCode;
	}

	/**
	 * 根据验证码和key获取手机号
	 * 
	 * @param key
	 * @param code
	 * @param delete
	 * 是否删除key
	 * @param second
	 * 不删除时，可重置过期时间（秒）
	 * @return
	 */
	@GetMapping(value = "/sms-anon/internal/phone", params = { "key", "code" })
	public String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second) {
		return smsCodeService.matcheCodeAndGetPhone(key, code, delete, second);
	}

	

	/**
	 * 查询短信发送记录
	 * 
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('sms:query')")
	@GetMapping("/sms")
	public PageResult<Sms> findSms(@RequestParam Map<String, Object> params) {
		return smsService.findSms(params);
	}
}
