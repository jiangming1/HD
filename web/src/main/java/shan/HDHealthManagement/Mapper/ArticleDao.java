package shan.HDHealthManagement.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shan.HDHealthManagement.po.Article;

public interface ArticleDao {
	/**
	 * 查询所有文章
	 * @return
	 */
	public List<Article> getAll();
	
	/**
	 * 分页查询文章
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Article> getByPage(@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 根据名称查询所有文章
	 * @return
	 */
	public List<Article> getAllByName(@Param("name")String name);
	
	/**
	 * 根据名称分页查询文章
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Article> getPageByName(@Param("name")String name,@Param("index")Integer index,@Param("rows")Integer rows);
	
	/**
	 * 添加文章
	 */
	public void add(Article article);
	
	/**
	 * 根据id查询文章
	 * @param id
	 * @return
	 */
	public Article findById(Long id);
	
	/**
	 * 根据id删除
	 */
	public void del(Long id);
}
