package com.central.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;


/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 *  权限标识
 */
@Data
public class SysPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1389727646460449239L;
	private Long id;
	private String permission;
	private String name;
	private Date createTime;
	private Date updateTime;

	private Long roleId;
	private Set<Long> authIds;

}
