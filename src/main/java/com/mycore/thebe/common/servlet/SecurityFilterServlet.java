package com.mycore.thebe.common.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycore.thebe.common.web.HttpUtils;
import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.User;

/**
 * Custom servlet class to do security filter
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public class SecurityFilterServlet implements Filter {
	
	public SecurityFilterServlet() {
		super();
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			HttpSession session = request.getSession(false);
			
			String contextUrl = HttpUtils.getContextUrl(request);
			String requestUrl = HttpUtils.getUrl(request);
//			String queryUrl = HttpUtils.getQueryPath(request);
			
			String loginURL = contextUrl + "/login";
			String redirectUrl = contextUrl + "/accessDenied";
			
			User user = null;

			boolean isAuthorized = false;
			boolean isLogin = false;
			boolean isRedirect = false;
			
			if (session != null) {
				user = (User) session.getAttribute("sessionUser");
				if (user != null) {
					isAuthorized = true;
					
					boolean isAllowed = false;
					if (session.getAttribute("availableLinks") != null) {
						List<RoleMenu> links = (List<RoleMenu>) session.getAttribute("availableLinks");
						List<String> allowedLinks = new ArrayList<String>();
						for (RoleMenu link : links) {
							allowedLinks.add(link.getMenuLink());
						}
						
						if (allowedLinks != null && allowedLinks.size() > 0) {
							for (String link : allowedLinks) {
								if (requestUrl.equalsIgnoreCase(link.trim())) {
									isAllowed = true;
									break;
								}
							}
						}
						
						if (!isAllowed) {
							response.sendRedirect(redirectUrl);
							isRedirect = true;
							isAuthorized = false;
						}
					}
					
				} else {
					if (requestUrl.equals("/doLogin")) {
						isAuthorized = true;
					}
				}
			} else {
				if (requestUrl.equals("/doLogin")) {
					isAuthorized = true;
				}
			}
			
			if (isAuthorized || isLogin) {
				chain.doFilter(request, response);
			} else {
				if (request.getRequestURI().equals(loginURL)) {
					chain.doFilter(request, response);
				} else {
					if (!isRedirect) {
						response.sendRedirect(loginURL);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
