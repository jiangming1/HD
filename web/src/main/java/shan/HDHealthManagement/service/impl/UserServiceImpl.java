package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.UserDao;
import shan.HDHealthManagement.po.User;
import shan.HDHealthManagement.po.UserRole;
import shan.HDHealthManagement.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public User findByUsername(String userName) {
		return userDao.findByUsername(userName);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public List<User> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return userDao.getByPage(index, rows);
	}

	public void add(User user,String role) {
		userDao.add(user);
		for (String string : role.split(",")) {
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getId());
			userRole.setRoleId(Long.valueOf(string.split(":")[0]));
			userDao.addUserRole(userRole);
		}
	}

	public User findById(Long id) {
		return userDao.findById(id);
	}

	public List<UserRole> findRoleById(Long id) {
		return userDao.findRoleById(id);
	}

	public void del(Long id) {
		userDao.del(id);
	}

	public void edit(User user, String role) {
		userDao.edit(user);
		String userRolesString;
		List<UserRole> list = userDao.findRoleById(user.getId());
		if (list.size() > 0) {
			String[] set1 = new String[list.size()];
			int i = 0;
			for (UserRole userRole : list) {
				set1[i] = userRole.getRoleId().toString();
				i++;
			}
			userRolesString = hashsetdel(set1, role.split(","));
			if (!userRolesString.equals("")) {
				for(String string:userRolesString.split(",")){
					userDao.delUserRole(user.getId(),Long.valueOf(string));
				}
			}
			userRolesString = hashsetdel(role.split(","), set1);
			if (!userRolesString.equals("")) {
				for (String string : userRolesString.split(",")) {
					UserRole userRole = new UserRole();
					userRole.setRoleId(Long.valueOf(string));
					userRole.setUserId(user.getId());
					userDao.addUserRole(userRole);
				}
			}
		} else {
			for (String string : role.split(",")) {
				UserRole userRole = new UserRole();
				userRole.setUserId(user.getId());
				userRole.setRoleId(Long.valueOf(string.split(":")[0]));
				userDao.addUserRole(userRole);
			}
		}
	}
	
	public String hashsetdel(String[] set1, String[] set2) {
		String userRole = "";
		int num;
		for (int i = 0; i < set1.length; i++) {
			num = 0;
			for (int j = 0; j < set2.length; j++) {
				if (set1[i].equals(set2[j])) {
					break;
				}
				num++;
			}
			if (set2.length == num) {
				if (userRole.equals("")) {
					userRole = set1[i];
				} else {
					userRole = userRole + "," + set1[i];
				}
			}
		}
		return userRole;
	}
}
