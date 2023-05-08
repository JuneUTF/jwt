package com.example.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.model.KeyNumberModel;
import com.example.demo.model.RegisterModel;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    public void RegisterEmail(RegisterModel registerModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(registerModel.getEmail());
        helper.setSubject("新規登録メール!");
        Context context = new Context();
    	context.setVariable("username",registerModel.getUsername());
    	context.setVariable("password",registerModel.getPassword());
    	context.setVariable("name",registerModel.getName());
    	context.setVariable("address",registerModel.getAddress());
    	context.setVariable("birthday",registerModel.getBirthday());
    	context.setVariable("sex",registerModel.getSex());
    	context.setVariable("tokenKey",registerModel.getKeynumber());
        String htmlContent = templateEngine.process("registerUser", context);
        helper.setText(htmlContent, true);
        emailSender.send(message);
    }
    public void sendKeyNumberPass(KeyNumberModel keyNumberModel) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        helper.setTo(keyNumberModel.getEmail());
        helper.setSubject("title");
        Context context = new Context();
        context.setVariable("keynumber", keyNumberModel.getKeynumber());
        String htmlContent = templateEngine.process("keyNumber", context);
        helper.setText(htmlContent, true);
        emailSender.send(message);
    }
}

