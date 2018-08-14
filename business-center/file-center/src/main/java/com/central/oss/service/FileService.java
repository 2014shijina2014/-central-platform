package com.central.oss.service;

import org.springframework.web.multipart.MultipartFile;

import com.central.oss.model.FileInfo;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件service 目前仅支持阿里云oss,七牛云
*/
public interface FileService {

	FileInfo upload(MultipartFile file ) throws Exception;

	void delete(FileInfo fileInfo);

}
