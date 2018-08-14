package com.central.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-08-01 23:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T datas;
    private Integer resp_code;
    private String resp_msg;

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, CodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, CodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> Result<T> succeedWith(T datas, Integer code,String msg) {
        return new Result<T>(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return failedWith(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model,String msg) {
        return failedWith(model, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T datas, Integer code, String msg) {
        return new Result<T>( datas, code, msg);
    }

}
