/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckRequestMock;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationCheckResponseMock;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostRequestMock;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;
import ru.diasoft.micro.domain.SmsVerificationPostResponseMock;

@Service
@lombok.Generated
public class SmsVerificationServiceMock implements SmsVerificationService {

    @Override
    public ResponseEntity<?> dsSmsVerificationCheck(
            SmsVerificationCheckRequest smsVerificationCheckRequest) { 
        return ResponseEntity.status(HttpStatus.OK).body(new SmsVerificationCheckResponseMock());  
    } 

    @Override
    public ResponseEntity<?> dsSmsVerificationCreate(
            SmsVerificationPostRequest smsVerificationPostRequest) { 
        return ResponseEntity.status(HttpStatus.OK).body(new SmsVerificationPostResponseMock());  
    } 

}

