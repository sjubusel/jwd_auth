package by.epamtc.jwd.auth.model.user_info;

import java.time.LocalDate;

// TODO add serialVersionUID
public class IdentityDocument implements java.io.Serializable {
    private int id;
    private String identificationDocumentType;
    private String series;
    private int documentNumber;
    private String latinHolderName;
    private String latinHolderSurName;
    private String citizenShip;
    private LocalDate birthday;
    private String personalNumber;
    private Gender gender;
    private String placeOfOrigin;
    private LocalDate dateOfIssue;
    private LocalDate dateOfExpiry;
    private String issueAuthority;

    public IdentityDocument() {
    }

    public IdentityDocument(int id, String identificationDocumentType,
            String series, int documentNumber, String latinHolderName,
            String latinHolderSurName, String citizenShip, LocalDate birthday,
            String personalNumber, Gender gender, String placeOfOrigin,
            LocalDate dateOfIssue, LocalDate dateOfExpiry, String issueAuthority) {
        this.id = id;
        this.identificationDocumentType = identificationDocumentType;
        this.series = series;
        this.documentNumber = documentNumber;
        this.latinHolderName = latinHolderName;
        this.latinHolderSurName = latinHolderSurName;
        this.citizenShip = citizenShip;
        this.birthday = birthday;
        this.personalNumber = personalNumber;
        this.gender = gender;
        this.placeOfOrigin = placeOfOrigin;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.issueAuthority = issueAuthority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationDocumentType() {
        return identificationDocumentType;
    }

    public void setIdentificationDocumentType(String identificationDocumentType) {
        this.identificationDocumentType = identificationDocumentType;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getLatinHolderName() {
        return latinHolderName;
    }

    public void setLatinHolderName(String latinHolderName) {
        this.latinHolderName = latinHolderName;
    }

    public String getLatinHolderSurName() {
        return latinHolderSurName;
    }

    public void setLatinHolderSurName(String latinHolderSurName) {
        this.latinHolderSurName = latinHolderSurName;
    }

    public String getCitizenShip() {
        return citizenShip;
    }

    public void setCitizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdentityDocument that = (IdentityDocument) o;

        if (id != that.id) {
            return false;
        }
        if (((identificationDocumentType == null) && (that.identificationDocumentType != null))
                || ((identificationDocumentType != null) && (!identificationDocumentType.equals(that.identificationDocumentType)))) {
            return false;
        }
        if (((series == null) && (that.series != null))
                || ((series != null) && (!series.equals(that.series)))) {
            return false;
        }
        if (documentNumber != that.documentNumber) {
            return false;
        }
        if (((latinHolderName == null) && (that.latinHolderName != null))
                || ((latinHolderName != null) && (!latinHolderName.equals(that.latinHolderName)))) {
            return false;
        }
        if (((latinHolderSurName == null) && (that.latinHolderSurName != null))
                || ((latinHolderSurName != null) && (!latinHolderSurName.equals(that.latinHolderSurName)))) {
            return false;
        }
        if (((citizenShip == null) && (that.citizenShip != null))
                || ((citizenShip != null) && (!citizenShip.equals(that.citizenShip)))) {
            return false;
        }
        if (((birthday == null) && (that.birthday != null))
                || ((birthday != null) && (!birthday.equals(that.birthday)))) {
            return false;
        }
        if (((personalNumber == null) && (that.personalNumber != null))
                || ((personalNumber != null) && (!personalNumber.equals(that.personalNumber)))) {
            return false;
        }
        if (((gender == null) && (that.gender != null))
                || ((gender != null) && (!gender.equals(that.gender)))) {
            return false;
        }
        if (((placeOfOrigin == null) && (that.placeOfOrigin != null))
                || ((placeOfOrigin != null) && (!placeOfOrigin.equals(that.placeOfOrigin)))) {
            return false;
        }
        if (((dateOfIssue == null) && (that.dateOfIssue != null))
                || ((dateOfIssue != null) && (!dateOfIssue.equals(that.dateOfIssue)))) {
            return false;
        }
        if (((dateOfExpiry == null) && (that.dateOfExpiry != null))
                || ((dateOfExpiry != null) && (!dateOfExpiry.equals(that.dateOfExpiry)))) {
            return false;
        }
        return ((issueAuthority != null) || (that.issueAuthority == null))
                && ((issueAuthority == null) || (issueAuthority.equals(that.issueAuthority)));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        hash = 31 * hash + (identificationDocumentType != null ? identificationDocumentType.hashCode() : 0);
        hash = 31 * hash + (series != null ? series.hashCode() : 0);
        hash = 31 * hash + documentNumber;
        hash = 31 * hash + (latinHolderName != null ? latinHolderName.hashCode() : 0);
        hash = 31 * hash + (latinHolderSurName != null ? latinHolderSurName.hashCode() : 0);
        hash = 31 * hash + (citizenShip != null ? citizenShip.hashCode() : 0);
        hash = 31 * hash + (birthday != null ? birthday.hashCode() : 0);
        hash = 31 * hash + (personalNumber != null ? personalNumber.hashCode() : 0);
        hash = 31 * hash + (gender != null ? gender.hashCode() : 0);
        hash = 31 * hash + (placeOfOrigin != null ? placeOfOrigin.hashCode() : 0);
        hash = 31 * hash + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        hash = 31 * hash + (dateOfExpiry != null ? dateOfExpiry.hashCode() : 0);
        hash = 31 * hash + (issueAuthority != null ? issueAuthority.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "IdentityDocument{" +
                "id=" + id +
                ", identificationDocumentType='" + identificationDocumentType + '\'' +
                ", series='" + series + '\'' +
                ", documentNumber=" + documentNumber +
                ", latinHolderName='" + latinHolderName + '\'' +
                ", latinHolderSurName='" + latinHolderSurName + '\'' +
                ", citizenShip='" + citizenShip + '\'' +
                ", birthday=" + birthday +
                ", personalNumber='" + personalNumber + '\'' +
                ", gender=" + gender +
                ", placeOfOrigin='" + placeOfOrigin + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiry=" + dateOfExpiry +
                ", issueAuthority='" + issueAuthority + '\'' +
                '}';
    }
}
