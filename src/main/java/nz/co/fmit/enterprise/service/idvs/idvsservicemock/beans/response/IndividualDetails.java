package nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nz.co.fmit.enterprise.service.idvs.idvsservicemock.beans.request.Name;


import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualDetails {
    private Date dateofbirth;
    private Name name;

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndividualDetails{");
        sb.append("dateOfBirth=").append(dateofbirth);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
