package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Benner;

public interface BennerService {
	/**
	 * 查询所有benner
	 * @return
	 */
	public List<Benner> getAll();
	
	/**
	 * 分页查询benner
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Benner> getByPage(Integer page,Integer rows);
	
	/**
	 * 添加benner
	 */
	public void add(Benner benner);
	
	/**
	 * 修改benner
	 */
	public void edit(Benner benner);
	
	/**
	 * 根据id查询benner
	 * @param id
	 * @return
	 */
	public Benner findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
