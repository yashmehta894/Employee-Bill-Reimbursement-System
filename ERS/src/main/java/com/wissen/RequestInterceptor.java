package com.wissen;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wissen.model.Token;
import com.wissen.repository.TokenRepository;

@Component
@CrossOrigin(origins = "*")
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	TokenRepository tokenRepository;

	// @ExceptionHandler(Exception.class)
	// public void handleException(HttpServletResponse response) throws Exception {
	//
	// ErrorHandler errorHandler = new ErrorHandler();
	// errorHandler.setMessage("Not Found");
	// errorHandler.setStatusCode("404");
	// response.setStatus(404);
	// response.setHeader("Message", "Requested Resoiurce not found");
	// response.sendError(404, "Requested resource not found");
	// // return errorHandler;
	// }

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("In Interceptor");
		response.setHeader("access-control-allow-origin", "*");
		response.setHeader("access-control-allow-credentials", "true");
		response.setHeader("access-control-allow-methods", "GET,POST,PUT,HEAD");
		// response.setHeader("access-control-allow-headers",
		// "access-control-allow-headers,origin,accept,x-requested-with,content-type,access-control-request-method,access-control-request-headers,auth");
		//
		response.setHeader("access-control-allow-headers", "token,id,password,auth,content-type");
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {

			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			// System.out.println(key + " " + value);
			if (key.equals("auth") && value.equals("true"))
				return true;
		}
		// System.out.println("Before here " + request.getHeader("auth"));
		// if
		// (request.getHeader("access-control-request-headers").split(",")[0].equals("auth"))
		// {
		// System.out.println("Sent forward");
		// return true;
		// }
		System.out.println("After while loop");
		// System.out.println(request.getHeader(arg0));
		Token res = tokenRepository.findByAuthToken(request.getHeader("token"));
		System.out.println(res);
		if (res != null) {
			System.out.println("in if");
			return true;
		}
		System.out.println("after if");
		return false;
	}

}
