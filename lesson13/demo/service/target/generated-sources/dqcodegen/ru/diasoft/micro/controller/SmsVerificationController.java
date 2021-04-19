/*
 * Created by DQCodegen
 */
package ru.diasoft.micro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.micro.domain.SmsVerificationCheckRequest;
import ru.diasoft.micro.domain.SmsVerificationCheckResponse;
import ru.diasoft.micro.domain.SmsVerificationPostRequest;
import ru.diasoft.micro.domain.SmsVerificationPostResponse;
import ru.diasoft.micro.mdp.lib.utils.exception.APIException;
import ru.diasoft.micro.mdp.lib.utils.exception.ExceptionUtils;
import ru.diasoft.micro.service.SmsVerificationService;

@RestController("ru.diasoft.micro.controller.SmsVerificationController")
@Api(tags = {"SmsVerification"})
@lombok.Generated
public class SmsVerificationController {

    private final SmsVerificationService smsVerificationService;
    
    @Autowired    
    public SmsVerificationController(SmsVerificationService smsVerificationService) {
        this.smsVerificationService = smsVerificationService;
    }

    @GetMapping("/v1/sms-verification")
    @ApiOperation(value = "Метод проверяет, что введенный код соответствует отправленному.", response = SmsVerificationCheckResponse.class, tags = {"SmsVerification"})
    public ResponseEntity<?> dsSmsVerificationCheck(
                @RequestBody
                @ApiParam(name = "SmsVerificationCheckRequest", value = "", required = false)
                SmsVerificationCheckRequest smsVerificationCheckRequest,
                @ApiParam(value = "Field set for return") 
                @RequestParam(value = "fields", required = false)
                String fields) {
        try {
            return smsVerificationService.dsSmsVerificationCheck(
                smsVerificationCheckRequest);         
        } catch (APIException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ExceptionUtils.buildErrorData(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.buildErrorData(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping("/v1/sms-verification")
    @ApiOperation(value = "Отправка проверочного кода на телефон клиента.", response = SmsVerificationPostResponse.class, tags = {"SmsVerification"})
    public ResponseEntity<?> dsSmsVerificationCreate(
                @RequestBody
                @ApiParam(name = "SmsVerificationPostRequest", value = "", required = false)
                SmsVerificationPostRequest smsVerificationPostRequest) {
        try {
            return smsVerificationService.dsSmsVerificationCreate(
                smsVerificationPostRequest);         
        } catch (APIException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ExceptionUtils.buildErrorData(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.buildErrorData(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

}
