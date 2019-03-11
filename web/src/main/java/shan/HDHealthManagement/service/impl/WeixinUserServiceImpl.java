package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shan.HDHealthManagement.Mapper.WeixinUserDao;
import shan.HDHealthManagement.po.Clock;
import shan.HDHealthManagement.po.IntegralRecord;
import shan.HDHealthManagement.po.WeixinUser;
import shan.HDHealthManagement.service.WeixinUserService;

@Service(value="weixinUserService")
@Transactional
public class WeixinUserServiceImpl implements WeixinUserService {
	@Resource
	private WeixinUserDao weixinUserDao;

	@Transactional(readOnly = true)
	public List<WeixinUser> getAll() {
		return weixinUserDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return weixinUserDao.getByPage(index, rows);
	}
	
	@Transactional(readOnly = true)
	public WeixinUser findById(Long id) {
		return weixinUserDao.findById(id);
	}
	
	public void add(WeixinUser weixinUser){
		weixinUserDao.add(weixinUser);
	}
	
	public void edit(WeixinUser weixinUser){
		weixinUserDao.edit(weixinUser);
	}

	@Transactional(readOnly = true)
	public List<IntegralRecord> record(Long weixinId) {
		return weixinUserDao.record(weixinId);
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> week() {
		return weixinUserDao.week();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> mouth() {
		return weixinUserDao.mouth();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> year() {
		return weixinUserDao.year();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> weekAll() {
		return weixinUserDao.week();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> mouthAll() {
		return weixinUserDao.mouth();
	}

	@Transactional(readOnly = true)
	public List<WeixinUser> yearAll() {
		return weixinUserDao.year();
	}

	public void addClock(Clock clock) {
		weixinUserDao.addClock(clock);
		WeixinUser weixinUser = weixinUserDao.findById(clock.getWeixinId());
		weixinUser.setClock(weixinUser.getClock()+clock.getMotion()+clock.getHousework());
		weixinUser.setWeek(weixinUser.getWeek()+clock.getMotion()+clock.getHousework());
		weixinUser.setMouth(weixinUser.getMouth()+clock.getMotion()+clock.getHousework());
		weixinUser.setYear(weixinUser.getYear()+clock.getMotion()+clock.getHousework());
		weixinUserDao.edit(weixinUser);
	}

}
