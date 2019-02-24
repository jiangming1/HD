package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.WeixinUserDao;
import shan.HDHealthManagement.po.WeixinUser;
import shan.HDHealthManagement.service.WeixinUserService;

@Service(value="weixinUserService")
public class WeixinUserServiceImpl implements WeixinUserService {
	@Resource
	private WeixinUserDao weixinUserDao;

	public List<WeixinUser> getAll() {
		return weixinUserDao.getAll();
	}

	public List<WeixinUser> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return weixinUserDao.getByPage(index, rows);
	}

}
