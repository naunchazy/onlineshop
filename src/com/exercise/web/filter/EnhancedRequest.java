package com.exercise.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EnhancedRequest extends HttpServletRequestWrapper{
	HttpServletRequest request;
	public EnhancedRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	@Override
	public String getParameter(String name) {
		String namevalue = this.request.getParameter(name);
		System.out.println(namevalue);
		String enc = null;
		if(namevalue!=null){
			try {
				enc = new String(namevalue.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return enc;
	}
}
