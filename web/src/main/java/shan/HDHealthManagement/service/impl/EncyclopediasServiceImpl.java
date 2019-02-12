package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.EncyclopediasDao;
import shan.HDHealthManagement.po.Encyclopedias;
import shan.HDHealthManagement.service.EncyclopediasService;

@Service(value="encyclopediasService")
public class EncyclopediasServiceImpl implements EncyclopediasService {
	
	@Resource
	private EncyclopediasDao encyclopediasDao;
	
	public List<Encyclopedias> getAll() {
		return encyclopediasDao.getAll();
	}

	public List<Encyclopedias> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return encyclopediasDao.getByPage(index, rows);
	}

	public void add(Encyclopedias encyclopedias) {
		encyclopediasDao.add(encyclopedias);
	}

	public void edit(Encyclopedias encyclopedias) {
		encyclopediasDao.edit(encyclopedias);
	}

	public Encyclopedias findById(Long id) {
		return encyclopediasDao.findById(id);
	}

	public void del(Long id) {
		encyclopediasDao.del(id);
	}

}
