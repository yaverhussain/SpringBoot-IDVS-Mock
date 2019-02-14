package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private Details details;
    private String reference;
    private Consent consent;

    public Details getDetails() {
        return details;
    }

    public Data setDetails(Details details) {
        this.details = details;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public Data setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public Consent getConsent() {
        return consent;
    }

    public Data setConsent(Consent consent) {
        this.consent = consent;
        return this;
    }

    public void validate() {
        if(consent == null || consent != Consent.Yes){
            throw new IDVSValidationException(ERR_INVALID_PARAMTER, CONSENT_ERROR, null);
        }

        if(details == null){
            throw new IDVSValidationException(ERR_REQUIRED_FIELD_MISSING, MANDATORY_DETAILS_MISSING, null);
        }

        details.validate();
    }
}
