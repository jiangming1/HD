package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Cases;

public interface CasesService {
	/**
	 * 查询所有案例
	 * @return
	 */
	public List<Cases> getAll();
	
	/**
	 * 分页查询案例
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Cases> getByPage(Integer page,Integer rows);
	
	/**
	 * 查询前4个
	 * @return
	 */
	public List<Cases> getAhead();
	
	/**
	 * 根据名称查询所有案例
	 * @return
	 */
	public List<Cases> getAllByName(String name);
	
	/**
	 * 根据名称分页查询案例
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Cases> getPageByName(String name,Integer page,Integer rows);
	
	/**
	 * 添加案例
	 */
	public void add(Cases cases);
	
	/**
	 * 修改案例
	 */
	public void edit(Cases cases);
	
	/**
	 * 根据id查询案例
	 * @param id
	 * @return
	 */
	public Cases findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
