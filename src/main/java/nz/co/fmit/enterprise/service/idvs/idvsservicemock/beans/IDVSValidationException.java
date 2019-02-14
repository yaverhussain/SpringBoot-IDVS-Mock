package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans;

public class IDVSValidationException extends RuntimeException {
    private final String errorCode;
    private final String message;
    private final String fields;

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getFields() {
        return fields;
    }

    public IDVSValidationException(String errorCode, String message, String fields) {
        this.errorCode = errorCode;
        this.message = message;

        this.fields = fields;
    }
}
