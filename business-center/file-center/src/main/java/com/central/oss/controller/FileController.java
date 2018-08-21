package com.central.oss.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.central.model.common.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.central.annotation.log.LogAnnotation;
import com.central.model.common.PageResult;
import com.central.model.common.utils.PageUtil;
import com.central.oss.config.OssServiceFactory;
import com.central.oss.dao.FileDao;
import com.central.oss.model.FileInfo;
import com.central.oss.model.FileType;
import com.central.oss.service.FileService;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
*  文件上传 同步oss db双写 目前仅实现了阿里云,七牛云
*  参考src/main/view/upload.html
*/
@RestController
public class FileController {

	@Autowired
	private OssServiceFactory fileServiceFactory;

	/**
	 * 文件上传
	 * 根据fileType选择上传方式
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@LogAnnotation(module = "file-center:FileController:upload", recordRequestParam = false)
	@PostMapping("/files-anon")
	public FileInfo upload(@RequestParam("file") MultipartFile file) throws Exception {
		String fileType = FileType.QINIU.toString();
		FileService fileService = fileServiceFactory.getFileService(fileType);
		return fileService.upload(file);
	}

	/**
	 * layui富文本文件自定义上传
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@LogAnnotation(module = "file-center:FileController:uploadLayui", recordRequestParam = false)
	@PostMapping("/files/layui")
	public Map<String, Object> uploadLayui(@RequestParam("file") MultipartFile file )
			throws Exception {
		
		FileInfo fileInfo = upload(file);

		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		Map<String, Object> data = new HashMap<>();
		data.put("src", fileInfo.getUrl());
		map.put("data", data);

		return map;
	}

	/**
	 * 文件删除
	 * @param id
	 */
	@LogAnnotation(module = "")
	@PreAuthorize("hasAuthority('file:del')") 
	@DeleteMapping("/files/{id}")
	public Result delete(@PathVariable String id) {

		try{
			FileInfo fileInfo = fileServiceFactory.getFileService(FileType.QINIU.toString()).getById(id);
			if (fileInfo != null) {
				FileService fileService = fileServiceFactory.getFileService(fileInfo.getSource());
				fileService.delete(fileInfo);
			}
			return Result.succeed("操作成功");
		}catch (Exception ex){
			return Result.failed("操作失败");
		}

	}
 
	/**
	 * 文件查询
	 * @param params
	 * @return
	 */
	@PreAuthorize("hasAuthority('file:query')")
	@GetMapping("/files")
	public PageResult<FileInfo> findFiles(@RequestParam Map<String, Object> params) {
        
		return  fileServiceFactory.getFileService(FileType.QINIU.toString()).findList(params);

	}
}
