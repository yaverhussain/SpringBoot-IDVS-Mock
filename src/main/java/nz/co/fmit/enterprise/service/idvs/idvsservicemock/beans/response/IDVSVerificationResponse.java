package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDVSVerificationResponse {
    private Verification verification = new Verification();

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public boolean isSuccess() {
        return verification.isVerificationSuccess();
    }

    public String getVerificationErrorMessage() {
        if (isSuccess() || verification.getError() == null) {
            return "";
        }

        StringBuilder error = new StringBuilder();
        error.append(verification.getMessage());
        if (verification.getFields() != null) {
            error.append("(");
            error.append(verification.getFields());
            error.append(")");
        }

        return error.toString();
    }

    public String getVerificationErrorCode() {
        if(isSuccess()){
            return "";
        }

        return verification.getError();
    }
}
