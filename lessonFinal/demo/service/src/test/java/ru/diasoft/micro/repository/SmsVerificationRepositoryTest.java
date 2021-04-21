package ru.diasoft.micro.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerification;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest //(classes = {DemoApplication.class})
public class SmsVerificationRepositoryTest {
    private final String PHONE_NUMBER = "+79162698190";
    private final String SECRET_CODE = "leo";

    @Autowired
    private SmsVerificationRepository repository;

    @Test
    public void smsVerificationCreateTest() {

        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(UUID.randomUUID().toString())
                .phoneNumber(PHONE_NUMBER)
                .secretCode(SECRET_CODE)
                .status(SmsVerification.waitingStatus)
                .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(smsVerificationCreated, "verificationID");
        assertThat(smsVerificationCreated.getVerificationID()).isNotNull();

    }

    @Test
    public void findBySecretCodeAndProcessGuidAndStatus() {
        final String guid = UUID.randomUUID().toString();
        SmsVerification smsVerification =
                SmsVerification.builder()
                        .processGuid(guid)
                        .phoneNumber(PHONE_NUMBER)
                        .secretCode(SECRET_CODE)
                        .status(SmsVerification.waitingStatus)
                        .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);
        assertThat(repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, guid, SmsVerification.waitingStatus)
                .orElse(SmsVerification.builder().build())).isEqualTo(smsVerificationCreated);
        assertThat(repository.findBySecretCodeAndProcessGuidAndStatus("wrong", guid, SmsVerification.waitingStatus))
                .isEmpty();

    }
}

