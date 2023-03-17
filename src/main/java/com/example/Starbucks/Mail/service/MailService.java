package com.example.Starbucks.Mail.service;

import com.example.Starbucks.Mail.dto.EmailDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface MailService {

    String createKey();
    String sendSimpleMessage(EmailDto to)throws Exception;

    public MimeMessage createMessage(EmailDto to) throws MessagingException, UnsupportedEncodingException;
}
