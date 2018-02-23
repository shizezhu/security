package cn.szz.security.utils;

public enum ErrorCode {

	SUCCESS(0, "成功"),
	ERROR(1, "异常"),
	PROPERTIES_ERROR(2, "加载配置文件异常"),
	NO_LOGIN(3, "您还没有登陆或登陆已过期"),
	NO_PER(4, "您没有权限,请联系管理员授权"),
	
	PARAM_EMPTY(11, "参数不能为空"),
	REG_NULL(12, "正则参数为NULL"),
	
	ID_EMPTY(101, "ID不能为空"),
	ID_ERROR(102, "ID不正确"),
	ST_EMPTY(103, "状态不能为空"),
	ST_ERROR(104, "状态不正确"),
	PARENT_ID_EMPTY(105, "父级ID不能为空"),
	PARENT_ID_ERROR(106, "父级ID不正确"),
	PARENT_NULL(107, "父级不能为null"),
	PARENT_NOT_EXISTENCE(108, "父级不存在"),
	PARENT_IS_MYSELF(109, "父级不能是自己"),
	PARENT_IS_CHILDREN(110, "父级不能是自己的子级"),
	
	EM_ADDRESSEE_EMPTY(201, "没有收件人"),
	EM_SUBJECT_EMPTY(202, "主题不能为空"),
	EM_CONTENT_EMPTY(203, "内容不能为空"),
	EM_SEND_ERROR(204, "邮件发送异常"),
	LOCAL_PROJECT_ID_ERROR(205, "本项目ID配置错误"),
	REMOTE_MOTHED_ERROR(206, "调用远程接口异常"),
	
	U_NULL(1001, "用户为NULL"),
	U_NOT_EXISTENCE(1002, "用户不存在"),
	U_UN_EMPTY(1003, "用户名不能为空"),
	U_UN_FORMAT(1004, "用户名必须为2-18位，支持字母、数字、“_”、”-“"),
	U_UN_NOT_EXISTENCE(1005, "用户名不存在"),
	U_UN_EXISTENCE(1006, "用户名已存在"),
	U_PW_EMPTY(1007, "密码不能为空"),
	U_PW_ERROR(1008, "密码不正确"),
	U_OPW_EMPTY(1009, "旧密码不能为空"),
	U_OPW_ERROR(1010, "旧密码不正确"),
	U_NPW_EMPTY(1011, "新密码不能为空"),
	U_PW_FORMAT(1012, "密码必须为6-18位，支持字母、数字、“*”"),
	U_NPW_FORMAT(1013, "新密码必须为6-18位，支持字母、数字、“*”"),
	U_PH_EMPTY(1014, "手机号不能为空"),
	U_PH_ERROR(1015, "手机号格式不正确"),
	U_PH_NOT_EXISTENCE(1016, "手机号不存在"),
	U_PH_EXISTENCE(1017, "手机号已存在"),
	U_EM_EMPTY(1018, "邮箱不能为空"),
	U_EM_ERROR(1019, "邮箱格式不正确"),
	U_EM_NOT_EXISTENCE(1020, "邮箱不存在"),
	U_EM_EXISTENCE(1021, "邮箱已存在"),
	U_RN_EMPTY(1022, "真实姓名不能为空"),
	U_RN_FORMAT(1023, "真实姓名必须为2-10位,支持中文、字母、数字、“_”、”-“"),
	U_RN_EXISTENCE(1024, "真实姓名已存在"),
	U_LO_INVALID(1025, "登陆失效,请重新登陆"),
	U_LO_DISABLE(1026, "您的账户已被禁用,请联系管理员"),
	
	R_NULL(1101, "角色为NULL"),
	R_NOT_EXISTENCE(1102, "角色不存在"),
	R_NA_EMPTY(1003, "角色名称不能为空"),
	R_NA_FORMAT(1104, "角色名称必须为1-15位,支持中文、字母、数字、“_”、”-“"),
	R_NA_EXISTENCE(1105, "角色名称已存在"),
	
	UR_NULL(1201, "用户与角色关联关系为NULL"),
	UR_NOT_EXISTENCE(1202, "用户与角色关联关系不存在"),
	UR_EXISTENCE(1203, "用户与角色关联关系已存在"),
	
	ME_NULL(1301, "菜单为NULL"),
	ME_NOT_EXISTENCE(1302, "菜单不存在"),
	ME_NA_EXISTENCE(1303, "菜单名称已存在"),
	ME_NA_EMPTY(1304, "菜单名称不能为空"),
	ME_TY_ERROR(1305, "菜单类型不正确"),
	ME_PA_TY_DIRECTORY(1306, "父级必须为目录"),
	ME_PA_TY_DIRECTORY_OR_VIEW(1307, "父级必须为目录或页面"),
	ME_PA_TY_VIEW_OR_BUTTON(1308, "父级必须为页面或按钮"),
	ME_TY_NOT_EXISTENCE(1309, "菜单类型不存在"),
	ME_UR_EMPTY(1310, "菜单URI不能为空"),
	ME_UR_ERROR(1312, "菜单URLI不正确"),
	ME_SO_ERROR(1313, "菜单排序不正确"),
	ME_AU_EMPTY(1314, "菜单授权标识不能为空"),
	ME_AU_EXISTENCE(1315, "菜单授权标识已存在"),
	ME_TY_NO_UPDATE(1316, "存在子级,不能修改类型"),
	ME_CANNOT_ADD_DIRECTORY(1317, "该目录下不能再添加目录了"),
	
	PR_NULL(1401, "项目为NULL"),
	PR_NOT_EXISTENCE(1402, "项目不存在"),
	PR_ID_ERROR(1403, "项目ID不正确"),
	PR_NA_EMPTY(1404, "项目名称不能为空"),
	PR_NA_EXISTENCE(1405, "项目名称已存在"),
	PR_NA_FORMAT(1106, "项目名称必须为1-15位,支持中文、字母、数字、“_”、”-“"),
	PR_PUR_EMPTY(1407, "项目URL不能为空"),
	PR_PUR_ERROR(1408, "项目URL不正确"),
	PR_PRUR_EMPTY(1409, "刷新权限URL不能为空"),
	PR_PRUR_ERROR(1410, "刷新权限URL不正确"),
	;
	
	private int code;
	private String msg;

	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
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
}
