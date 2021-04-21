package ru.diasoft.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.diasoft.micro.domain.SmsVerification;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmsVerificationRepository extends JpaRepository<SmsVerification, Long> {

    //@Query("select v from SmsVerification v where secretCode = ?1 and processGuid = ?2 and status = ?3")
    Optional<SmsVerification> findBySecretCodeAndProcessGuidAndStatus(String secretCode, String processGuid, String status);

    //Optional<SmsVerification> findBySecretCodeAndProcessGuid(String secretCode, String processGuid);

    //@Query("select u from UserRightEntity u where u.id in :id")
    //List<UserRightEntity> findAllByIdIn(@Param("id") List<Long> id);

    @Transactional
    @Modifying
    @Query("update SmsVerification v set status = ?1 where processGuid = ?2")
    int updateStatusByProcessGuid(String status, String processGuid);

}
