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
	
	public List<Encyclopedias> getAhead(){
		return encyclopediasDao.getAhead();
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

	public List<Encyclopedias> getAllByName(String name) {
		name = "%"+name+"%";
		return encyclopediasDao.getAllByName(name);
	}

	public List<Encyclopedias> getPageByName(Integer page, Integer rows,String name) {
		Integer index = (page-1)*rows;
		name = "%"+name+"%";
		return encyclopediasDao.getPageByName(name, index, rows);
	}

}
