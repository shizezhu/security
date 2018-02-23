package cn.szz.sdk.utils;

/**
 * 日志类型
 *
 * @author Shi Zezhu
 * @date 2018年2月3日 下午2:46:35
 *
 */
public enum LogType {
	
	LOGIN(1),
	SAVE(2),
	UPDATE(3),
	DELETE(4),
	SELECT(5);
	
	private int type;

	private LogType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
