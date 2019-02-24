package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Integral;

public interface IntegralService {
	/**
	 * 查询所有积分
	 * @return
	 */
	public List<Integral> getAll();
	
	/**
	 * 分页查询积分
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Integral> getByPage(Integer page,Integer rows);
}
