package by.epamtc.jwd.auth.model.constant;

public final class SqlStatement {
    public static final String SELECT_AUTH_USER_BY_LOGIN = "SELECT au.id, p.first_name, " +
            "p.middle_name, p.last_name, aur.auth_user_role_name, " +
            "au.person_id, au.staff_id, au.password " +
            "FROM hospital.auth_user au " +
            "JOIN hospital.persons p ON au.person_id = p.person_id " +
            "JOIN hospital.auth_user_roles aur " +
            "ON au.role_id = aur.auth_user_role_id " +
            "WHERE au.login = ?";
    public static final String INSERT_PERSON_WHILE_REGISTRATION =
            "INSERT INTO hospital.persons (email, phone_number, first_name, " +
                    "middle_name, last_name, birth_date, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_AUTH_USER_WHILE_REGISTRATION =
            "INSERT INTO hospital.auth_user (login, password, role_id, " +
                    "person_id) VALUES (?, ?, ?, ?)";
    public static final String SELECT_PATIENT_INFO = "SELECT p.photo_path, p.first_name, p.middle_name, p.last_name, p.birth_date, p.gender, p.email, p.phone_number,\n" +
            "        p.marital_status,\n" +
            "        id.identification_document_id, id.identification_document_type_id, idt.name, id.series,\n" +
            "        id.serial_number_of_document, id.latin_holder_name, id.latin_holder_surname, c.full_country_name,\n" +
            "        id.birth_date, id.personal_number, id.gender, id.place_of_origin, id.date_of_issue, id.date_of_expiry,\n" +
            "        id.issue_authority,\n" +
            "        a.address_id, a.zip_code, c2.short_country_name, ret.region_type_name, re.region_name,\n" +
            "        art.area_type_name, ar.area_name, st.settlement_type_name, s.settlement_name, rt.road_type_name, r.road_name,\n" +
            "        a.house, a.building, a.room,\n" +
            "        p.in_case_of_emergency_person_id, p.in_case_of_emergency_phone_number, p.blood_type, p.rhesus_factor,\n" +
            "        p.disability_degree, p.transportation_status, p.allergic_anamnesis_food, p.allergic_anamnesis_medicine,\n" +
            "        p.extremely_hazardous_diseases\n" +
            "FROM hospital.persons as p\n" +
            "         LEFT OUTER JOIN hospital.identification_documents id\n" +
            "              ON p.identification_document_id = id.identification_document_id\n" +
            "         LEFT OUTER JOIN hospital.identification_document_types idt\n" +
            "              ON id.identification_document_type_id = idt.identification_document_type_id\n" +
            "         LEFT OUTER JOIN hospital.countries c ON id.citizenship_id = c.country_id\n" +
            "         LEFT OUTER JOIN hospital.addresses a ON p.permanent_home_address_id = a.address_id\n" +
            "         LEFT OUTER JOIN hospital.roads r ON a.road_id = r.road_id\n" +
            "         LEFT OUTER JOIN hospital.road_types rt ON r.road_type_id = rt.road_type_id\n" +
            "         LEFT OUTER JOIN hospital.settlements s ON r.settlement_id = s.settlement_id\n" +
            "         LEFT OUTER JOIN hospital.settlement_types st ON s.settlement_type_id = st.settlement_type_id\n" +
            "         LEFT OUTER JOIN hospital.areas ar ON s.area_id = ar.area_id\n" +
            "         LEFT OUTER JOIN hospital.area_types art ON ar.area_type_id = art.area_type_id\n" +
            "         LEFT OUTER JOIN hospital.regions re ON ar.region_id = re.region_id\n" +
            "         LEFT OUTER JOIN hospital.region_types ret ON re.region_type_id = ret.region_type_id\n" +
            "         LEFT OUTER JOIN hospital.countries c2 ON re.country_id = c2.country_id\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_PHOTO_PATH = "UPDATE hospital.persons p\n" +
            "SET p.photo_path = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String SELECT_IF_CONTAINS_LOGIN = "SELECT login FROM " +
            "hospital.auth_user WHERE login = ?";
    public static final String SELECT_IF_CONTAINS_EMAIL = "SELECT email FROM " +
            "hospital.persons WHERE email = ?";
    public static final String SELECT_PASSWORD_BY_AUTH_USER_ID =
            "SELECT au.password\n" +
            "FROM auth_user au\n" +
            "WHERE au.id = ?;";
    public static final String UPDATE_EMAIL = "UPDATE hospital.persons p\n" +
            "SET p.email = ? WHERE p.person_id = ?;";
    public static final String UPDATE_PASSWORD = "UPDATE hospital.auth_user au " +
            "SET au.password = ?" +
            "WHERE au.id = ?;";

    private SqlStatement() {
    }
}
