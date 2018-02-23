说明
本工程仅供学习使用，请勿用于商用。

本工程分为3个项目：
1、	cas-------------------------单点登录
2、	security------------------主要项目，用来管理各个子项目，子项目都基于此项目运行
3、	security-sdk-------------封装了shiro代码，可打包成jar包，用于子项目快速接入security项目，快速搭建用户认证模块和权限模块
项目描述
1．cas
	cas4.2.7版本，此项目部署完成后一般情况下是不需要再进行维护了，对源代码稍微做了一些改动，改动内容如下：
		1、原本用户信息是放在配置文件里，改为从数据库读取，密码进行了MD5盐值加密验证。
		2、增加了一个超级用户功能，超级用户是不需要分配权限信息，默认是拥有所有权限，可以在系统刚刚部署数据库还没有任何数据，和应急时使用。
2．security
	开发工具：
		1、JDK8
		2、ecplise4.5.2
		3、tomcat8.0
		4、mysql5.5
		5、maven3.3.9
	后端框架：
		1、spring4.3.14
		2、springMVC4.3.14
		3、mybatis1.3.1
		4、shiro1.2.6
		5、hessian4.0.33
		6、ehcache2.6.11
	前端框架：
		1、H+
	主要模块：
		1、项目管理
			a)新增
			b)修改
			c)删除
		2、角色管理
			a)新增
			b)修改
			c)删除
		3、用户管理
			a)新增
			b)修改
			c)删除
			d)重置密码
		4、菜单管理
			a)新增
			b)修改
			c)删除
		5、日志管理

3．security-sdk
	开发工具：
		1、JDK7
		2、ecplise4.5.2
		3、maven3.3.9
	后端框架：
		1、spring4.3.14
		2、shiro1.2.6
		3、hessian4.0.33
		4、ehcache2.6.11

	可打包成jar文件导入到其它系统中使用，主要提供以下服务：
		1、用户信息认证
		2、用户权限认证
		3、远程获取用户信息
		4、远程获取权限信息
		5、操作日志保存接口
		6、远程刷新项目权限信息
		7、远程刷新用户授权信息
