package shan.HDHealthManagement.Mapper;

import java.util.List;

import shan.HDHealthManagement.po.Distribution;

public interface DistributionDao {
	/**
	 * 添加
	 * @param distribution
	 */
	public void add(Distribution distribution);
	/**
	 * 查询区域分布
	 * @return
	 */
	public List<Distribution> findByRegional();
	/**
	 * 查询年龄分布
	 * @return
	 */
	public List<Distribution> findByAge();
	/**
	 * 删除所有
	 */
	public void del();
	/**
	 * 查询所有
	 * @return
	 */
	public List<Distribution> getAll();
}
