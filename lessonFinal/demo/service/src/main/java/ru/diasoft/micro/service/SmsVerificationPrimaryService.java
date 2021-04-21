package ru.diasoft.micro.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;
import ru.diasoft.micro.model.SmsVerificationMessage;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Primary
public class SmsVerificationPrimaryService implements SmsVerificationService {

    private final SmsVerificationRepository smsVerificationRepository;
    private final SmsVerificationCreatedPublishGateway messagingGateway;

    private final Logger logger = LogManager.getLogger(SmsVerificationPrimaryService.class);

    @Override
    public SmsVerificationCheckResponse dsSmsVerificationCheck(SmsVerificationCheckRequest smsVerificationCheckRequest) {
        Optional<SmsVerification> smsVerification = smsVerificationRepository.findBySecretCodeAndProcessGuidAndStatus(smsVerificationCheckRequest.getCode(), smsVerificationCheckRequest.getProcessGUID(), SmsVerification.okStatus);
        return new SmsVerificationCheckResponse(smsVerification.isPresent());
    }

    @Override
    public SmsVerificationPostResponse dsSmsVerificationCreate(SmsVerificationPostRequest smsVerificationPostRequest) {
        String guid = UUID.randomUUID().toString();
        String secretCode = (new Random().nextInt(10000)) + "";
        logger.debug("Phone number " + smsVerificationPostRequest.getPhoneNumber());

        SmsVerification smsVerification = SmsVerification.builder()
                .phoneNumber(smsVerificationPostRequest.getPhoneNumber())
                .processGuid(guid)
                .secretCode(secretCode)
                .status(SmsVerification.waitingStatus)
                .build();
        smsVerificationRepository.save(smsVerification);
        messagingGateway.smsVerificationCreated(SmsVerificationMessage.builder().
                guid(guid).
                code(secretCode).
                phoneNumber(smsVerificationPostRequest.getPhoneNumber()).build());
        return new SmsVerificationPostResponse(guid);
    }
}
