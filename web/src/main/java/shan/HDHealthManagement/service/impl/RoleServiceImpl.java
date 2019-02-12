package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.RoleDao;
import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}
}
