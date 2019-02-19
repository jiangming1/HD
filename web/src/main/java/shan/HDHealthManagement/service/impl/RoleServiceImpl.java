package shan.HDHealthManagement.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	public void add(Role role,String jurisdiction) {
		roleDao.add(role);
		for(String string:jurisdiction.split(",")){
			if(!"".equals(string)){
				RoleJurisdiction roleJurisdiction = new RoleJurisdiction();
				roleJurisdiction.setRoleId(role.getId());
				roleJurisdiction.setJurisdictionId(Long.valueOf(string));
				roleDao.addRoleJurisdiction(roleJurisdiction);
			}
		}
	}

	public void edit(Role role,String jurisdiction) {
		roleDao.edit(role);
		List<RoleJurisdiction> roleJurisdictions = roleDao.findRoleJurisdictions(role.getId());
		List<String> list = new ArrayList<String>();
		List<String> newList = new ArrayList<String>();
		for (RoleJurisdiction roleJurisdiction : roleJurisdictions) {
			list.add(roleJurisdiction.getJurisdictionId().toString());
		}
		for(String string:jurisdiction.split(",")){
			if(!"".equals(string)){
				newList.add(string);
			}
		}
		list.removeAll(newList);
		for (String string : list) {
			roleDao.delRoleJurisdiction(role.getId(), Long.valueOf(string));
		}
		list = new ArrayList<String>();
		for (RoleJurisdiction roleJurisdiction : roleJurisdictions) {
			list.add(roleJurisdiction.getJurisdictionId().toString());
		}
		newList.removeAll(list);
		for (String string : newList) {
			RoleJurisdiction roleJurisdiction = new RoleJurisdiction();
			roleJurisdiction.setRoleId(role.getId());
			roleJurisdiction.setJurisdictionId(Long.valueOf(string));
			roleDao.addRoleJurisdiction(roleJurisdiction);
		}
	}

	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	public void del(Long id) {
		roleDao.del(id);
	}

	public String getAllJurisdiction() {
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
					childArray.add(childObject);
				}
				object.put("data", childArray);
			}
			array.add(object);
		}
		return array.toString();
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
}
