package shan.HDHealthManagement.Mapper;

import java.util.List;

import shan.HDHealthManagement.po.Role;

public interface RoleDao {
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> getAllRole();
}
