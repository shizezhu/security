package cn.szz.sdk.exception;

/**
 * 自定义异常
 * 
 * @author Shi Zezhu
 * @date 2017年7月25日 下午9:13:36
 */
public class SecuritySdkException extends RuntimeException {

	private static final long serialVersionUID = 3054458026076308895L;
	
	public SecuritySdkException(String message) {
		super(message);
	}
	
	public SecuritySdkException(String message, Throwable cause) {
		super(message, cause);
	}
}
