package shan.HDHealthManagement.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static String getHttp(String code) {
        try {
        	String path = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code="+code+"&grant_type=authorization_code";
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
//          conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
           // out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
           // out.print(data);
            //缓冲数据
          //  out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            StringBuffer buffer = new StringBuffer(); 
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
               buffer.append(str);
            }
            //关闭流
            is.close();
            conn.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
