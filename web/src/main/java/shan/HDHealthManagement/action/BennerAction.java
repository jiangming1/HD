package shan.HDHealthManagement.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shan.HDHealthManagement.po.Benner;
import shan.HDHealthManagement.service.BennerService;

@Controller
@RequestMapping(value = "/benner")
public class BennerAction {
	@Resource(name = "bennerService")
	private BennerService bennerService;
	
	@RequestMapping(value="/getAllByPage")
	public void getAllByPage(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = bennerService.getAll().size();
			List<Benner> list= bennerService.getByPage(page, rows);
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
	public void add(@RequestParam("fileUrl") CommonsMultipartFile file,
            HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			if (!file.isEmpty()) {
	            String type = file.getOriginalFilename().substring(
	                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
	            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
	            String path = request.getSession().getServletContext()
	                    .getRealPath("/bennerFile/" + filename);// 存放位置
	            File destFile = new File(path);
	            try {
	                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
	                Benner benner = new Benner();
	                benner.setName(request.getParameter("name"));
	                benner.setUrl(request.getParameter("url"));
	                benner.setFile("/bennerFile/" + filename);
	                bennerService.add(benner);
	                out.write("suc");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value="/editJsp.do")
	public String editJsp(@RequestParam("id") Long id,HttpServletRequest request){
		Benner benner = bennerService.findById(id);
		request.setAttribute("benner", benner);
		return "benner/edit";
	}
	
	@RequestMapping(value="/edit.do")
	@ResponseBody
	public void edit(@RequestParam(value="fileUrl",required=false) CommonsMultipartFile file,
            HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Benner benner = bennerService.findById(Long.valueOf(request.getParameter("id")));
			if (!file.isEmpty()) {
	            String type = file.getOriginalFilename().substring(
	                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
	            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
	            String path = request.getSession().getServletContext()
	                    .getRealPath("/bennerFile/" + filename);// 存放位置
	            File destFile = new File(path);
	            try {
	                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
	                benner.setFile("/bennerFile/" + filename);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
            benner.setName(request.getParameter("name"));
            benner.setUrl(request.getParameter("url"));
            bennerService.edit(benner);
            out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/del.do")
	@ResponseBody
	public void del(@RequestParam("id") Long id,HttpServletResponse response){
		try {
			bennerService.del(id);
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
					bennerService.del(Long.valueOf(string));
				}
			}
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
