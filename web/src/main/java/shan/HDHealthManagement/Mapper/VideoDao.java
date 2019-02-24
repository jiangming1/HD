package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.Video;

public interface VideoDao {
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
	public List<Video> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 根据名称查询所有视频
	 * @return
	 */
	public List<Video> getAllByName(@Param("name")String name);
	
	/**
	 * 根据名称分页查询视频
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Video> getPageByName(@Param("name")String name,@Param("index")Integer index,@Param("rows")Integer rows);
	
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
