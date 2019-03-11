package shan.HDHealthManagement.service;

import java.util.List;

import shan.HDHealthManagement.po.Article;

public interface ArticleService {
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
	public List<Article> getByPage(Integer page,Integer rows);
	
	/**
	 * 根据名称查询所有文章
	 * @return
	 */
	public List<Article> getAllByName(String name);
	
	/**
	 * 根据名称分页查询文章
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Article> getPageByName(String name,Integer page,Integer rows);
	
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
