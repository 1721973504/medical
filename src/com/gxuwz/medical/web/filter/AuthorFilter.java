package com.gxuwz.medical.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 
 * @author 演示
 *
 */
public class AuthorFilter implements Filter {

	public void destroy() {}

	public void init(FilterConfig config) throws ServletException {
		

	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	    
		chain.doFilter(request, response);

	}


}
