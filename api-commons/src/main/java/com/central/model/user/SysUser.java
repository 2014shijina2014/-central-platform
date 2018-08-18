package com.central.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51 
 * 用户实体
 */
@Data
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5886012896705137070L;
	private Long id;
	private String username;
	private String password;
	private String nickname;
	private String headImgUrl;
	private String phone;
	private Integer sex;
	private Boolean enabled;
	private String type;
	private Date createTime;
	private Date updateTime;

	private List<SysRole> roles;

	private String roleId;


	private String oldPassword;
	private String newPassword;

}
