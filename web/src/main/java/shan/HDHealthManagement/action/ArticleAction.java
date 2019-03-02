package shan.HDHealthManagement.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import shan.HDHealthManagement.po.Article;
import shan.HDHealthManagement.service.ArticleService;

@RequestMapping(value="/article")
@Controller
public class ArticleAction {
	@Resource(name = "articleService")
	private ArticleService articleService;
	
	@RequestMapping(value="/getAllByPage.do")
	public void getAllByPage(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = articleService.getAll().size();
			List<Article> list= articleService.getByPage(page, rows);
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
	
	@RequestMapping(value="/getAllByName")
	public void getAllByName(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,@RequestParam("name") String name,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = articleService.getAllByName(name).size();
			List<Article> list= articleService.getPageByName(name, page, rows);
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
	public void add(Article article,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			articleService.add(article);
            out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value="/del.do")
	@ResponseBody
	public void del(@RequestParam("id") Long id,HttpServletResponse response){
		try {
			articleService.del(id);
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
					articleService.del(Long.valueOf(string));
				}
			}
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/upload.do")
	@ResponseBody
	public void upload(@RequestParam(value="file",required=false) CommonsMultipartFile file,
            HttpServletRequest request,HttpServletResponse response){
		if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
            String path = request.getSession().getServletContext()
                    .getRealPath("/articleFile/" + filename);// 存放位置
            File destFile = new File(path);
            try {
        		PrintWriter out = response.getWriter();
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
                JSONObject object = new JSONObject();
    			object.put("data", "articleFile/" + filename);
    			object.put("code", 0);
    			object.put("msg", "");
    			out.write(object.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	@RequestMapping(value="/down.do")
	public void down(@RequestParam("id") Long id,HttpServletRequest request,HttpServletResponse response){
		try {
			Article article = articleService.findById(id);
			String type = article.getFileUrl().substring(article.getFileUrl().indexOf("."));
			String name =new String(article.getName().getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题
			String path = request.getSession().getServletContext().getRealPath(article.getFileUrl());// 存放位置
			File file = new File(path);
			response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\""+name+type + "\"");  
		    response.addHeader("Content-Length", "" + file.length());  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream()); 
			while(true){
				byte[] bytes = new byte[1024];
				int a = inputStream.read(bytes);
				outputStream.write(bytes);
				if(a<=0){
					break;
				}
			}
			outputStream.flush();  
		    outputStream.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
