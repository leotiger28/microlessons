
package ru.diasoft.micro.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class SmsVerificationServiceTest {

    @Mock
    private SmsVerificationRepository repository;

    @Mock
    private SmsVerificationCreatedPublishGateway messagingGateway;

    private SmsVerificationPrimaryService service;
    private final String PHONE_NUMBER = "+79162698190";
    private final String SECRET_CODE = "leo";
    private final String INVALID_CODE = "WRONG";
    private final String GUID = UUID.randomUUID().toString();

    @Before
    public void init() {
        service = new SmsVerificationPrimaryService(repository, messagingGateway);

        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(GUID)
                .phoneNumber(PHONE_NUMBER)
                .secretCode(SECRET_CODE)
                .status(SmsVerification.okStatus)
                .build();

        when(repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, GUID, SmsVerification.okStatus))
                .thenReturn(Optional.of(smsVerification));

        when(repository.findBySecretCodeAndProcessGuidAndStatus(INVALID_CODE, GUID, SmsVerification.okStatus))
                .thenReturn(Optional.empty());
    }

    @Test
    public void dsSmsVerificationCheckTest() {
        SmsVerificationCheckRequest checkRequest = new SmsVerificationCheckRequest(GUID, SECRET_CODE);
        SmsVerificationCheckResponse response = service.dsSmsVerificationCheck(checkRequest);
        assertThat(response.getCheckResult()).isEqualTo(true);

        checkRequest = new SmsVerificationCheckRequest(GUID, INVALID_CODE);
        response = service.dsSmsVerificationCheck(checkRequest);
        assertThat(response.getCheckResult()).isEqualTo(false);
    }

    @Test
    public void dsSmsVerificationCreateTest() {
        SmsVerificationPostRequest req = new SmsVerificationPostRequest(PHONE_NUMBER);
        SmsVerificationPostResponse response = service.dsSmsVerificationCreate(req);
        assertThat(response.getProcessGUID()).isNotEmpty();
    }

}

