package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Verification {
    public static String CONSENT_ERROR = "You must obtain consent to run a verification.";
    public static String DOB_MISSING = "Date of birth is missing.";
    public static String NAME_MISSING = "Name is missing.";
    public static String FIELD_MISSING = "Please check the required fields are all supplied.";
    public static String MANDATORY_DETAILS_MISSING = "Mandatory details missing.";
    public static String INVALID_FORMAT = "Please check the format of the supplied identification details.";
    public static String INVALID_KEY = "Invalid access key supplied.";




    public static final String ERR_ACCESS_KEY = "101";
    public static final String ERR_REQUIRED_FIELD_MISSING = "102";
    public static final String ERR_TIMESTAMP_TOO_OLD = "103";
    public static final String ERR_NONCE_ALREADY_USED = "104";
    public static final String ERR_INVALID_VERIFICATION_TOKEN = "105";
    public static final String ERR_INVALID_SIGNATURE = "106";
    public static final String ERR_INVALID_PARAMTER = "107";

    @JsonDeserialize(converter = StringToDateConverter.class)
    private Date requestDate;
    @JsonDeserialize(converter = StringToDateConverter.class)
    private Date verificationDate;

    private String reference;
    private String message;
    private String error;
    private String errorDetail;
    private String fields;
    private boolean verificationSuccess;
    private Map validated;
    private String verificationReference;
    private boolean verificationPartialSuccess;
    private IndividualDetails details;
    private List<VerificationSource> sources;

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getVerificationDate() {
        return verificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        this.verificationDate = verificationDate;
    }

    public boolean isVerificationSuccess() {
        return verificationSuccess;
    }

    public void setVerificationSuccess(boolean verificationSuccess) {
        this.verificationSuccess = verificationSuccess;
    }

    public Map getValidated() {
        return validated;
    }

    public void setValidated(Map validated) {
        this.validated = validated;
    }

    public String getVerificationReference() {
        return verificationReference;
    }

    public void setVerificationReference(String verificationReference) {
        this.verificationReference = verificationReference;
    }

    public boolean isVerificationPartialSuccess() {
        return verificationPartialSuccess;
    }

    public void setVerificationPartialSuccess(boolean verificationPartialSuccess) {
        this.verificationPartialSuccess = verificationPartialSuccess;
    }

    public IndividualDetails getDetails() {
        return details;
    }

    public void setDetails(IndividualDetails details) {
        this.details = details;
    }

    public List<VerificationSource> getSources() {
        return sources;
    }

    public void setSources(List<VerificationSource> sources) {
        this.sources = sources;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Verification{");
        sb.append("fields=").append(fields);
        sb.append(", errorDetail=").append(errorDetail);
        sb.append(", requestDate=").append(requestDate);
        sb.append(", verificationDate=").append(verificationDate);
        sb.append(", errorMessage='").append(message).append('\'');
        sb.append(", errorCode='").append(error).append('\'');
        sb.append(", verificationSuccess=").append(verificationSuccess);
        sb.append(", validated=").append(validated);
        sb.append(", verificationReference='").append(verificationReference).append('\'');
        sb.append(", partialSuccess=").append(verificationPartialSuccess);
        sb.append(", details=").append(details);
        sb.append(", sources=").append(sources);
        sb.append('}');
        return sb.toString();
    }

    public static class StringToDateConverter extends StdConverter<String, Date> {
        private static final Log log = LogFactory.getLog(StringToDateConverter.class);

        public StringToDateConverter() {
        }

        @Override
        public Date convert(String text) {
            Date result = null;
            if (text != null) {
                try {
                    result = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(text);
                } catch (ParseException e) {
                    log.warn("failed to parse " + text, e);
                }
            }
            return result;
        }
    }
}
