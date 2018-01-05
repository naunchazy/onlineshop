package com.exercise.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.exercise.domain.User;
import com.exercise.service.UserService;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		Cookie[] cookies=request.getCookies();
		String username=null;
		String password=null;
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("username".equals(cookie.getName())){
					username=cookie.getValue();
				}
				if ("password".equals(cookie.getName())) {
					password = cookie.getValue();
				}
			}
		}
		// 2.去数据库验证
		if (username != null && password != null) {
			UserService service = new UserService();
			User user = null;
			try {
				user = service.findUserService(username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 3.根据结果做相应的业务（存域对象）
			if(user!=null){
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
