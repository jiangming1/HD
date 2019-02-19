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
	 * 添加benner
	 */
	public void add(Role role,String jurisdiction);
	
	/**
	 * 修改benner
	 */
	public void edit(Role role,String jurisdiction);
	
	/**
	 * 根据id查询benner
	 * @param id
	 * @return
	 */
	public Role findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public String getAllJurisdiction();
	
	/**
	 * 根据角色查询所有权限
	 * @param roleId
	 * @return
	 */
	public String getAllJurisdictionById(Long roleId);
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
