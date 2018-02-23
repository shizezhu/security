package cn.szz.security.pojo;

import java.io.Serializable;
import java.util.Objects;

import cn.szz.security.utils.ErrorCode;


/**
 * result基类
 *
 * @author Shi Zezhu
 * @date 2017年12月4日 下午4:32:05
 *
 */
public class Result implements Serializable {
	
	private static final long serialVersionUID = 1381695547116163922L;

	private int code;// 状态码
	
	private String msg;// 消息
	
	public Result() {
		super();
	}
	
	public Result(Result result) {
		super();
		this.code = result.getCode();
		this.msg = result.getMsg();
	}
	
	public Result(int code) {
		super();
		this.code = code;
	}
	
	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Result(ErrorCode errorCode) {
		super();
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
	}
	
	public boolean isSuccess() {
		return Objects.equals(code, 0) ? true : false;
	}
	
	public boolean isError() {
		return Objects.equals(code, 0) ? false : true;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + "]";
	}
}
