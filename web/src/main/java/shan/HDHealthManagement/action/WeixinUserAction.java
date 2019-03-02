package shan.HDHealthManagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shan.HDHealthManagement.po.IntegralRecord;
import shan.HDHealthManagement.po.WeixinUser;
import shan.HDHealthManagement.service.WeixinUserService;

@Controller
@RequestMapping(value = "/weixinUser")
public class WeixinUserAction {
	@Resource(name = "weixinUserService")
	private WeixinUserService weixinUserService;
	
	@RequestMapping(value="/getAllByPage")
	public void getAllByPage(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = weixinUserService.getAll().size();
			List<WeixinUser> list= weixinUserService.getByPage(page, rows);
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
	
	@RequestMapping(value="/record.do")
	public void getAllByPage(@RequestParam("weixinId") Long weixinId,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<IntegralRecord> list= weixinUserService.record(weixinId);
			JSONArray json = JSONArray.fromObject(list);
			JSONObject object = new JSONObject();
			object.put("data", json);
			object.put("code", 0);
			object.put("msg", "");
			out.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
