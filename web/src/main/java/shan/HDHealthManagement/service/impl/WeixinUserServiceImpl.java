package shan.HDHealthManagement.service.impl;

import java.util.Date;
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
	
	@Transactional(readOnly = true)
	public WeixinUser findByOpenId(String openid) {
		return weixinUserDao.findByOpenId(openid);
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

	public void addClock(Long weixinId,Integer motion,Integer housework) {
		Clock clock = new Clock();
		clock.setWeixinId(weixinId);
		clock.setMotion(motion);
		clock.setHousework(housework);
		clock.setDate(new Date());
		weixinUserDao.addClock(clock);
		WeixinUser weixinUser = weixinUserDao.findById(clock.getWeixinId());
		IntegralRecord record = new IntegralRecord();
		record.setWeixinId(weixinId);
		record.setMemo("运动");
		record.setDate(new Date());
		if(motion==10) {
			weixinUser.setIntegral(weixinUser.getIntegral()+4);
			record.setIntegral(4);
		}else if(motion==20) {
			weixinUser.setIntegral(weixinUser.getIntegral()+6);
			record.setIntegral(6);
		}else if(motion==30) {
			weixinUser.setIntegral(weixinUser.getIntegral()+8);
			record.setIntegral(8);
		}else if(motion==40) {
			weixinUser.setIntegral(weixinUser.getIntegral()+10);
			record.setIntegral(10);
		}
		weixinUserDao.addIntegralRecord(record);
		record = new IntegralRecord();
		record.setWeixinId(weixinId);
		record.setMemo("做家务");
		record.setDate(new Date());
		if(housework==10) {
			weixinUser.setIntegral(weixinUser.getIntegral()+4);
			record.setIntegral(4);
		}else if(housework==20) {
			weixinUser.setIntegral(weixinUser.getIntegral()+6);
			record.setIntegral(6);
		}else if(housework==30) {
			weixinUser.setIntegral(weixinUser.getIntegral()+8);
			record.setIntegral(8);
		}else if(housework==40) {
			weixinUser.setIntegral(weixinUser.getIntegral()+10);
			record.setIntegral(10);
		}
		weixinUserDao.addIntegralRecord(record);
		weixinUser.setClock(weixinUser.getClock()+clock.getMotion()+clock.getHousework());
		weixinUser.setWeek(weixinUser.getWeek()+clock.getMotion()+clock.getHousework());
		weixinUser.setMouth(weixinUser.getMouth()+clock.getMotion()+clock.getHousework());
		weixinUser.setYear(weixinUser.getYear()+clock.getMotion()+clock.getHousework());
		weixinUserDao.edit(weixinUser);
	}

}
