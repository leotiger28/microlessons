package ru.diasoft.micro.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "sms_verification")
public class SmsVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_verification_seq")
    @SequenceGenerator(name = "sms_verification_seq", sequenceName = "sms_verification_seq", allocationSize = 1)
    @Column(name = "verificationid")
    private Long verificationID;

    @Column(name = "processguid")
    private String processGUID;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "secretcode")
    private String secretCode;

    @Column(name = "status")
    private String status;
}
