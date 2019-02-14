package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.IDVSValidationException;

import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification;


import java.util.Date;

import static nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response.Verification.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {

    private Name name;

    private Date dateofbirth;

    private DriversLicence driverslicence;

    private Passport passport;

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Name getName() {
        return name;
    }

    public Details setName(Name name) {
        this.name = name;
        return this;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public Details setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
        return this;
    }

    public DriversLicence getDriverslicence() {
        return driverslicence;
    }

    public Details setDriverslicence(DriversLicence driverslicence) {
        this.driverslicence = driverslicence;
        return this;
    }

    public void validate() {
        if (dateofbirth == null) {
            throw new IDVSValidationException(Verification.ERR_REQUIRED_FIELD_MISSING, DOB_MISSING, null);
        }

        if (name == null) {
            throw new IDVSValidationException(ERR_REQUIRED_FIELD_MISSING, NAME_MISSING, null);
        }

        name.validate();

        if (driverslicence != null) {
            driverslicence.validate();
        }

        if (passport != null) {
            passport.validate();
        }
    }

}
