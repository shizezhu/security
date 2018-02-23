package cn.szz.security.exception;

import cn.szz.security.pojo.Result;
import cn.szz.security.utils.ErrorCode;

/**
 * 自定义异常
 * 
 * @author Shi Zezhu
 * @date 2017年7月25日 下午9:13:36
 */
public class ResultException extends RuntimeException {

	private static final long serialVersionUID = 3054458026076308895L;
	
	private Result result;
	
	public ResultException(Result result) {
		this.result = result;
	}
	
	public ResultException(int code) {
		this.result = new Result(code);
	}
	
	public ResultException(int code, String msg) {
		this.result = new Result(code, msg);
	}
	
	public ResultException(ErrorCode code) {
		this.result = new Result(code);
	}
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultException [result=" + result + "]";
	}
}
