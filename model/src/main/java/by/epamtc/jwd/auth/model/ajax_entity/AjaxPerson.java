package by.epamtc.jwd.auth.model.ajax_entity;

import java.io.Serializable;

public class AjaxPerson implements Serializable {
    private static final long serialVersionUID = 8054220186034186344L;

    private int personId;
    private String personInfo;

    public AjaxPerson(int personId, String personInfo) {
        this.personId = personId;
        this.personInfo = personInfo;
    }

    public AjaxPerson() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxPerson person = (AjaxPerson) o;

        if (personId != person.personId) {
            return false;
        }
        return (personInfo != null) ? personInfo.equals(person.personInfo)
                                    : (person.personInfo == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + personId;
        hash = 31 * hash + ((personInfo != null) ? personInfo.hashCode()
                                                 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxPerson{" +
                "personId=" + personId +
                ", personInfo='" + personInfo + '\'' +
                '}';
    }
}
