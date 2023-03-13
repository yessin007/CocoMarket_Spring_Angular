package com.example.coco_spring.Service;

import com.example.coco_spring.Entity.Product;
import com.example.coco_spring.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.slf4j.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private TemplateEngine templateEngine;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public void sendEmail(String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setSubject(subject);
        messageHelper.setTo(to);

        Context context = new Context();
        context.setVariable("subject", subject);
        context.setVariable("message", message);
        //String content = templateEngine.process("email-template", context);
        String content = templateEngine.process("my-new-email", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    public void sendWelcomeEmail(User u) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setSubject("Welcome");
        messageHelper.setTo(u.getEmail());

        Context context = new Context();
        context.setVariable("code", u.getCodeActivation());
        context.setVariable("name", u.getName());
        //String content = templateEngine.process("email-template", context);
        String content = templateEngine.process("welcomeMail", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    public void sendDailyOfferEmail(User u, List<Product> p) throws MessagingException {
        if (StringUtils.isEmpty(u.getEmail())) {
            logger.error("Recipient address is empty");
            return;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(u.getEmail());
        if (!matcher.matches()) {
            logger.error("Invalid recipient email address / the mail is: "+u.getEmail());
            return;
        }
        logger.info("sending daily mail to "+ u.getEmail());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setSubject("Daily Deals: Get Up to 40% Off on Our Latest Collection");
        messageHelper.setTo(u.getEmail());

        Context context = new Context();
        //context.setVariable("subject", subject);
        //context.setVariable("message", message);
        String content = templateEngine.process("DailyOffres", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);

    }

    public void sendBlockEmail(User u,Integer dure) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        messageHelper.setSubject("Your Account has benn Blocked");
        messageHelper.setTo(u.getEmail());

        Context context = new Context();
        context.setVariable("name", u.getName());
        context.setVariable("dure", dure);
        //String content = templateEngine.process("email-template", context);
        String content = templateEngine.process("BlockMail.html", context);


        messageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }
}

