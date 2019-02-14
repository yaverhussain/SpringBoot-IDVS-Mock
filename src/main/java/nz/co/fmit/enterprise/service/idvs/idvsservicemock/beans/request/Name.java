package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;

import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.ERR_REQUIRED_FIELD_MISSING;
import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.FIELD_MISSING;
import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.NAME_MISSING;

public class Name {
    private String given;
    private String middle;
    private String family;

    public String getGiven() {
        return given;
    }

    public Name setGiven(String given) {
        this.given = given;
        return this;
    }

    public String getMiddle() {
        return middle;
    }

    public Name setMiddle(String middle) {
        this.middle = middle;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Name setFamily(String family) {
        this.family = family;
        return this;
    }

    public void validate() {
        if (family == null || family.length() == 0) {
            throw new IDVSValidationException(ERR_REQUIRED_FIELD_MISSING, FIELD_MISSING, "familyName");
        }
    }
}
