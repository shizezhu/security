package cn.szz.security.service.impl;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.szz.security.exception.ResultException;
import cn.szz.security.service.MailService;
import cn.szz.security.utils.CheckUtils;
import cn.szz.security.utils.ErrorCode;
import cn.szz.security.utils.PropertiesUtils;

@Service
public class MailServiceImpl implements MailService {

	@Resource
	private JavaMailSender mailSender;

	@Resource
	private TaskExecutor taskExecutor;
	
	@Override
	public void sendMail(InternetAddress[] to, InternetAddress[] cc, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException {
		CheckUtils.checkNotEmpty(to, ErrorCode.EM_ADDRESSEE_EMPTY);
		cc = CheckUtils.ifNull(cc, new InternetAddress[]{});
		CheckUtils.checkNotEmpty(subject, ErrorCode.EM_SUBJECT_EMPTY);
		CheckUtils.checkNotEmpty(content, ErrorCode.EM_CONTENT_EMPTY);
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
		helper.setFrom(new InternetAddress(PropertiesUtils.getProperty("mail.username"), URLDecoder.decode(PropertiesUtils.getProperty("mail.name"), "UTF-8"), "UTF-8"));
		helper.setTo(to);
		helper.setCc(cc);
		helper.setSubject(subject);
		helper.setText(content, htmlflag);
		this.sendMail(mime);
	}

	@Override
	public void sendMail(String[] to, String[] cc, String subject, String content,
			boolean htmlflag) throws ResultException, MessagingException, IOException {
		CheckUtils.checkNotEmpty(to, ErrorCode.EM_ADDRESSEE_EMPTY);
		cc = CheckUtils.ifNull(cc, new String[]{});
		CheckUtils.checkNotEmpty(subject, ErrorCode.EM_SUBJECT_EMPTY);
		CheckUtils.checkNotEmpty(content, ErrorCode.EM_CONTENT_EMPTY);
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
		helper.setFrom(PropertiesUtils.getProperty("mail.username"), URLDecoder.decode(PropertiesUtils.getProperty("mail.name"), "UTF-8"));
		helper.setTo(to);
		helper.setCc(cc);
		helper.setSubject(subject);
		helper.setText(content, htmlflag);
		this.sendMail(mime);
	}

	@Override
	public void sendMail(List<InternetAddress> to, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException {
		this.sendMail(to.toArray(new InternetAddress[to.size()]), null, subject, htmlflag, content);
	}
	
	@Override
	public void sendMail(InternetAddress to, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException {
		this.sendMail(new InternetAddress[]{to}, null, subject, htmlflag, content);
	}
	
	@Override
	public void sendMail(List<InternetAddress> to, List<InternetAddress> cc, String subject, boolean htmlflag,
			String content) throws ResultException, MessagingException, IOException {
		this.sendMail(to.toArray(new InternetAddress[to.size()]), cc.toArray(new InternetAddress[cc.size()]), subject, htmlflag, content);
	}

	@Override
	public void sendMail(List<String> to, String subject, String content,
			boolean htmlflag) throws ResultException, MessagingException, IOException {
		this.sendMail(to.toArray(new String[to.size()]), null, subject, content, htmlflag);
	}
	
	@Override
	public void sendMail(String to, String subject, String content,
			boolean htmlflag) throws ResultException, MessagingException, IOException {
		this.sendMail(new String[]{to}, null, subject, content, htmlflag);
	}
	
	@Override
	public void sendMail(List<String> to, List<String> cc, String subject, String content,
			boolean htmlflag) throws ResultException, MessagingException, IOException {
		this.sendMail(to.toArray(new String[to.size()]), cc.toArray(new String[cc.size()]), subject, content, htmlflag);
	}
	
	private void sendMail(final MimeMessage mime) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				mailSender.send(mime);
			}
		});
	}
}
