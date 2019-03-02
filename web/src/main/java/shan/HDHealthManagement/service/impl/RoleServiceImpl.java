package shan.HDHealthManagement.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import shan.HDHealthManagement.Mapper.RoleDao;
import shan.HDHealthManagement.po.Jurisdiction;
import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.po.RoleJurisdiction;
import shan.HDHealthManagement.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	public List<Role> getByPage(Integer page, Integer rows) {
		Integer index = (page-1)*rows;
		return roleDao.getByPage(index, rows);
	}

	public void add(Role role) {
		roleDao.add(role);
	}

	public void edit(Role role) {
		roleDao.edit(role);
	}

	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	public void del(Long id) {
		roleDao.del(id);
	}

	public String getAllJurisdictionById(Long roleId) {
		List<RoleJurisdiction> roleJurisdictions = roleDao.findRoleJurisdictions(roleId);
		List<String> roleSet = new ArrayList<String>();
		for (RoleJurisdiction roleJurisdiction : roleJurisdictions) {
			roleSet.add(roleJurisdiction.getJurisdictionId().toString());
		}
		List<Jurisdiction> list = roleDao.getJurisdictionParent();
		JSONArray array = new JSONArray();
		for (Jurisdiction jurisdiction : list) {
			JSONObject object = new JSONObject();
			object.put("title", jurisdiction.getZhName());
			object.put("value", jurisdiction.getId());
			List<Jurisdiction> childList = roleDao.getJurisdictionByParentId(jurisdiction.getId());
			if(childList!=null && childList.size()!=0){
				JSONArray childArray = new JSONArray();
				for (Jurisdiction child : childList) {
					JSONObject childObject = new JSONObject();
					childObject.put("title", child.getZhName());
					childObject.put("value", child.getId());
					if(roleSet.indexOf(child.getId().toString())!=-1){
						childObject.put("checked", true);
					}
					childArray.add(childObject);
				}
				object.put("data", childArray);
			}
			array.add(object);
		}
		return array.toString();
	}

	public List<RoleJurisdiction> findRoleJurisdictions(Long roleId) {
		return roleDao.findRoleJurisdictions(roleId);
	}

	public Jurisdiction findJurisdictionById(Long id){
		return roleDao.findJurisdictionById(id);
	}

	public void editJurisdiction(String treeChecked, Long id) {
		List<RoleJurisdiction> list = roleDao.findRoleJurisdictions(id);
		Set<String> old = new HashSet<String>();
		Set<String> news = new HashSet<String>();
		for (RoleJurisdiction roleJurisdiction : list) {
			old.add(roleJurisdiction.getJurisdictionId().toString());
		}
		for(String string:treeChecked.split(",")){
			if(!"".equals(string)){
				news.add(string);
			}
		}
		for (RoleJurisdiction roleJurisdiction : list) {
			if(news.add(roleJurisdiction.getJurisdictionId().toString())){
				roleDao.delRoleJurisdiction(id, roleJurisdiction.getJurisdictionId());
			}
		}
		for(String string:treeChecked.split(",")){
			if(!"".equals(string)){
				if(old.add(string)){
					RoleJurisdiction roleJurisdiction = new RoleJurisdiction();
					roleJurisdiction.setRoleId(id);
					roleJurisdiction.setJurisdictionId(Long.valueOf(string));
					roleDao.addRoleJurisdiction(roleJurisdiction);
				}
			}
		}
	}
}
