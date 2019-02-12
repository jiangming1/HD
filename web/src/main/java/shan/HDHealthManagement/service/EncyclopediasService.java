package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Encyclopedias;

public interface EncyclopediasService {
	/**
	 * 查询所有小百科
	 * @return
	 */
	public List<Encyclopedias> getAll();
	
	/**
	 * 分页查询小百科
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Encyclopedias> getByPage(Integer page,Integer rows);
	
	/**
	 * 添加小百科
	 */
	public void add(Encyclopedias encyclopedias);
	
	/**
	 * 修改小百科
	 */
	public void edit(Encyclopedias encyclopedias);
	
	/**
	 * 根据id查询小百科
	 * @param id
	 * @return
	 */
	public Encyclopedias findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
