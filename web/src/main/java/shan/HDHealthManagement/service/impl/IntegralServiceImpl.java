package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.IntegralDao;
import shan.HDHealthManagement.po.Integral;
import shan.HDHealthManagement.service.IntegralService;

@Service(value="integralService")
public class IntegralServiceImpl implements IntegralService {
	@Resource
	private IntegralDao integralDao;

	public List<Integral> getAll() {
		return integralDao.getAll();
	}

	public List<Integral> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return integralDao.getByPage(index, rows);
	}

}
