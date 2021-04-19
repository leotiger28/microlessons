package ru.diasoft.micro.service;
/*
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.DemoApplication;
import ru.diasoft.micro.domain.SmsVerification;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;
import ru.diasoft.micro.repository.SmsVerificationRepository;
import ru.diasoft.micro.smsverificationcreated.publish.SmsVerificationCreatedPublishGateway;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class SmsVerificationPrimaryServiceTest {

    @Mock
    private SmsVerificationRepository repository;

    @Mock
    private SmsVerificationCreatedPublishGateway messagingGateway;

    private SmsVerificationPrimaryService service;

    private SmsVerificationPostRequest req;

    private SmsVerificationCheckRequest checkRequest;

    private final String PHONE = "+78800853535";

    private final String VALID_CODE = "SUCCESS";

    private final String INVALID_CODE = "WRONG";

    private final String GUID = UUID.randomUUID().toString();

    private final String STATUS = "OK";

    @Before
    public void init() {
        service = new SmsVerificationPrimaryService(repository, messagingGateway);
        req = new SmsVerificationPostRequest(PHONE);
        checkRequest = new SmsVerificationCheckRequest();

        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(GUID)
                .phoneNumber(PHONE)
                .secretCode(VALID_CODE)
                .status(STATUS)
                .build();

        when(repository.findBySecretCodeAndProcessGuidAndStatus(VALID_CODE, GUID, STATUS))
                .thenReturn(Optional.of(smsVerification));

        when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_CODE, GUID, STATUS))
                .thenReturn(Optional.empty());
    }

    @Test
    public void testDsSmsVerificationCheck() {
        checkRequest.setProcessGUID(GUID);
        checkRequest.setCode(VALID_CODE);
        ResponseEntity<SmsVerificationCheckResponse> response = service.dsSmsVerificationCheck(checkRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCheckResult()).isEqualTo(true);

        checkRequest.setProcessGUID(GUID);
        checkRequest.setCode(INVALID_CODE);
        response = service.dsSmsVerificationCheck(checkRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCheckResult()).isEqualTo(false);
    }

    @Test
    public void testDsSmsVerificationCreate() {
        ResponseEntity<SmsVerificationPostResponse> response = service.dsSmsVerificationCreate(req);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getProcessGUID()).isNotEmpty();
    }

}
*/