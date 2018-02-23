package cn.szz.security.pojo.vo;

import cn.szz.security.pojo.Result;
import cn.szz.security.utils.ErrorCode;

/**
 * ajax数据返回封装
 *
 * @author Shi Zezhu
 * @date 2018年2月5日 上午11:30:47
 *
 */
public class AjaxResult extends Result {

	private static final long serialVersionUID = -5052761507270848804L;
	
	private Object data;
	
	public AjaxResult() {
		super();
	}
	
	public AjaxResult(Result result) {
		super(result);
	}
	
	public AjaxResult(int code) {
		super(code);
	}
	
	public AjaxResult(int code, String msg) {
		super(code, msg);
	}
	
	public AjaxResult(int code, String msg, Object data) {
		super(code, msg);
		this.data = data;
	}
	
	public AjaxResult(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public AjaxResult(ErrorCode errorCode, Object data) {
		super(errorCode);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AlipayResult [super=" + super.toString() + "data=" + data + "]";
	}
}
