package cn.szz.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.szz.security.exception.ResultException;
import cn.szz.security.pojo.Result;
import cn.szz.security.utils.ErrorCode;
import cn.szz.security.utils.HttpUtils;

/**
 * 声明式异常处理
 * 
 * @author Shi Zezhu
 * @date 2017年8月7日 下午5:34:14
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger logger = Logger.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(value = ResultException.class)
	@ResponseBody
	public Result resultExceptionHandler(ResultException resultException) {
		return resultException.getResult();
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		try {
			logger.error("发生了异常", ex);
			if (HttpUtils.isAjax(request)) {
				HttpUtils.sendData(response, ErrorCode.ERROR);
			} else {
				response.sendRedirect(request.getContextPath() + "/500");
			}
		} catch (Exception e) {
			logger.error("处理Exception发生了异常", e);
		}
	}
}
