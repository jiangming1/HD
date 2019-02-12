package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.CasesDao;
import shan.HDHealthManagement.po.Cases;
import shan.HDHealthManagement.service.CasesService;

@Service(value="casesService")
public class CasesServiceImpl implements CasesService {
	@Resource
	private CasesDao casesDao;
	
	public List<Cases> getAll() {
		return casesDao.getAll();
	}

	public List<Cases> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return casesDao.getByPage(index, rows);
	}

	public void add(Cases cases) {
		casesDao.add(cases);
	}

	public void edit(Cases cases) {
		casesDao.edit(cases);
	}

	public Cases findById(Long id) {
		return casesDao.findById(id);
	}

	public void del(Long id) {
		casesDao.del(id);
	}

}
