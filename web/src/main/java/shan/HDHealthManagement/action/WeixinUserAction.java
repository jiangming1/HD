package shan.HDHealthManagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shan.HDHealthManagement.po.Clock;
import shan.HDHealthManagement.po.IntegralRecord;
import shan.HDHealthManagement.po.WeixinUser;
import shan.HDHealthManagement.service.WeixinUserService;
import shan.HDHealthManagement.util.HttpUtil;

@Controller
@RequestMapping(value = "/weixinUser")
public class WeixinUserAction {
	@Resource(name = "weixinUserService")
	private WeixinUserService weixinUserService;
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param response
	 */
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
	
	/**
	 * 积分记录
	 * @param weixinId
	 * @param response
	 */
	@RequestMapping(value="/record.do")
	public void record(@RequestParam("weixinId") Long weixinId,HttpServletResponse response){
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
	
	/**
	 * 本周打卡排名获取
	 */
	@RequestMapping(value="/week.do")
	public void week(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.week();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本月打卡排名获取
	 */
	@RequestMapping(value="/mouth.do")
	public void mouth(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.mouth();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本年打卡排名获取
	 */
	@RequestMapping(value="/year.do")
	public void year(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.year();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本周打卡排名获取
	 */
	@RequestMapping(value="/weekAll.do")
	public void weekAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.weekAll();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本月打卡排名获取
	 */
	@RequestMapping(value="/mouthAll.do")
	public void mouthAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.mouthAll();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 本年打卡排名获取
	 */
	@RequestMapping(value="/yearAll.do")
	public void yearAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<WeixinUser> list= weixinUserService.yearAll();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打卡数据上传
	 */
	@RequestMapping(value="/addClock.do")
	public void addClock(@RequestParam("weixinId")Long weixinId,@RequestParam("motion")Integer motion,@RequestParam("housework")Integer housework,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Clock clock = new Clock();
			clock.setWeixinId(weixinId);
			clock.setMotion(motion);
			clock.setHousework(housework);
			clock.setDate(new Date());
			weixinUserService.addClock(clock);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/login.do")
	public void login(@RequestParam("code")String code,HttpServletResponse response){
		String result = HttpUtil.getHttp(code);
		JSONObject object = new JSONObject(result);
		String errcode = object.getString("errcode");
		if(errcode!=null&&"0".equals(errcode)) {
			String openid = object.getString("openid");
		}
	}
}
