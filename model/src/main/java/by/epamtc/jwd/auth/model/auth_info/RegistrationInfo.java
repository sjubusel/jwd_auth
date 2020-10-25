package by.epamtc.jwd.auth.model.auth_info;

import java.time.LocalDate;

// TODO private static final long serialVersionUID after class completion
// TODO refactor bean
public class RegistrationInfo implements java.io.Serializable {
    private String login;
    private byte[] passwordByte;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;
    private Gender gender;
}
