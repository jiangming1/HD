package shan.HDHealthManagement.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shan.HDHealthManagement.po.Role;
import shan.HDHealthManagement.po.User;
import shan.HDHealthManagement.po.UserRole;
import shan.HDHealthManagement.service.RoleService;
import shan.HDHealthManagement.service.UserService;
import shan.HDHealthManagement.util.Verification;

@Controller
@RequestMapping(value = "/user")
public class UserAction {
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "roleService")
	private RoleService roleService;

	@RequestMapping(value = "/getVerification.do")
	public void getVerification(HttpServletRequest request,
			HttpServletResponse response) {
		String code = Verification.verificationCode();
		HttpSession session = request.getSession();
		session.setAttribute("login_verification", code);
		BufferedImage image = Verification.imageCode(code);
		try {
			ServletOutputStream responseOutputStream = response
					.getOutputStream();
			ImageIO.write(image, "JPEG", responseOutputStream);
			responseOutputStream.flush();
			responseOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login.do")
	@ResponseBody
	public void login(@RequestParam("userName") String userName,@RequestParam("verification") String verification,
			@RequestParam("password") String password,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String code = String.valueOf(session.getAttribute("login_verification"));
			verification = verification.toLowerCase();
			code = code.toLowerCase();
			if(code.equals(verification)){
				UsernamePasswordToken token = new UsernamePasswordToken(userName,
						password);
				Subject subject = SecurityUtils.getSubject();
				try {
					subject.login(token);
				} catch (IncorrectCredentialsException ice) {
					// 捕获密码错误异常
					out.write("1");
				} catch (UnknownAccountException uae) {
					// 捕获未知用户名异常
					out.write("1");
				} catch (ExcessiveAttemptsException eae) {
					// 捕获错误登录过多的异常
					out.write("2");
				}
				User user = userService.findByUsername(userName);
				subject.getSession().setAttribute("user", user);
				out.write("3");
			}else{
				out.write("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getAllByPage.do")
	@ResponseBody
	public void getAll(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,HttpServletResponse response){ 
		try {
			PrintWriter out = response.getWriter();
			Integer count = userService.getAll().size();
			List<User> list= userService.getByPage(page, rows);
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
	
	@RequestMapping(value="/add.do")
	@ResponseBody
	public void add(@RequestParam("role") String role,User user,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			userService.add(user,role);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/edit.do")
	@ResponseBody
	public void edit(@RequestParam("role") String role,User user,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			userService.edit(user,role);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/del.do")
	@ResponseBody
	public void del(@RequestParam("id") Long id,HttpServletResponse response){
		try {
			userService.del(id);
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/dels.do")
	@ResponseBody
	public void dels(@RequestParam("ids") String ids,HttpServletResponse response){
		try {
			for(String string : ids.split(",")){
				if(!string.equals("")){
					userService.del(Long.valueOf(string));
				}
			}
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/addjsp.do")
	public String addjsp(HttpServletRequest request,HttpServletResponse response){
		List<Role> roles = roleService.getAllRole();
		request.setAttribute("roles",roles);
		return "user/add";
	}
	@RequestMapping(value="/editjsp.do")
	public String editjsp(@RequestParam("id") Long id,HttpServletRequest request,HttpServletResponse response){
		List<Role> roles = roleService.getAllRole();
		request.setAttribute("roles",roles);
		User user = userService.findById(id);
		List<UserRole> list = userService.findRoleById(id);
		List<Long> list2 = new ArrayList<Long>();
		for (UserRole userRole : list) {
			list2.add(userRole.getRoleId());
		}
		request.setAttribute("user", user);
		request.setAttribute("role", list2);
		return "user/edit";
	}
}
