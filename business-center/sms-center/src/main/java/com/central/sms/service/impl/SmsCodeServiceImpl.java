package com.central.sms.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.central.sms.model.Sms;
import com.central.sms.model.SmsCode;
import com.central.sms.service.SmsCodeService;
import com.central.sms.service.SmsService;
import com.central.sms.utils.Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsCodeServiceImpl implements SmsCodeService {

	@Value("${aliyun.sms.expire-minute:15}")
	private Integer expireMinute;
	@Value("${aliyun.sms.day-count:30}")
	private Integer dayCount;
	@Autowired
	private StringRedisTemplate redisTemplate ;
	@Autowired
	private SmsService smsService;

	@Transactional
	@Override
	public SmsCode generateCode(String phone) {
		//生成流水号
		String uuid = UUID.randomUUID().toString();
		String code = Util.randomCode(6);

		Map<String, String> map = new HashMap<>(2);
		map.put("code", code);
		map.put("phone", phone);
		
		//短信验证码缓存15分钟，
		redisTemplate.opsForValue().set(smsRedisKey(uuid), JSONObject.toJSONString(map), expireMinute,
				TimeUnit.MINUTES);
		log.info("缓存验证码：{}", map);

		//存储sys_sms
		saveSmsAndSendCode(phone, code);
		SmsCode smsCode = new SmsCode();
		smsCode.setKey(uuid);
		return smsCode;
	}

	/**
	 * 保存短信记录，并发送短信
	 * @param phone
	 * @param code
	 */
	private void saveSmsAndSendCode(String phone, String code) {
		checkTodaySendCount(phone);

		Sms sms = new Sms();
		sms.setPhone(phone);

		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		smsService.save(sms, params);

		smsService.sendSmsMsg(sms);

		// 当天发送验证码次数+1
		String countKey = countKey(phone);
		redisTemplate.opsForValue().increment(countKey, 1L);
		redisTemplate.expire(countKey, 1, TimeUnit.DAYS);
	}

	

	/**
	 * 获取当天发送验证码次数
	 * 限制号码当天次数
	 * @param phone
	 * @return
	 */
	private void checkTodaySendCount(String phone) {
		String value =   redisTemplate.opsForValue().get(countKey(phone));
		if (value != null) {
			Integer count = Integer.valueOf(value );
			if (count > dayCount) {
				throw new IllegalArgumentException("已超过当天最大次数");
			}
		}

	}

	private String countKey(String phone) {
		return "sms:count:" + LocalDate.now().toString() + ":" + phone;
	}

	private String smsRedisKey(String str) {
		return "sms:" + str;
	}

	public String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second) {
		key = smsRedisKey(key);

		String value = (String)redisTemplate.opsForValue().get(key);
		if (value != null) {
			JSONObject json = JSONObject.parseObject(value);
			if (code != null && code.equals(json.getString("code"))) {
				log.info("验证码校验成功：{}", value);

				if (delete == null || delete) {
					redisTemplate.delete(key);
				}

				if (delete == Boolean.FALSE && second != null && second > 0) {
					redisTemplate.expire(key, second, TimeUnit.SECONDS);
				}

				return json.getString("phone");
			}

		}

		return null;
	}
}
