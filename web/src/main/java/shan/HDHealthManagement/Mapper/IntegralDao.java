package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.Integral;


public interface IntegralDao {
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
	public List<Integral> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
}
