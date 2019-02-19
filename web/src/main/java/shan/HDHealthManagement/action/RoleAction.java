package shan.HDHealthManagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.service.RoleService;

@Controller
@RequestMapping(value = "/role")
public class RoleAction {
	@Resource(name = "roleService")
	private RoleService roleService;

	@RequestMapping(value = "/getAllByPage.do")
	public void getAllByPage(@RequestParam("page") Integer page,
			@RequestParam("limit") Integer rows, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			Integer count = roleService.getAllRole().size();
			List<Role> list = roleService.getByPage(page, rows);
			JSONArray json = JSONArray.fromObject(list);
			JSONObject object = new JSONObject();
			object.put("data", json);
			object.put("count", count);
			object.put("code", 0);
			object.put("msg", "");
			out.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/add.do")
	@ResponseBody
	public void add(Role role,@RequestParam("jurisdiction")String jurisdiction,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			roleService.add(role,jurisdiction);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/addJsp.do")
	public String addJsp(HttpServletRequest request) {
		request.setAttribute("data", roleService.getAllJurisdiction());
		return "role/add";
	}
	
	@RequestMapping(value = "/editJsp.do")
	public String editJsp(@RequestParam("id") Long id,
			HttpServletRequest request) {
		request.setAttribute("role", roleService.findById(id));
		request.setAttribute("data", roleService.getAllJurisdictionById(id));
		return "role/edit";
	}

	@RequestMapping(value = "/edit.do")
	@ResponseBody
	public void edit(Role role,@RequestParam("jurisdiction")String jurisdiction,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			roleService.edit(role,jurisdiction);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/del.do")
	@ResponseBody
	public void del(@RequestParam("id") Long id, HttpServletResponse response) {
		try {
			roleService.del(id);
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/dels.do")
	@ResponseBody
	public void dels(@RequestParam("ids") String ids,
			HttpServletResponse response) {
		try {
			for (String string : ids.split(",")) {
				if (!string.equals("")) {
					roleService.del(Long.valueOf(string));
				}
			}
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
