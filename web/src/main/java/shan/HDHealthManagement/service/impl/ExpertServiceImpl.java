package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.ExpertDao;
import shan.HDHealthManagement.po.Expert;
import shan.HDHealthManagement.service.ExpertService;

@Service(value="expertService")
public class ExpertServiceImpl implements ExpertService {
	@Resource 
	private ExpertDao expertDao;
	
	public List<Expert> getAll() {
		return expertDao.getAll();
	}

	public List<Expert> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return expertDao.getByPage(index, rows);
	}

	public void add(Expert expert) {
		expertDao.add(expert);
	}

	public void edit(Expert expert) {
		expertDao.edit(expert);
	}

	public Expert findById(Long id) {
		return expertDao.findById(id);
	}

	public void del(Long id) {
		expertDao.del(id);
	}

}
