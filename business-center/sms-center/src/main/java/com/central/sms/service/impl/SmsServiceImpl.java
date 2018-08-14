package com.central.sms.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.sms.dao.SmsDao;
import com.central.sms.model.Sms;
import com.central.sms.service.SmsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private IAcsClient acsClient;
	@Value("${aliyun.sms.sign.name:xxxxxx}")
	private String signName;
	@Value("${aliyun.sms.template.code:xxxxx}")
	private String templateCode;

	@Autowired
	private SmsDao smsDao;

	@Async
	@Override
	public SendSmsResponse sendSmsMsg(Sms sms) {
		if (sms.getSignName() == null) {
			sms.setSignName(this.signName);
		}

		if (sms.getTemplateCode() == null) {
			sms.setTemplateCode(this.templateCode);
		}

		// 阿里云短信官网demo代码
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(sms.getPhone());
		request.setSignName(sms.getSignName());
		request.setTemplateCode(sms.getTemplateCode());
		request.setTemplateParam(sms.getParams());
		request.setOutId(sms.getId().toString());

		SendSmsResponse response = null;
//		测试时不需要开此 add by owen begin
//		try {
//			response = acsClient.getAcsResponse(request);
//			if (response != null) {
//				log.info("发送短信结果：code:{}，message:{}，requestId:{}，bizId:{}", response.getCode(), response.getMessage(),
//						response.getRequestId(), response.getBizId());
//
//				sms.setCode(response.getCode());
//				sms.setMessage(response.getMessage());
//				sms.setBizId(response.getBizId());
//			}
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//		测试时不需要开此 add by owen end
		update(sms);

		return response;
	}

	@Transactional
	@Override
	public void save(Sms sms, Map<String, String> params) {
		if (!CollectionUtils.isEmpty(params)) {
			sms.setParams(JSONObject.toJSONString(params));
		}

		sms.setCreateTime(new Date());
		sms.setUpdateTime(sms.getCreateTime());
		sms.setDay(sms.getCreateTime());

		smsDao.save(sms);
	}

	@Transactional
	@Override
	public void update(Sms sms) {
		sms.setUpdateTime(new Date());
		smsDao.update(sms);
	}

	@Override
	public Sms findById(Long id) {
		return smsDao.findById(id);
	}

	@Override
	public PageResult<Sms> findSms(Map<String, Object> params) {
		int total = smsDao.count(params);
		List<Sms> list = Collections.emptyList();
		if (total > 0) {
			PageUtil.pageParamConver(params, true);

			list = smsDao.findList(params);
		}
		return  PageResult.<Sms>builder().data(list).code(0).count(total).build();
	}

}
