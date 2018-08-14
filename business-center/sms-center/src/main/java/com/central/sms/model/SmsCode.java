package com.central.sms.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SmsCode implements Serializable {

	private static final long serialVersionUID = -3122215341764978293L;
	//短信流水号
	private String key;
}
