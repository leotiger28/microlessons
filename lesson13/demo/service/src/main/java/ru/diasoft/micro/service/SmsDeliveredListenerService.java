package ru.diasoft.micro.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import ru.diasoft.micro.repository.SmsVerificationRepository;
//import ru.diasoft.micro.smsverificationdelivered.subscribe.SmsVerificationDeliveredSubscribeListenerService;
//import ru.diasoft.micro.model.SmsDeliveredMessage;

@RequiredArgsConstructor
@Service
@Primary
public class SmsDeliveredListenerService {//implements SmsVerificationDeliveredSubscribeListenerService {

    private final SmsVerificationRepository repository;

//    @Override
  //  public void smsVerificationDelivered(SmsDeliveredMessage msg) {
    //    repository.updateStatusByProcessGuid("OK",msg.getGuid());
   // }
}