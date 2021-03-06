package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import shan.HDHealthManagement.po.Expert;

@MapperScan
public interface ExpertDao {
	/**
	 * 查询所有专家
	 * @return
	 */
	public List<Expert> getAll();
	
	/**
	 * 分页查询专家
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Expert> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 查询前4个
	 * @return
	 */
	public List<Expert> getAhead();
	/**
	 * 添加专家
	 */
	public void add(Expert expert);
	
	/**
	 * 修改专家
	 */
	public void edit(Expert expert);
	
	/**
	 * 根据id查询专家
	 * @param id
	 * @return
	 */
	public Expert findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
