package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Expert;

public interface ExpertService {
	/**
	 * 查询所有专家
	 * @return
	 */
	public List<Expert> getAll();
	
	/**
	 * 分页查询专家
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Expert> getByPage(Integer page,Integer rows);
	
	/**
	 * 添加专家
	 */
	public void add(Expert expert);
	
	/**
	 * 修改专家
	 */
	public void edit(Expert expert);
	
	/**
	 * 根据id查询专家
	 * @param id
	 * @return
	 */
	public Expert findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
