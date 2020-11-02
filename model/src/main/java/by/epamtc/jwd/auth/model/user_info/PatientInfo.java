package by.epamtc.jwd.auth.model.user_info;

import java.time.LocalDate;

// TODO add serialVersionUID
public class PatientInfo implements java.io.Serializable {
    private static final long serialVersionUID = -669272966787219927L;

    private String photoPath;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private MaritalStatus maritalStatus;
    private IdentityDocument identityDocument;
    private Address homeAddress;
    private String inCaseOfEmergencyContactPersonInfo;
    private String inCaseOfEmergencyContactPersonPhone;
    private BloodType bloodType;
    // TODO ENUM
    private String rhBloodGroup;
    // TODO ENUM
    private String disabilityDegree;
    // TODO ENUM
    private String transportationStatus;
    private boolean hasAllergicReactions;
    private boolean hasExtremelyHazardousDiseases;

    public PatientInfo() {
    }

    public PatientInfo(String photoPath, String firstName, String middleName,
            String lastName, LocalDate birthday, Gender gender, String email,
            String phoneNumber, MaritalStatus maritalStatus, IdentityDocument identityDocument,
            Address homeAddress, String inCaseOfEmergencyContactPersonInfo,
            String inCaseOfEmergencyContactPersonPhone, BloodType bloodType,
            String rhBloodGroup, String disabilityDegree,
            String transportationStatus, boolean hasAllergicReactions,
            boolean hasExtremelyHazardousDiseases) {
        this.photoPath = photoPath;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.maritalStatus = maritalStatus;
        this.identityDocument = identityDocument;
        this.homeAddress = homeAddress;
        this.inCaseOfEmergencyContactPersonInfo = inCaseOfEmergencyContactPersonInfo;
        this.inCaseOfEmergencyContactPersonPhone = inCaseOfEmergencyContactPersonPhone;
        this.bloodType = bloodType;
        this.rhBloodGroup = rhBloodGroup;
        this.disabilityDegree = disabilityDegree;
        this.transportationStatus = transportationStatus;
        this.hasAllergicReactions = hasAllergicReactions;
        this.hasExtremelyHazardousDiseases = hasExtremelyHazardousDiseases;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public IdentityDocument getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(IdentityDocument identityDocument) {
        this.identityDocument = identityDocument;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getInCaseOfEmergencyContactPersonInfo() {
        return inCaseOfEmergencyContactPersonInfo;
    }

    public void setInCaseOfEmergencyContactPersonInfo(String inCaseOfEmergencyContactPersonInfo) {
        this.inCaseOfEmergencyContactPersonInfo = inCaseOfEmergencyContactPersonInfo;
    }

    public String getInCaseOfEmergencyContactPersonPhone() {
        return inCaseOfEmergencyContactPersonPhone;
    }

    public void setInCaseOfEmergencyContactPersonPhone(String inCaseOfEmergencyContactPersonPhone) {
        this.inCaseOfEmergencyContactPersonPhone = inCaseOfEmergencyContactPersonPhone;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getRhBloodGroup() {
        return rhBloodGroup;
    }

    public void setRhBloodGroup(String rhBloodGroup) {
        this.rhBloodGroup = rhBloodGroup;
    }

    public String getDisabilityDegree() {
        return disabilityDegree;
    }

    public void setDisabilityDegree(String disabilityDegree) {
        this.disabilityDegree = disabilityDegree;
    }

    public String getTransportationStatus() {
        return transportationStatus;
    }

    public void setTransportationStatus(String transportationStatus) {
        this.transportationStatus = transportationStatus;
    }

    public boolean isHasAllergicReactions() {
        return hasAllergicReactions;
    }

    public void setHasAllergicReactions(boolean hasAllergicReactions) {
        this.hasAllergicReactions = hasAllergicReactions;
    }

    public boolean isHasExtremelyHazardousDiseases() {
        return hasExtremelyHazardousDiseases;
    }

    public void setHasExtremelyHazardousDiseases(boolean hasExtremelyHazardousDiseases) {
        this.hasExtremelyHazardousDiseases = hasExtremelyHazardousDiseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PatientInfo patientInfo = (PatientInfo) o;

        if (((photoPath == null) && (patientInfo.photoPath != null))
                || ((photoPath != null) && (!photoPath.equals(patientInfo.photoPath)))) {
            return false;
        }

        if (((firstName == null) && (patientInfo.firstName != null))
                || ((firstName != null) && (!firstName.equals(patientInfo.firstName)))) {
            return false;
        }
        if (((middleName == null) && (patientInfo.middleName != null))
                || ((middleName != null) && (!middleName.equals(patientInfo.middleName)))) {
            return false;
        }
        if (((lastName == null) && (patientInfo.lastName != null))
                || ((lastName != null) && (!lastName.equals(patientInfo.lastName)))) {
            return false;
        }
        if (((birthday == null) && (patientInfo.birthday != null))
                || ((birthday != null) && (!birthday.equals(patientInfo.birthday)))) {
            return false;
        }
        if (((gender == null) && (patientInfo.gender != null))
                || ((gender != null) && (!gender.equals(patientInfo.gender)))) {
            return false;
        }
        if (((email == null) && (patientInfo.email != null))
                || ((email != null) && (!email.equals(patientInfo.email)))) {
            return false;
        }
        if (((phoneNumber == null) && (patientInfo.phoneNumber != null))
                || ((phoneNumber != null) && (!phoneNumber.equals(patientInfo.phoneNumber)))) {
            return false;
        }
        if (((maritalStatus == null) && (patientInfo.maritalStatus != null))
                || ((maritalStatus != null) && (!maritalStatus.equals(patientInfo.maritalStatus)))) {
            return false;
        }
        if (((identityDocument == null) && (patientInfo.identityDocument != null))
                || ((identityDocument != null) && (!identityDocument.equals(patientInfo.identityDocument)))) {
            return false;
        }
        if (((homeAddress == null) && (patientInfo.homeAddress != null))
                || ((homeAddress != null) && (!homeAddress.equals(patientInfo.homeAddress)))) {
            return false;
        }
        if (((inCaseOfEmergencyContactPersonInfo == null) && (patientInfo.inCaseOfEmergencyContactPersonInfo != null))
                || ((inCaseOfEmergencyContactPersonInfo != null) && (!inCaseOfEmergencyContactPersonInfo.equals(patientInfo.inCaseOfEmergencyContactPersonInfo)))) {
            return false;
        }
        if (((inCaseOfEmergencyContactPersonPhone == null) && (patientInfo.inCaseOfEmergencyContactPersonPhone != null))
                || ((inCaseOfEmergencyContactPersonPhone != null) && (!inCaseOfEmergencyContactPersonPhone.equals(patientInfo.inCaseOfEmergencyContactPersonPhone)))) {
            return false;
        }
        if (((bloodType == null) && (patientInfo.bloodType != null))
                || ((bloodType != null) && (!bloodType.equals(patientInfo.bloodType)))) {
            return false;
        }
        if (((rhBloodGroup == null) && (patientInfo.rhBloodGroup != null))
                || ((rhBloodGroup != null) && (!rhBloodGroup.equals(patientInfo.rhBloodGroup)))) {
            return false;
        }
        if (((disabilityDegree == null) && (patientInfo.disabilityDegree != null))
                || ((disabilityDegree != null) && (!disabilityDegree.equals(patientInfo.disabilityDegree)))) {
            return false;
        }
        if (((transportationStatus == null) && (patientInfo.transportationStatus != null))
                || ((transportationStatus != null) && (!transportationStatus.equals(patientInfo.transportationStatus)))) {
            return false;
        }
        if (hasAllergicReactions != patientInfo.hasAllergicReactions) {
            return false;
        }
        return hasExtremelyHazardousDiseases == patientInfo.hasExtremelyHazardousDiseases;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (photoPath != null ? photoPath.hashCode() : 0);
        hash = 31 * hash + (firstName != null ? firstName.hashCode() : 0);
        hash = 31 * hash + (middleName != null ? middleName.hashCode() : 0);
        hash = 31 * hash + (lastName != null ? lastName.hashCode() : 0);
        hash = 31 * hash + (birthday != null ? birthday.hashCode() : 0);
        hash = 31 * hash + (gender != null ? gender.hashCode() : 0);
        hash = 31 * hash + (email != null ? email.hashCode() : 0);
        hash = 31 * hash + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        hash = 31 * hash + (maritalStatus != null ? maritalStatus.hashCode() : 0);
        hash = 31 * hash + (identityDocument != null ? identityDocument.hashCode() : 0);
        hash = 31 * hash + (homeAddress != null ? homeAddress.hashCode() : 0);
        hash = 31 * hash + (inCaseOfEmergencyContactPersonInfo != null ? inCaseOfEmergencyContactPersonInfo.hashCode() : 0);
        hash = 31 * hash + (inCaseOfEmergencyContactPersonPhone != null ? inCaseOfEmergencyContactPersonPhone.hashCode() : 0);
        hash = 31 * hash + (bloodType != null ? bloodType.hashCode() : 0);
        hash = 31 * hash + (rhBloodGroup != null ? rhBloodGroup.hashCode() : 0);
        hash = 31 * hash + (disabilityDegree != null ? disabilityDegree.hashCode() : 0);
        hash = 31 * hash + (transportationStatus != null ? transportationStatus.hashCode() : 0);
        hash = 31 * hash + (hasAllergicReactions ? 1 : 0);
        hash = 31 * hash + (hasExtremelyHazardousDiseases ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "photoPath='" + photoPath + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", identityDocument=" + identityDocument +
                ", homeAddress=" + homeAddress +
                ", inCaseOfEmergencyContactPersonInfo='" + inCaseOfEmergencyContactPersonInfo + '\'' +
                ", inCaseOfEmergencyContactPersonPhone='" + inCaseOfEmergencyContactPersonPhone + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", rhBloodGroup='" + rhBloodGroup + '\'' +
                ", disabilityDegree='" + disabilityDegree + '\'' +
                ", transportationStatus='" + transportationStatus + '\'' +
                ", hasAllergicReactions=" + hasAllergicReactions +
                ", hasExtremelyHazardousDiseases=" + hasExtremelyHazardousDiseases +
                '}';
    }
}
