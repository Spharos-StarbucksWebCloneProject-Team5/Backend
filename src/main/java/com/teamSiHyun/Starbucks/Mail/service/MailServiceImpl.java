package com.teamSiHyun.Starbucks.Mail.service;

import com.teamSiHyun.Starbucks.Mail.dto.EmailDto;
import com.teamSiHyun.Starbucks.config.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;  //
    private final RedisUtil redisUtil;
    private static final Long EMAIL_EXPIRE = 120*1L;
    private String ePw; //인증 번호

    @Override
    public MimeMessage createMessage(EmailDto to) throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO,to.getTo()); //보내는 대상
        message.setSubject("인증 메일"); // 제목

        String msgg = "";
        msgg += "<div style='margin:100px; border-top:3px solid rgb(15, 153, 207)'>";
        msgg += "<div style=\"display: flex; padding-top: 30px; width: 100%; justify-content: space-between;\">";
        msgg += "<div>";
        msgg += "<h1 style=\"color:  rgb(15, 153, 207)\"> 인증번호 확인 후</h1>";
        msgg += "<h1 style=\"color:  rgb(15, 153, 207)\"> 이메일 인증을 완료해주세요.</h1>";
        msgg += "<hr style=\"float:left ;width: 100px; border:2px solid rgb(15, 153, 207)\">";
        msgg += "</div>";
        msgg += "<img src=\"https://j.gifs.com/EqVvOv.gif\" style=\"width: 400px; margin-left: 200px;\">";
        msgg += "</div>";
        msgg += "<div>";
        msgg += "<h2 style=\"font-size: 15px;\">안녕하세요? 시현조 입니다.</h2>";
        msgg += "<h2 style=\"font-size: 15px;\">아래 인증코드를 입력하여 이메일 인증을 진행하세요.</h2>";
        msgg += "</div> </br>";
        msgg += "style='border:1px solid black; font-family:verdana; background-color: rgba(15, 153, 207, 0.7); width: 500px; margin-left: 27%; align-content: center;'>";
        msgg += "<h3 style='color:white;'>메일 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "<p style=\"color:white;\">인증코드 : <strong></p>";
        msgg += "<p style=\"color:white;\">"+ ePw + ": <strong></p>"; // 메일에 인증번호 넣기
        msgg += "<div> </br>";
        msgg += "</div>";
        msgg += "</div>";
        msgg += "</div>";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("dkdlzks32@naver.com", "test"));// 보내는 사람

        return message;

    }

    // 랜덤 인증 코드 전송
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send
    @Override
    public String sendSimpleMessage(EmailDto to) throws Exception {

        ePw = createKey(); // 랜덤 인증번호 생성

        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to); // 메일 생성

        try {// 예외처리
            redisUtil.setDataExpire(ePw,to.getTo(),EMAIL_EXPIRE);  //유효시간 2분
            mailSender.send(message);   //메일 발송
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }


        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }

    public ResponseEntity<?> verifyEmail(String key) throws ChangeSetPersister.NotFoundException{
        String userEmail = redisUtil.getData(key);
        if(userEmail == null){
//            throw new ChangeSetPersister.NotFoundException();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 요청이 만료되었습니다.");
        }
        redisUtil.deleteData(key);
//        return ePw;
        return ResponseEntity.ok("인증되었습니다.");
    }


}
