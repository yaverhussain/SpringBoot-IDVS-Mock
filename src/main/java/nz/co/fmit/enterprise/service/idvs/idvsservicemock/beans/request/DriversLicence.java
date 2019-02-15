package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.ERR_INVALID_PARAMTER;
import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.INVALID_FORMAT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriversLicence {

    private String number;
    private String version;

    public String getNumber() {
        return number;
    }

    public DriversLicence setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public DriversLicence setVersion(String version) {
        this.version = version;
        return this;
    }

    public void validate() {
        List<String> errorFields = new ArrayList<>();
        if (!StringUtils.isBlank(number)) {
            if (!(number.matches("[a-zA-Z0-9]{8}"))) {
                errorFields.add("driversLicenceNumber");
            }
        }

        if (!StringUtils.isBlank(version)) {
            if (version.length() != 3) {
                errorFields.add("driversLicenceVersion");
            } else {
                try {
                    Integer.parseInt(version.trim());
                } catch (NumberFormatException ex) {
                    errorFields.add("driversLicenceVersion");
                }
            }
        }

        if (errorFields.size() > 0) {
            StringBuilder str = new StringBuilder();
            for (String s : errorFields) {
                str.append(s).append(", ");

            }
            throw new IDVSValidationException(ERR_INVALID_PARAMTER, INVALID_FORMAT, str.toString().replaceAll(", $", ""));
        }
    }
}
