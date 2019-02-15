package nz.co.fmit.enterprise.service.idvs.idvsservicemock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.Name;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.Data;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.DriversLicence;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.Request;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.Passport;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.IDVSVerificationResponse;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.VerificationSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/idvs-mock")
public class IDVSService {
    private static final Log log = LogFactory.getLog(IDVSService.class);

    @PostMapping("/verify")
    public ResponseEntity<IDVSVerificationResponse> verify(@RequestParam Map<String, String> request) {
        boolean success = true;

        Request requestObject = new Request();
        requestObject.setKey(request.get("key"));
        requestObject.setSignature(request.get("signature"));
        requestObject.setNonce(request.get("nonce"));
        requestObject.setTimestamp(request.get("timestamp"));

        Data data;
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(request.get("data"), Data.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        requestObject.setData(data);

        requestObject.validate();

        DriversLicence licence = data.getDetails().getDriverslicence();
        Passport passport = data.getDetails().getPassport();

        boolean isLicencePresent = true;
        boolean isPassportPresent = true;
        if (licence == null) {
            isLicencePresent = false;
        }

        if (passport == null) {
            isPassportPresent = false;
        }

        if (!(isLicencePresent || isPassportPresent) || (isLicencePresent && isPassportPresent)) {
            success = false;
        }

        if (isLicencePresent && (StringUtils.isBlank(licence.getNumber()) || StringUtils.isBlank(licence.getVersion()))) {
            success = false;
        }

        if (isPassportPresent && (StringUtils.isBlank(passport.getNumber()) ||
                passport.getExpiry() == null ||  Days.daysBetween(new DateTime(passport.getExpiry()), new DateTime()).getDays() >= 0)) {
            success = false;
        }

        Name name = data.getDetails().getName();
        if (StringUtils.isBlank(name.getGiven())) {
            success = false;
        }

        if (Days.daysBetween(new DateTime(data.getDetails().getDateofbirth()), new DateTime()).getDays() <= 0) {
            success = false;
        }

        IDVSVerificationResponse response = new IDVSVerificationResponse();
        Verification verification = response.getVerification();
        verification.setVerificationSuccess(success);
        verification.setVerificationPartialSuccess(false);
        verification.setSources(Arrays.asList(new VerificationSource()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "hurray";
    }

    @ExceptionHandler(IDVSValidationException.class)
    public ResponseEntity<IDVSVerificationResponse> handleHttpStatusException(IDVSValidationException exception) {
        exception.printStackTrace();
        log.error(exception);
        IDVSVerificationResponse response = new IDVSVerificationResponse();
        Verification verification = response.getVerification();
        verification.setVerificationSuccess(false);
        verification.setVerificationPartialSuccess(false);
        verification.setError(exception.getErrorCode());
        verification.setMessage(exception.getMessage());
        verification.setFields(exception.getFields());
        verification.setSources(new ArrayList<VerificationSource>());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<IDVSVerificationResponse> handleHttpStatusException(Exception exception) {
        exception.printStackTrace();
        log.error(exception);
        IDVSVerificationResponse response = new IDVSVerificationResponse();
        Verification verification = response.getVerification();
        verification.setVerificationSuccess(false);
        verification.setVerificationPartialSuccess(false);
        verification.setSources(new ArrayList<VerificationSource>());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

