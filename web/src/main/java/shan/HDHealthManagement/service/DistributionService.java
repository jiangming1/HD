package shan.HDHealthManagement.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shan.HDHealthManagement.po.Distribution;

public interface DistributionService {
	/**
	 * 添加
	 * @param distribution
	 */
	public Boolean add(CommonsMultipartFile file);
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
	 * 查询所有
	 * @return
	 */
	public List<Distribution> getAll();
}
