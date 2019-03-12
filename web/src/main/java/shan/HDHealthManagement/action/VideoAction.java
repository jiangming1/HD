package shan.HDHealthManagement.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shan.HDHealthManagement.po.Video;
import shan.HDHealthManagement.service.VideoService;

/**
 * 康复视频请求处理类
 * @author 18732
 *
 */
@Controller
@RequestMapping(value="/video")
public class VideoAction {
	@Resource(name = "videoService")
	private VideoService videoService;
	
	/**
	 * 分页查询所有数据
	 * @param page
	 * @param rows
	 * @param response
	 */
	@RequestMapping(value="/getAllByPage")
	public void getAllByPage(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = videoService.getAll().size();
			List<Video> list= videoService.getByPage(page, rows);
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
	 * 根据名称查询
	 * 分页显示
	 * @param page
	 * @param rows
	 * @param name
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getAllByName")
	public void getAllByName(@RequestParam("page") Integer page,@RequestParam("limit") Integer rows,@RequestParam("name") String name,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Integer count = videoService.getAllByName(name).size();
			List<Video> list= videoService.getPageByName(name, page, rows);
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
	 * 查询前4个数据显示
	 * @param response
	 */
	@RequestMapping(value="/getAhead.do")
	@ResponseBody
	public void getAhead(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Video> list= videoService.getAhead();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有数据显示
	 * @param response
	 */
	@RequestMapping(value="/getAll.do")
	@ResponseBody
	public void getAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Video> list= videoService.getAhead();
			JSONArray json = JSONArray.fromObject(list);
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id查询显示
	 * @param id
	 * @param response
	 */
	@RequestMapping(value="/getById.do")
	@ResponseBody
	public void getById(@RequestParam("id") Long id,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			Video video = videoService.findById(id);
			JSONObject object = JSONObject.fromObject(video);
			out.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加康复视频
	 * @param video
	 * @param response
	 */
	@RequestMapping(value="/add.do")
	@ResponseBody
	public void add(Video video,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
            videoService.add(video);
            out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 修改页面显示
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editJsp.do")
	public String editJsp(@RequestParam("id") Long id,HttpServletRequest request){
		Video video = videoService.findById(id);
		request.setAttribute("video", video);
		return "video/edit";
	}
	/**
	 * 修改康复视频
	 * @param video
	 * @param response
	 */
	@RequestMapping(value="/edit.do")
	@ResponseBody
	public void edit(Video video,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			videoService.edit(video);
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
			videoService.del(id);
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
					videoService.del(Long.valueOf(string));
				}
			}
			PrintWriter out = response.getWriter();
			out.write("suc");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 上传文件
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
                    .getRealPath("/videoFile/" + filename);// 存放位置
            File destFile = new File(path);
            try {
        		PrintWriter out = response.getWriter();
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
                JSONObject object = new JSONObject();
    			object.put("data", "videoFile/" + filename);
    			object.put("code", 0);
    			object.put("msg", "");
    			out.write(object.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
