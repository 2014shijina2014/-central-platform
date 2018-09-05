package com.central.generator.service;

import com.central.generator.dao.SysGeneratorDao;
import com.central.generator.utils.GenUtils;
import com.central.model.common.PageResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Author: [zhangzhiguang]
 * @Date: [2018-09-05 12:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Service
public interface SysGeneratorService {

     PageResult queryList(Map<String, Object> map);

     int queryTotal(Map<String, Object> map);

     Map<String, String> queryTable(String tableName);

     List<Map<String, String>> queryColumns(String tableName);

     byte[] generatorCode(String[] tableNames);

}
