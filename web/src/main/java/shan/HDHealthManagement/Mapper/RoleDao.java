package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.Jurisdiction;
import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.po.RoleJurisdiction;

public interface RoleDao {
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
	public List<Role> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 添加benner
	 */
	public void add(Role role);
	
	/**
	 * 修改benner
	 */
	public void edit(Role role);
	
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
	 * 查询一级权限
	 * @return
	 */
	public List<Jurisdiction> getJurisdictionParent();
	
	/**
	 * 查询下级权限
	 * @param parentId
	 * @return
	 */
	public List<Jurisdiction> getJurisdictionByParentId(Long parentId);
	
	/**
	 * 添加角色权限
	 * @param roleJurisdiction
	 */
	public void addRoleJurisdiction(RoleJurisdiction roleJurisdiction);
	
	/**
	 * 删除角色权限
	 * @param roleId
	 * @param jurisdiction
	 */
	public void delRoleJurisdiction(Long roleId,Long jurisdictionId);
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
