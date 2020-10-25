package by.epamtc.jwd.auth.model.auth_info;

import java.time.LocalDate;

// TODO private static final long serialVersionUID after class completion
// TODO refactor bean
public class RegistrationInfo implements java.io.Serializable {
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;

    public RegistrationInfo() {
    }

    public RegistrationInfo(String login, String password, String email,
            String phoneNumber, String firstName, String middleName,
            String lastName, LocalDate birthday, Gender gender) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegistrationInfo regInfo = (RegistrationInfo) o;

        if (((login == null) && (regInfo.login != null))
                || ((login != null) && (!login.equals(regInfo.login)))) {
            return false;
        }

        if (((password == null) && (regInfo.password != null))
                || ((password != null) && (!password.equals(regInfo.password)))) {
            return false;
        }

        if (((email == null) && (regInfo.email != null))
                || ((email != null) && (!email.equals(regInfo.email)))) {
            return false;
        }

        if (((phoneNumber == null) && (regInfo.phoneNumber != null))
                || ((phoneNumber != null)
                && (!phoneNumber.equals(regInfo.phoneNumber)))) {
            return false;
        }

        if (((firstName == null) && (regInfo.firstName != null))
                || ((firstName != null) && (!firstName.equals(regInfo.firstName)))) {
            return false;
        }

        if (((middleName == null) && (regInfo.middleName != null))
                || ((middleName != null) && (!middleName.equals(regInfo.middleName)))) {
            return false;
        }

        if (((lastName == null) && (regInfo.lastName != null))
                || ((lastName != null) && (!lastName.equals(regInfo.lastName)))) {
            return false;
        }

        if (((birthday == null) && (regInfo.birthday != null))
                || ((birthday != null) && (!birthday.equals(regInfo.birthday)))) {
            return false;
        }

        return ((gender != null) || (regInfo.gender == null))
                && ((gender == null) || (gender.equals(regInfo.gender)));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (login != null ? login.hashCode() : 0);
        hash = 31 * hash + (password != null ? password.hashCode() : 0);
        hash = 31 * hash + (email != null ? email.hashCode() : 0);
        hash = 31 * hash + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        hash = 31 * hash + (firstName != null ? firstName.hashCode() : 0);
        hash = 31 * hash + (middleName != null ? middleName.hashCode() : 0);
        hash = 31 * hash + (lastName != null ? lastName.hashCode() : 0);
        hash = 31 * hash + (birthday != null ? birthday.hashCode() : 0);
        hash = 31 * hash + (gender != null ? gender.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "RegistrationInfo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                '}';
    }
}
