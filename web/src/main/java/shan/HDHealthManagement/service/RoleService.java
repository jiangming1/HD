package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Role;

public interface RoleService {
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> getAllRole();
}
