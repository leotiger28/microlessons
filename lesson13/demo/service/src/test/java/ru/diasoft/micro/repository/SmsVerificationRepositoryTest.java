package ru.diasoft.micro.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.diasoft.micro.domain.SmsVerification;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsVerificationRepositoryTest {
    private final String PHONE_NUMBER = "+79162698190";
    private final String SECRET_CODE = "leo";
    private final String STATUS = "waiting";

    @Autowired
    private SmsVerificationRepository repository;

    @Test
    public void smsVerificationCreateTest() {
        SmsVerification smsVerification = SmsVerification.builder()
                .processGUID(UUID.randomUUID().toString())
                .phoneNumber(PHONE_NUMBER)
                .secretCode(SECRET_CODE)
                .status(STATUS)
                .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);
        assertThat(smsVerification).isEqualToComparingOnlyGivenFields(smsVerificationCreated, "verificationid");
        assertThat(smsVerificationCreated.getVerificationID()).isNotNull();
    }
/*
    @Test
    public void findBySecretCodeAndProcessGuidAndStatus() {
        SmsVerification smsVerification = SmsVerification.builder()
                .processGuid(UUID.randomUUID().toString())
                .phoneNumber(PHONE)
                .secretCode(SECRET_CODE)
                .status(STATUS)
                .build();

        SmsVerification smsVerificationCreated = repository.save(smsVerification);
        assertThat(repository.findBySecretCodeAndProcessGuidAndStatus(SECRET_CODE, GUID, STATUS)
                .orElse(SmsVerification.builder().build())).isEqualTo(smsVerificationCreated);

        assertThat(repository.findBySecretCodeAndProcessGuidAndStatus("wrong", GUID, STATUS))
                .isEmpty();
    }

 */
}
