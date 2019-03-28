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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shan.HDHealthManagement.po.Distribution;
import shan.HDHealthManagement.service.DistributionService;

@RequestMapping(value="/distribution")
@Controller
public class DistributionAction {

	@Resource
	private DistributionService distributionService;
	
	@RequestMapping(value="/upload.do")
	@ResponseBody
	public void upload(@RequestParam(value="file",required=false) CommonsMultipartFile file,
            HttpServletRequest request,HttpServletResponse response){
		if (!file.isEmpty()) {
            try {
            	distributionService.add(file);
        		PrintWriter out = response.getWriter();
                JSONObject object = new JSONObject();
    			object.put("code", 0);
    			object.put("msg", "");
    			out.write(object.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	/**
	 * 查询所有
	 * @param response
	 */
	@RequestMapping(value="/getAll.do")
	public void getAll(HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Distribution> list= distributionService.getAll();
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
