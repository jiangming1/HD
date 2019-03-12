package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Video;

public interface VideoService {
	/**
	 * 查询所有视频
	 * @return
	 */
	public List<Video> getAll();
	
	/**
	 * 分页查询视频
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Video> getByPage(Integer page,Integer rows);
	
	/**
	 * 根据名称查询所有视频
	 * @return
	 */
	public List<Video> getAllByName(String name);
	
	/**
	 * 根据名称分页查询视频
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Video> getPageByName(String name,Integer page,Integer rows);
	
	/**
	 * 查询前4个
	 * @return
	 */
	public List<Video> getAhead();
	
	/**
	 * 添加视频
	 */
	public void add(Video video);
	
	/**
	 * 修改视频
	 */
	public void edit(Video video);
	
	/**
	 * 根据id查询视频
	 * @param id
	 * @return
	 */
	public Video findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
