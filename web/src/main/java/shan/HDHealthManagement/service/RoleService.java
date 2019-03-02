package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Jurisdiction;
import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.po.RoleJurisdiction;

public interface RoleService {
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> getAllRole();
	
	/**
	 * 分页查询benner
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Role> getByPage(Integer page,Integer rows);
	
	/**
	 * 添加角色
	 */
	public void add(Role role);
	
	/**
	 * 修改角色
	 */
	public void edit(Role role);
	
	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
	
	/**
	 * 根据角色查询所有权限
	 * @param roleId
	 * @return
	 */
	public String getAllJurisdictionById(Long roleId);
	
	/**
	 * 修改角色所有权限
	 * @param treeChecked
	 * @param id
	 */
	public void editJurisdiction(String treeChecked,Long id);
	/**
	 * 查询角色权限
	 * @param roleId
	 * @return
	 */
	public List<RoleJurisdiction> findRoleJurisdictions(Long roleId);
	/**
	 * 根据id查询权限
	 * @param parentId
	 * @return
	 */
	public Jurisdiction findJurisdictionById(Long id);
}
