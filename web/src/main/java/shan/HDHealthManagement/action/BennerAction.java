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
	
	/**
	 * 分页显示
	 * @param page
	 * @param rows
	 * @param response
	 */
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
	
	/**
	 * 获取所有
	 * @param response
	 */
	@RequestMapping(value="/getAll.do")
	@ResponseBody
	public void getAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Benner> list = bennerService.getAll();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加
	 * @param benner
	 * @param response
	 */
	@RequestMapping(value="/add.do")
	@ResponseBody
	public void add(Benner benner,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			bennerService.add(benner);
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 修改页面
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editJsp.do")
	public String editJsp(@RequestParam("id") Long id,HttpServletRequest request){
		Benner benner = bennerService.findById(id);
		request.setAttribute("benner", benner);
		return "benner/edit";
	}
	
	/**
	 * 修改
	 * @param benner
	 * @param response
	 */
	@RequestMapping(value="/edit.do")
	@ResponseBody
	public void edit(Benner benner,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
            bennerService.edit(benner);
            out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @param response
	 */
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
	
	/**
	 * 多条删除
	 * @param ids
	 * @param response
	 */
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
	/**
	 * 文件上传
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/upload.do")
	@ResponseBody
	public void upload(@RequestParam(value="file",required=false) CommonsMultipartFile file,
            HttpServletRequest request,HttpServletResponse response){
		if (!file.isEmpty()) {
            String type = file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."));// 取文件格式后缀名
            String filename = System.currentTimeMillis() + type;// 取当前时间戳作为文件名
            String path = request.getSession().getServletContext()
                    .getRealPath("/bennerFile/" + filename);// 存放位置
            File destFile = new File(path);
            try {
        		PrintWriter out = response.getWriter();
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
                JSONObject object = new JSONObject();
    			object.put("data", "bennerFile/" + filename);
    			object.put("code", 0);
    			object.put("msg", "");
    			out.write(object.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
