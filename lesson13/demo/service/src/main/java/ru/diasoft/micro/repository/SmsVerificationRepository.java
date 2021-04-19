package ru.diasoft.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.diasoft.micro.domain.SmsVerification;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface SmsVerificationRepository extends JpaRepository<SmsVerification, Long> {

    Optional<SmsVerification> findBySecretCodeAndProcessGuidAndStatus(String secretCode, String processGuid, String status);

    @Transactional
    @Modifying
    @Query("update SmsVerification v set status = ?1 where processguid = ?2")
    int updateStatusByProcessGuid(String status,String processGuid);

}
