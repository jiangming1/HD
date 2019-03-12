package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.BennerDao;
import shan.HDHealthManagement.po.Benner;
import shan.HDHealthManagement.service.BennerService;

@Service(value="bennerService")
public class BennerServiceImpl implements BennerService {
	@Resource
	private BennerDao bennerDao;

	public List<Benner> getAll() {
		return bennerDao.getAll();
	}

	public List<Benner> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return bennerDao.getByPage(index, rows);
	}

	public void add(Benner benner) {
		bennerDao.add(benner);
	}

	public void edit(Benner benner) {
		bennerDao.edit(benner);
	}

	public Benner findById(Long id) {
		return bennerDao.findById(id);
	}

	public void del(Long id) {
		bennerDao.del(id);
	}

}
