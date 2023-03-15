package com.example.Starbucks.Mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface MailService {

    String createKey();
    String sendSimpleMessage(String to)throws Exception;

    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;
}
