package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VerificationSource {

    private boolean success;
    private String name;
    private boolean error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VerificationSource{");
        sb.append("success=").append(success);
        sb.append(", name='").append(name).append('\'');
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}
