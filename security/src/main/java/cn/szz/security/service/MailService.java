package cn.szz.security.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import cn.szz.security.exception.ResultException;

/**
 * 邮件
 * 
 * @author Shi Zezhu
 * @date 2017年7月14日 上午11:22:52
 */
public interface MailService {

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param cc 抄送
	 * @param subject 主题
	 * @param htmlflag 是否HTLM内容
	 * @param content 内容
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年7月14日 下午4:35:50
	 */
	void sendMail(InternetAddress[] to, InternetAddress[] cc, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException;

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param cc 抄送
	 * @param subject 主题
	 * @param content 内容
	 * @param htmlflag 是否HTLM内容
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年7月14日 下午4:35:50
	 */
	void sendMail(String[] to, String[] cc, String subject, String content, boolean htmlflag)
			throws ResultException, MessagingException, IOException;

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param subject 主题
	 * @param htmlflag 是否HTLM内容
	 * @param content 内容
	 * @throws ResultException
	 * @throws MessagingException
	 * @throws IOException
	 * @author Shi Zezhu
	 * @date 2017年7月15日 上午11:31:35
	 */
	void sendMail(List<InternetAddress> to, String subject, boolean htmlflag, String content)
			throws ResultException, MessagingException, IOException;
	
	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param subject 主题
	 * @param htmlflag 是否HTLM内容
	 * @param content 内容
	 * @throws ResultException
	 * @throws MessagingException
	 * @throws IOException
	 * @author Shi Zezhu
	 * @date 2017年7月15日 上午11:31:35
	 */
	void sendMail(InternetAddress to, String subject, boolean htmlflag, String content)
			throws ResultException, MessagingException, IOException;

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param cc 抄送
	 * @param subject 主题
	 * @param htmlflag 是否HTLM内容
	 * @param content 内容
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年7月14日 下午4:35:50
	 */
	void sendMail(List<InternetAddress> to, List<InternetAddress> cc, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException;

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param subject 主题
	 * @param content 内容
	 * @param htmlflag 是否HTLM内容
	 * @throws ResultException
	 * @throws MessagingException
	 * @throws IOException
	 * @author Shi Zezhu
	 * @date 2017年7月15日 上午11:32:02
	 */
	void sendMail(List<String> to, String subject, String content, boolean htmlflag)
			throws ResultException, MessagingException, IOException;
	
	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param subject 主题
	 * @param content 内容
	 * @param htmlflag 是否HTLM内容
	 * @throws ResultException
	 * @throws MessagingException
	 * @throws IOException
	 * @author Shi Zezhu
	 * @date 2017年7月15日 上午11:32:02
	 */
	void sendMail(String to, String subject, String content, boolean htmlflag)
			throws ResultException, MessagingException, IOException;

	/**
	 * 发送邮件
	 * 
	 * @param to 收件
	 * @param cc 抄送
	 * @param subject 主题
	 * @param content 内容
	 * @param htmlflag 是否HTLM内容
	 * @throws ResultException
	 * @author Shi Zezhu
	 * @date 2017年7月14日 下午4:35:50
	 */
	void sendMail(List<String> to, List<String> cc, String subject, String content,
			boolean htmlflag) throws ResultException, MessagingException, IOException;
}
