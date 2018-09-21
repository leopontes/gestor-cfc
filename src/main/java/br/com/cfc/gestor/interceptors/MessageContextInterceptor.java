package br.com.cfc.gestor.interceptors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.cfc.gestor.utils.MessageContext;

@Component
public class MessageContextInterceptor extends HandlerInterceptorAdapter{
	
	@Resource
	private MessageContext messageContext;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		if(messageContext != null && modelAndView != null) {
			modelAndView.addObject("message", messageContext);
		}
	}

}
