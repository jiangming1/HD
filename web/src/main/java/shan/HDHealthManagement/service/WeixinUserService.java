package shan.HDHealthManagement.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import shan.HDHealthManagement.po.Clock;
import shan.HDHealthManagement.po.IntegralRecord;
import shan.HDHealthManagement.po.WeixinUser;

public interface WeixinUserService {
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
	public List<WeixinUser> getByPage(Integer page,Integer rows);
	
	/**
	 * 根据id查询
	 * @param WeixinId
	 * @return
	 */
	public WeixinUser findById(Long id);
	
	/**
	 * 根据openid查询
	 * @param WeixinId
	 * @return
	 */
	public WeixinUser findByOpenId(String openid);
	
	/**
	 * 增加
	 * @param weixinUser
	 */
	public void add(WeixinUser weixinUser);
	
	/**
	 * 修改
	 * @param weixinUser
	 */
	public void edit(WeixinUser weixinUser);
	
	/**
	 * 查询积分记录
	 * @param weixinId
	 * @return
	 */
	public List<IntegralRecord> record(Long weixinId);
	
	/**
	 * 本周打卡排名获取
	 * @return
	 */
	public List<WeixinUser> week();
	
	/**
	 * 本月打卡排名获取
	 * @return
	 */
	public List<WeixinUser> mouth();
	
	/**
	 * 本年打卡排名获取
	 * @return
	 */
	public List<WeixinUser> year();
	
	/**
	 * 本周打卡排名获取
	 * @return
	 */
	public List<WeixinUser> weekAll();
	
	/**
	 * 本月打卡排名获取
	 * @return
	 */
	public List<WeixinUser> mouthAll();
	
	/**
	 * 本年打卡排名获取
	 * @return
	 */
	public List<WeixinUser> yearAll();
	
	/**
	 * 添加打卡记录
	 */
	public void addClock(Long weixinId,Integer motion,Integer housework);
}
