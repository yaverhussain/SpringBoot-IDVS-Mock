package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.ERR_INVALID_PARAMTER;
import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.INVALID_FORMAT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Passport {
    private String number;

    private Date expiry;

    public String getNumber() {
        return number;
    }

    public Passport setNumber(String number) {
        this.number = number;
        return this;
    }

    public Date getExpiry() {
        return expiry;
    }

    public Passport setExpiry(Date expiry) {
        this.expiry = expiry;
        return this;
    }

    public void validate() {
        String errorFields = "";
        if (!StringUtils.isBlank(number)) {
            if (!(number.matches("[a-zA-Z0-9]{8}"))) {
                errorFields = "passportNumber";
            }
        }

        if (errorFields.length() > 0) {
            throw new IDVSValidationException(ERR_INVALID_PARAMTER, INVALID_FORMAT, errorFields);
        }
    }
}
