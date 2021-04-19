/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.service;

import org.springframework.http.ResponseEntity;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;

@lombok.Generated
public interface SmsVerificationService {

    ResponseEntity<?> dsSmsVerificationCheck(
        SmsVerificationCheckRequest smsVerificationCheckRequest);

    ResponseEntity<?> dsSmsVerificationCreate(
        SmsVerificationPostRequest smsVerificationPostRequest);

}
