package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.User;
import shan.HDHealthManagement.po.UserRole;

public interface UserService {
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
	public List<User> getByPage(Integer page,Integer rows);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user,String role);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public void edit(User user,String role);
	
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
}
