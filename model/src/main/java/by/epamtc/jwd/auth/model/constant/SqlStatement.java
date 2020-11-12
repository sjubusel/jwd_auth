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
    public static final String SELECT_MEDICAL_HISTORY_PERMISSIONS
            = "SELECT rec.record_id,\n" +
            "       rec.recipient_id,\n" +
            "       p.first_name,\n" +
            "       p.middle_name,\n" +
            "       p.last_name,\n" +
            "       rec.permission_datetime,\n" +
            "       rec.cancellation_datetime,\n" +
            "       rec.cancellation_reason\n" +
            "FROM hospital.medical_history_share_permission_records rec\n" +
            "         JOIN hospital.persons p ON rec.recipient_id = p.person_id\n" +
            "WHERE rec.patient_id = ?;";
    public static final String CANCEL_MEDICAL_HISTORY_PERMISSION
            = "UPDATE hospital.medical_history_share_permission_records m\n" +
            "SET m.cancellation_datetime = ?, m.cancellation_reason = 'ОТМЕНЕНО ВЛАДЕЛЬЦЕМ ИНФОРМАЦИИ'\n" +
            "WHERE m.record_id = ?;";
    public static final String UPDATE_PHONE_NUMBER = "UPDATE hospital.persons p\n" +
            "SET p.phone_number = ?\n" +
            "WHERE p.person_id = ?";
    public static final String UPDATE_MARITAL_STATUS = "UPDATE hospital.persons p\n" +
            "SET p.marital_status = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String INSERT_IDENTITY_DOCUMENT
            = "INSERT INTO hospital.identification_documents (person_id, identification_document_type_id, series,\n" +
            "serial_number_of_document, latin_holder_name, latin_holder_surname,\n" +
            "citizenship_id, birth_date, personal_number, gender, place_of_origin,\n" +
            "date_of_issue, date_of_expiry, issue_authority)\n" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_IDENTITY_DOCUMENT
            = "UPDATE hospital.persons p\n" +
            "SET p.identification_document_id = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String INSERT_ADDRESS
            = "INSERT INTO hospital.addresses (zip_code, road_id, house, " +
            "building, room)\n" +
            "VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_ADDRESS = "UPDATE hospital.persons p \n" +
            "SET p.permanent_home_address_id = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_EMERGENCY_PERSON = "UPDATE hospital.persons p\n" +
            "SET p.in_case_of_emergency_person_id = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_EMERGENCY_PHONE = "UPDATE hospital.persons p\n" +
            "SET p.in_case_of_emergency_phone_number = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_BLOOD_TYPE = "UPDATE hospital.persons p \n" +
            "SET p.blood_type = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_RH_BLOOD_GROUP = "UPDATE hospital.persons p\n" +
            "SET p.rhesus_factor = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_DISABILITY_DEGREE = "UPDATE hospital.persons p\n" +
            "SET p.disability_degree = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String UPDATE_TRANSPORTATION_STATUS = "UPDATE hospital.persons p\n" +
            "SET p.transportation_status = ?\n" +
            "WHERE p.person_id = ?;";
    public static final String INSERT_MEDICAL_HISTORY_PERMISSION
            = "INSERT INTO hospital.medical_history_share_permission_records " +
            "(patient_id, recipient_id, permission_datetime)\n" +
            "VALUES (?, ?, CURRENT_TIMESTAMP);";
    public static final String SELECT_ALLERGIC_FOOD_REACTIONS
            = "SELECT arf.reaction_id, ft.food_type_id, ft.name, arf.detection_date, arf.allergic_reaction_description\n" +
            "FROM hospital.allergic_reactions_food arf\n" +
            "         JOIN hospital.food_types ft ON arf.food_type_id = ft.food_type_id\n" +
            "         JOIN hospital.persons p ON arf.person_id = p.person_id\n" +
            "WHERE p.person_id = ?;";
    public static final String SELECT_ALLERGIC_MEDICINE_REACTION
            = "SELECT arm.reaction_id,\n" +
            "       arm.pharmaceutical_substance_id,\n" +
            "       ps.atc_international_nonproprietary_name,\n" +
            "       arm.detection_date,\n" +
            "       arm.allergic_reaction_description\n" +
            "FROM hospital.allergic_reactions_medicine arm\n" +
            "         JOIN hospital.atc_pharmaceutical_substances ps\n" +
            "              ON arm.pharmaceutical_substance_id = ps.atc_pharmaceutical_substance_id\n" +
            "         JOIN hospital.persons p ON arm.person_id = p.person_id\n" +
            "WHERE p.person_id = ?;";
    public static final String INSERT_ALLERGIC_FOOD_REACTION
            = "INSERT INTO hospital.allergic_reactions_food (person_id, food_type_id, detection_date, allergic_reaction_description)\n" +
            "VALUES (?, ?, ?, ?);";
    public static final String INSERT_ALLERGIC_MEDICINE_REACTION
            = "INSERT INTO hospital.allergic_reactions_medicine (person_id, pharmaceutical_substance_id, detection_date,\n" +
            "                                                  allergic_reaction_description)\n" +
            "VALUES (?, ?, ?, ?);";
    public static final String SELECT_EXTREMELY_HAZARDOUS_DISEASE_CASE
            = "SELECT c.case_id,\n" +
            "       c.extremely_hazardous_disease_id,\n" +
            "       d.icd_10_disease_name,\n" +
            "       c.detection_date,\n" +
            "       c.case_description,\n" +
            "       c.recovery_date\n" +
            "FROM hospital.cases_extremely_hazardous_diseases c\n" +
            "         JOIN hospital.extremely_hazardous_diseases ex\n" +
            "              ON c.extremely_hazardous_disease_id = ex.extremely_hazardous_disease_id\n" +
            "         JOIN hospital.icd_10_diseases d ON ex.icd_10_disease_id = d.icd_10_disease_id\n" +
            "WHERE c.person_id = ?;";
    public static final String SELECT_HAZARDOUS_STATUS
            = "SELECT p.extremely_hazardous_diseases FROM hospital.persons p\n" +
            "WHERE p.person_id = ?";
    public static final String INSERT_HAZARDOUS_FLAG_TO_PERSON
            = "UPDATE hospital.persons p\n" +
            "SET p.extremely_hazardous_diseases = FALSE\n" +
            "WHERE p.person_id = ?;";
    public static final String ADD_HAZARDOUS_CASE
            = "INSERT INTO hospital.cases_extremely_hazardous_diseases " +
            "(extremely_hazardous_disease_id, case_description,\n" +
            "detection_date, person_id)\n" +
            "VALUES (?, ?, ?, ?);";
    public static final String INSERT_VISIT = "INSERT INTO " +
            "hospital.visits_to_admission_department\n" +
            "(visit_datetime, person_id, visit_reason, visit_reason_description," +
            " transportation_status)\n" +
            "VALUES (CURRENT_TIMESTAMP, ?, ?, ?, ?);";
    public static final String FETCH_NEW_VISITS
            = "SELECT v2ad.visit_id,\n" +
            "       v2ad.visit_datetime,\n" +
            "       p.last_name,\n" +
            "       p.first_name,\n" +
            "       p.middle_name,\n" +
            "       v2ad.visit_reason_description\n" +
            "from visits_to_admission_department v2ad\n" +
            "         JOIN hospital.persons p ON v2ad.person_id = p.person_id\n" +
            "WHERE v2ad.responsible_doctor_id IS NULL\n" +
            "  AND v2ad.visit_datetime > ?;";
    public static final String SELECT_RESPONSIBLE_DOCTOR
            = "SELECT v.responsible_doctor_id\n" +
            "FROM hospital.visits_to_admission_department v\n" +
            "WHERE v.visit_id = ?;";
    public static final String INSERT_RESPONSIBLE_DOCTOR
            = "UPDATE hospital.visits_to_admission_department v\n" +
            "SET v.responsible_doctor_id = ?\n" +
            "WHERE v.visit_id = ?;";
    public static final String SELECT_VISITS_ON_CONTROL_BY_DOCTOR
            = "SELECT v2ad.visit_id,\n" +
            "       v2ad.visit_datetime,\n" +
            "       p.last_name,\n" +
            "       p.first_name,\n" +
            "       p.middle_name,\n" +
            "       v2ad.visit_reason_description\n" +
            "FROM hospital.visits_to_admission_department v2ad\n" +
            "         JOIN hospital.persons p ON v2ad.person_id = p.person_id\n" +
            "WHERE v2ad.responsible_doctor_id = ?\n" +
            "  AND v2ad.visit_result IS NULL;";
    public static final String SELECT_IF_VISIT_PRESCRIPTION_IS_INCOMPLETE
            = "SELECT vam.visit_id\n" +
            "FROM hospital.visit_applied_medicines vam\n" +
            "WHERE (vam.execution_datetime IS NULL AND vam.patient_disagreement_datetime IS NULL)\n" +
            "  AND vam.visit_id = ?\n" +
            "UNION\n" +
            "SELECT vpr.visit_id\n" +
            "FROM hospital.visit_prescription_records vpr\n" +
            "WHERE (vpr.execution_datetime IS NULL AND vpr.patient_disagreement_datetime IS NULL)\n" +
            "AND vpr.visit_id = ?;";

    private SqlStatement() {
    }
}
