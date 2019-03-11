package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shan.HDHealthManagement.Mapper.ArticleDao;
import shan.HDHealthManagement.po.Article;
import shan.HDHealthManagement.service.ArticleService;

@Service(value="articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleDao articleDao;
	
	@Transactional(readOnly = true)
	public List<Article> getAll() {
		return articleDao.getAll();
	}
	
	@Transactional(readOnly  = true)
	public List<Article> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return articleDao.getByPage(index, rows);
	}

	@Transactional(readOnly  = true)
	public List<Article> getAllByName(String name) {
		name = "%"+name+"%";
		return articleDao.getAllByName(name);
	}

	@Transactional(readOnly  = true)
	public List<Article> getPageByName(String name, Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		name = "%"+name+"%";
		return articleDao.getPageByName(name, index, rows);
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	@Transactional(readOnly  = true)
	public Article findById(Long id) {
		return articleDao.findById(id);
	}

	public void del(Long id) {
		articleDao.del(id);
	}

}
