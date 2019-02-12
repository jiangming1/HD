package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.Benner;


public interface BennerDao {
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
	public List<Benner> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
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
