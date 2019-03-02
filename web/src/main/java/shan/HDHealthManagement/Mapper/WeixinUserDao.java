package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.IntegralRecord;
import shan.HDHealthManagement.po.WeixinUser;

public interface WeixinUserDao {
	/**
	 * 查询所有微信用户
	 * @return
	 */
	public List<WeixinUser> getAll();
	
	/**
	 * 分页查询微信用户
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<WeixinUser> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 查询积分记录
	 * @param weixinId
	 * @return
	 */
	public List<IntegralRecord> record(Long weixinId);
}
