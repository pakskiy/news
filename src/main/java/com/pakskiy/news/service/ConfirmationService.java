package com.pakskiy.news.service;

import com.pakskiy.news.exception.OutOfLimitException;
import com.pakskiy.news.exception.RecordNotFoundException;
import com.pakskiy.news.model.ConfirmationCodeEntity;
import com.pakskiy.news.model.UserEntity;
import com.pakskiy.news.repository.ConfirmationRepository;
import com.pakskiy.news.validator.ValidatePhone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfirmationService {
    private final UserService userService;
    private final ConfirmationRepository confirmationRepository;

    @Value("${spring.confirmation.code.length}")
    private Integer codeLength;

    @Value("${spring.confirmation.code.timeout}")
    private Integer timeoutInterval;
    public void send(String phone) {
        UserEntity user = userService.getUserByPhone(new ValidatePhone(phone).value()).orElseThrow(() -> new RecordNotFoundException("User not found"));

        var userId = user.getId();
        checkRequestInterval(userId);

        var code = generateCode(userId);

        log.info("sms code: " + code + " for uid: " + userId);

        try {
            ConfirmationCodeEntity confirmationCodeEntity = new ConfirmationCodeEntity();
            confirmationCodeEntity.setUserId(userId);
            confirmationCodeEntity.setCode(code);
            confirmationCodeEntity.setStatus(0);
            confirmationRepository.save(confirmationCodeEntity);
        }catch (Exception e){
            log.error("send", e);
        }
    }

    public String validate(String phone, @Length(min = 6, max = 6) String code) {
        UserEntity user = userService.getUserByPhone(new ValidatePhone(phone).value()).orElseThrow(() -> new RecordNotFoundException("User not found"));
        var userId = user.getId();
        var sessionId = UUID.randomUUID().toString();
        if(!confirmationRepository.isValidCode(userId, code)){
            throw new RecordNotFoundException("Code not found");
        }
        confirmationRepository.changeStatus(userId, code, sessionId);
        return sessionId;
    }

    private void checkRequestInterval(Long userId) {
        long intervalSeconds = Long.valueOf(timeoutInterval);
        confirmationRepository.findLastByUserId(userId).ifPresent(code -> {
            if (!code.getCreatedAt().plusSeconds(intervalSeconds).isBefore(LocalDateTime.now())) {
                throw new OutOfLimitException("Request interval must be greater than " + intervalSeconds / 60 + " minutes");
            }
        });
    }

    private String generateCode(Long uid) {
        String returnCode = "";
        boolean codeIsUnique = false;
        while(!codeIsUnique){
            returnCode = RandomStringUtils.randomNumeric(codeLength);
            codeIsUnique = (confirmationRepository.findCodeByUid(uid, returnCode)==0);
        }
        return returnCode;
    }
}