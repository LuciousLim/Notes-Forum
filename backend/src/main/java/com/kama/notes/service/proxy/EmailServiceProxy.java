package com.kama.notes.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kama.notes.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary // 确保优先注入此Bean
public class EmailServiceProxy implements EmailService { ;

    @Value("${mail.provider}")
    private String emailProvider; // 读取配置

    @Autowired
    @Qualifier("redisEmailServiceImpl")
    private EmailService redisEmailService;

    @Autowired
    @Qualifier("kafkaEmailServiceImpl")
    private EmailService kafkaEmailService;

    @Override
    public String sendVerificationCode(String email) {
        if ("kafka".equals(emailProvider)) {
            return kafkaEmailService.sendVerificationCode(email);
        } else {
            return redisEmailService.sendVerificationCode(email);
        }
    }

    // 验证码存储仍在Redis中，与消息队列无关
    @Override
    public boolean checkVerificationCode(String email, String code) {
        return redisEmailService.checkVerificationCode(email, code);
    }

    @Override
    public boolean isVerificationCodeRateLimited(String email) {
        return redisEmailService.isVerificationCodeRateLimited(email);
    }
}
