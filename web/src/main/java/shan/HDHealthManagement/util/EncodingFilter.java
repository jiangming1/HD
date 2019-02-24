package shan.HDHealthManagement.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * 字符编码过滤器
 * @author WJ
 *
 */
public class EncodingFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private FilterConfig filterConfig;
	private String trargetEncoding = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.trargetEncoding = this.filterConfig.getInitParameter("encoding");

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) {
		try {
			request.setCharacterEncoding(trargetEncoding);
			response.setCharacterEncoding(trargetEncoding);
	/*		Map<String , String[] > map= request.getParameterMap();
			for (String iterable_element : map.keySet()) {
				System.out.println(iterable_element);
			}
			for (String[] iterable_element : map.values()) {
				for (String string : iterable_element) {
					System.out.println(string);
				}
			}*/
			filterChain.doFilter(request, response);
		} catch (ServletException sx) {
			filterConfig.getServletContext().log(sx.getMessage());

		} catch (IOException iox) {
			filterConfig.getServletContext().log(iox.getMessage());
		}

	}

	public void destroy() {
		// 清空资源
		this.filterConfig = null;
		this.trargetEncoding = null;

	}
}
