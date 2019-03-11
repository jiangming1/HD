package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import shan.HDHealthManagement.po.User;
import shan.HDHealthManagement.po.UserRole;

@MapperScan
public interface UserDao {
	/**
	 * 根据名称获取用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String userName);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> getAll();
	
	/**
	 * 分页查询用户
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<User> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 添加用户
	 */
	public void add(User user);
	
	/**
	 * 添加用户
	 */
	public void edit(User user);
	
	/**
	 * 添加用户角色
	 * @param userRole
	 */
	public void addUserRole(UserRole userRole);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User findById(Long id);
	
	/**
	 * 根据id查询用户角色
	 * @param id
	 * @return
	 */
	public List<UserRole> findRoleById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
	
	/**
	 * 根据用户id角色id删除
	 * @param userId
	 * @param roleId
	 */
	public void delUserRole(@Param("userId")Long userId,@Param("roleId")Long roleId);
}
