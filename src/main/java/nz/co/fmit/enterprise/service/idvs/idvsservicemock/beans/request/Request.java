package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    private final static String SECRET_KEY = "4nB1e2d7KNdGJBWe";

    private String key;
    private String signature;
    private String nonce;
    private String timestamp;
    private Data data;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void validate(){

        if(key == null || !SECRET_KEY.equals(key)){
            throw new IDVSValidationException(ERR_ACCESS_KEY, INVALID_KEY, null);
        }

        data.validate();
    }
}
