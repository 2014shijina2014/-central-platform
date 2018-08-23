package com.central.oauth.dao;

import com.central.oauth.model.SysService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: [gitgeek]
 * @Date: [2018-08-23 12:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Mapper
public interface SysServiceDao {

    @Insert("insert into sys_service(parentId, name, url, path, css, sort, createTime, updateTime) "
            + "values (#{parentId}, #{name}, #{url} , #{path} , #{css}, #{sort}, #{createTime}, #{updateTime})")
    int save(SysService service);

    int update(SysService service);

    @Select("select * from sys_service t where t.id = #{id}")
    SysService findById(Long id);

    @Delete("delete from sys_service where id = #{id}")
    int delete(Long id);

    @Delete("delete from sys_service where parentId = #{id}")
    int deleteByParentId(Long id);

    @Select("select * from sys_service t order by t.sort")
    List<SysService> findAll();

    @Select("select * from sys_service t where t.isMenu = 1 order by t.sort")
    List<SysService> findOnes();

}
