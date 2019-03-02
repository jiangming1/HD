package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.VideoDao;
import shan.HDHealthManagement.po.Video;
import shan.HDHealthManagement.service.VideoService;

@Service(value="videoService")
public class VideoServiceImpl implements VideoService {
	
	@Resource
	private VideoDao videoDao;
	
	public List<Video> getAll() {
		return videoDao.getAll();
	}

	public List<Video> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return videoDao.getByPage(index, rows);
	}
	
	public List<Video> getAhead(){
		return videoDao.getAhead();
	}

	public void add(Video video) {
		videoDao.add(video);
	}

	public void edit(Video video) {
		videoDao.edit(video);
	}

	public Video findById(Long id) {
		return videoDao.findById(id);
	}

	public void del(Long id) {
		videoDao.del(id);
	}

	public List<Video> getAllByName(String name) {
		name = "%"+name+"%";
		return videoDao.getAllByName(name);
	}

	public List<Video> getPageByName(String name, Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		name = "%"+name+"%";
		return videoDao.getPageByName(name, index, rows);
	}

}
