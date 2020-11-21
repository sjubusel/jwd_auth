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
    public static final String SELECT_FULL_VISIT
            = "SELECT v2ad.visit_id,\n" +
            "       v2ad.visit_datetime,\n" +
            "       p.last_name,\n" +
            "       p.first_name,\n" +
            "       p.middle_name,\n" +
            "       v2ad.visit_reason_description,\n" +
            "       v2ad.person_id,\n" +
            "       v2ad.visit_reason,\n" +
            "       v2ad.responsible_doctor_id,\n" +
            "       dp.last_name,\n" +
            "       dp.first_name,\n" +
            "       dp.middle_name,\n" +
            "       v2ad.transportation_status,\n" +
            "       v2ad.responsible_paramedical_staff_id,\n" +
            "       paraPerson.last_name,\n" +
            "       paraPerson.first_name,\n" +
            "       paraPerson.middle_name,\n" +
            "       v2ad.complaints_description,\n" +
            "       v2ad.visit_result,\n" +
            "       v2ad.hospitalization_department_id,\n" +
            "       hospDep.name\n" +
            "\n" +
            "FROM hospital.visits_to_admission_department v2ad\n" +
            "         JOIN hospital.persons p ON v2ad.person_id = p.person_id\n" +
            "\n" +
            "         JOIN hospital.staff d ON v2ad.responsible_doctor_id = d.staff_id\n" +
            "         JOIN hospital.persons dp ON d.person_id = dp.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff paraStaff ON v2ad.responsible_paramedical_staff_id = paraStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons paraPerson ON paraStaff.person_id = paraPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital_departments hospDep\n" +
            "                         ON v2ad.hospitalization_department_id = hospDep.hospital_department_id\n" +
            "\n" +
            "\n" +
            "WHERE v2ad.visit_id = ?;";
    public static final String SELECT_VISIT_DIAGNOSES
            = "SELECT vdr.diagnosis_id,\n" +
            "       vdr.diagnosis_datetime,\n" +
            "       vdr.disease_id,\n" +
            "       d.icd_10_disease_name,\n" +
            "       vdr.diagnosis_description,\n" +
            "       vdr.established_diagnosis_staff_id,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vdr.cancellation_datetime,\n" +
            "       vdr.cancellation_staff_id,\n" +
            "       cancelDoctorAsPerson.first_name,\n" +
            "       cancelDoctorAsPerson.middle_name,\n" +
            "       cancelDoctorAsPerson.last_name,\n" +
            "       vdr.cancellation_reason\n" +
            "\n" +
            "\n" +
            "FROM hospital.visit_diagnosis_records vdr\n" +
            "         JOIN hospital.visits_to_admission_department v2ad ON vdr.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.icd_10_diseases d ON vdr.disease_id = d.icd_10_disease_id\n" +
            "\n" +
            "         JOIN hospital.staff doctorAsStaff ON vdr.established_diagnosis_staff_id = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff cancelDoctorAsStaff ON vdr.cancellation_staff_id = cancelDoctorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons cancelDoctorAsPerson\n" +
            "                         ON cancelDoctorAsStaff.person_id = cancelDoctorAsPerson.person_id\n" +
            "WHERE v2ad.person_id = ?;";
    public static final String SELECT_INNER_HOSPITAL_DIAGNOSES
            = "SELECT dr.diagnosis_id,\n" +
            "       dr.diagnosis_datetime,\n" +
            "       dr.disease_id,\n" +
            "       d.icd_10_disease_name,\n" +
            "       dr.diagnosis_description,\n" +
            "       dr.doctor_id,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       dr.cancellation_datetime,\n" +
            "       dr.cancellation_staff_id,\n" +
            "       cancelDoctorAsPerson.first_name,\n" +
            "       cancelDoctorAsPerson.middle_name,\n" +
            "       cancelDoctorAsPerson.last_name,\n" +
            "       dr.cancellation_reason\n" +
            "\n" +
            "\n" +
            "FROM hospital.diagnosis_records dr\n" +
            "         JOIN hospital.medical_charts mc ON dr.medical_chart_id = mc.medical_chart_id\n" +
            "         JOIN hospital.icd_10_diseases d ON dr.disease_id = d.icd_10_disease_id\n" +
            "\n" +
            "         JOIN hospital.staff doctorAsStaff ON dr.doctor_id = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff cancelDoctorAsStaff ON dr.cancellation_staff_id = cancelDoctorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons cancelDoctorAsPerson\n" +
            "                         ON cancelDoctorAsStaff.person_id = cancelDoctorAsPerson.person_id\n" +
            "\n" +
            "WHERE mc.person_id = ?;";
    public static final String SELECT_MEDICINE_PRESCRIPTIONS_BY_VISIT
            = "SELECT vam.prescription_id,\n" +
            "       vam.visit_id,\n" +
            "       vam.applied_medicine_id,\n" +
            "       m.name,\n" +
            "       dft.name,\n" +
            "       mc.dosage_mg,\n" +
            "       m.dosage_ml,\n" +
            "       vam.prescription_datetime,\n" +
            "       vam.prescribing_doctor,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vam.dosage_quantity,\n" +
            "       vam.dosage_measure_unit_id,\n" +
            "       vam.application_datetime,\n" +
            "       vam.executor_staff,\n" +
            "       executorStaffAsPerson.first_name,\n" +
            "       executorStaffAsPerson.middle_name,\n" +
            "       executorStaffAsPerson.last_name,\n" +
            "       vam.execution_datetime,\n" +
            "       vam.execution_description,\n" +
            "       vam.patient_agreement_mark,\n" +
            "       vam.patient_disagreement_description,\n" +
            "       vam.patient_disagreement_datetime\n" +
            "\n" +
            "FROM hospital.visit_applied_medicines vam\n" +
            "         JOIN hospital.medicines m " +
            "ON vam.applied_medicine_id = m.medicine_id\n" +
            "         JOIN hospital.dosage_form_types dft " +
            "ON m.dosage_form_type_id = dft.dosage_form_type_id\n" +
            "         JOIN hospital.medicine_components mc " +
            "ON m.main_medicine_component_id = mc.medicine_component_id\n" +
            "\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vam.prescribing_doctor = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsPerson.person_id = doctorAsStaff.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff executorStaffAsStaff " +
            "ON vam.executor_staff = executorStaffAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorStaffAsPerson\n" +
            "ON executorStaffAsPerson.person_id = executorStaffAsStaff.person_id\n" +
            "\n" +
            "WHERE vam.visit_id = ?;";
    public static final String SELECT_NON_MEDICINE_PRESCRIPTIONS_BY_VISIT
            = "SELECT vpr.prescription_id,\n" +
            "       vpr.visit_id,\n" +
            "       vpr.prescription_datetime,\n" +
            "       vpr.prescribing_staff,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vpr.prescription_description,\n" +
            "       vpr.responsible_paramedical_staff_id,\n" +
            "       responsibleAsPerson.first_name,\n" +
            "       responsibleAsPerson.middle_name,\n" +
            "       responsibleAsPerson.last_name,\n" +
            "       vpr.executor_staff,\n" +
            "       executorAsPerson.first_name,\n" +
            "       executorAsPerson.middle_name,\n" +
            "       executorAsPerson.last_name,\n" +
            "       vpr.execution_datetime,\n" +
            "       vpr.execution_result,\n" +
            "       vpr.patient_agreement_mark,\n" +
            "       vpr.patient_disagreement_description,\n" +
            "       vpr.patient_disagreement_datetime\n" +
            "\n" +
            "\n" +
            "FROM hospital.visit_prescription_records vpr\n" +
            "         JOIN hospital.staff doctorAsStaff ON vpr.prescribing_staff = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff responsibleAsStaff\n" +
            "                         ON vpr.responsible_paramedical_staff_id = responsibleAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons responsibleAsPerson\n" +
            "                         ON responsibleAsStaff.person_id = responsibleAsPerson.person_id\n" +
            "         LEFT OUTER JOIN hospital.staff executorAsStaff ON vpr.executor_staff = executorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorAsPerson ON executorAsPerson.person_id = executorAsStaff.person_id\n" +
            "WHERE vpr.visit_id = ?;";
    public static final String UPDATE_COMPLAINTS_BY_VISIT
            = "UPDATE hospital.visits_to_admission_department v2ad\n" +
            "SET v2ad.complaints_description = ?\n" +
            "WHERE v2ad.visit_id = ?;";
    public static final String INSERT_VISIT_DIAGNOSIS
            = "INSERT INTO hospital.visit_diagnosis_records (visit_id," +
            " diagnosis_datetime, disease_id, established_diagnosis_staff_id,\n" +
            " diagnosis_description)\n" +
            "VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?);";
    public static final String INSERT_VISIT_MEDICINE_PRESCRIPTION
            = "INSERT INTO hospital.visit_applied_medicines (visit_id," +
            " applied_medicine_id, prescription_datetime,\n" +
            " application_datetime, dosage_quantity, dosage_measure_unit_id,\n" +
            " prescribing_doctor)\n" +
            "VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?);";
    public static final String SELECT_PATIENT_ID_BY_VISIT_ID
            = "SELECT v2ad.person_id\n" +
            "FROM visits_to_admission_department v2ad\n" +
            "WHERE v2ad.visit_id = ?;";
    public static final String SELECT_IF_THERE_IS_ALLERGY
            = "SELECT arm.allergic_reaction_description\n" +
            "FROM hospital.allergic_reactions_medicine arm\n" +
            "WHERE arm.pharmaceutical_substance_id IN\n" +
            "      (SELECT mc.pharmaceutical_substance_id " +
            "FROM medicine_components mc WHERE mc.medicine_id = ?)\n" +
            "  AND arm.person_id = ?;";
    public static final String INSERT_VISIT_PRESCRIPTION
            = "INSERT INTO hospital.visit_prescription_records " +
            "(visit_id, prescription_datetime, prescribing_staff,\n" +
            " prescription_description)\n" +
            "VALUES (?, CURRENT_TIMESTAMP, ?, ?);";
    public static final String UPDATE_CANCEL_PRESCRIPTION
            = "UPDATE hospital.visit_prescription_records\n" +
            "SET execution_result   = 'ОТМЕНЕНО ИНИЦИАТОРОМ НАЗНАЧЕНИЯ',\n" +
            "    execution_datetime = CURRENT_TIMESTAMP,\n" +
            "    executor_staff     = ?\n" +
            "WHERE prescription_id = ?;";
    public static final String UPDATE_CANCEL_MED_PRESCRIPTION
            = "UPDATE hospital.visit_applied_medicines\n" +
            "SET executor_staff        = ?,\n" +
            "    execution_datetime    = CURRENT_TIMESTAMP,\n" +
            "    execution_description = 'ОТМЕНЕНО ИНИЦИАТОРОМ НАЗНАЧЕНИЯ'\n" +
            "WHERE prescription_id = ?;";
    public static final String SELECT_MEDICINE_UNEXECUTED_PRESCRIPTIONS
            = "SELECT vam.prescription_id,\n" +
            "       vam.visit_id,\n" +
            "       vam.applied_medicine_id,\n" +
            "       m.name,\n" +
            "       dft.name,\n" +
            "       mc.dosage_mg,\n" +
            "       m.dosage_ml,\n" +
            "       vam.prescription_datetime,\n" +
            "       vam.prescribing_doctor,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vam.dosage_quantity,\n" +
            "       vam.dosage_measure_unit_id,\n" +
            "       vam.application_datetime,\n" +
            "       vam.executor_staff,\n" +
            "       executorStaffAsPerson.first_name,\n" +
            "       executorStaffAsPerson.middle_name,\n" +
            "       executorStaffAsPerson.last_name,\n" +
            "       vam.execution_datetime,\n" +
            "       vam.execution_description,\n" +
            "       vam.patient_agreement_mark,\n" +
            "       vam.patient_disagreement_description,\n" +
            "       vam.patient_disagreement_datetime,\n" +
            "       patient.person_id,\n" +
            "       patient.first_name,\n" +
            "       patient.middle_name,\n" +
            "       patient.last_name\n" +
            "\n" +
            "FROM hospital.visit_applied_medicines vam\n" +
            "         JOIN hospital.medicines m ON vam.applied_medicine_id " +
            "= m.medicine_id\n" +
            "         JOIN hospital.dosage_form_types dft ON m.dosage_form_type_id " +
            "= dft.dosage_form_type_id\n" +
            "         JOIN hospital.medicine_components mc " +
            "ON m.main_medicine_component_id = mc.medicine_component_id\n" +
            "\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vam.prescribing_doctor = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsPerson.person_id = doctorAsStaff.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff executorStaffAsStaff " +
            "ON vam.executor_staff = executorStaffAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorStaffAsPerson\n" +
            " ON executorStaffAsPerson.person_id = executorStaffAsStaff.person_id\n" +
            "\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON vam.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.persons patient " +
            "ON v2ad.person_id = patient.person_id\n" +
            "\n" +
            "WHERE (vam.executor_staff IS NULL\n" +
            "    AND vam.execution_datetime IS NULL\n" +
            "    AND vam.execution_description IS NULL)\n" +
            "  AND (vam.patient_agreement_mark\n" +
            "    AND vam.patient_disagreement_datetime IS NULL\n" +
            "    AND vam.patient_disagreement_description IS NULL)\n" +
            "  AND (v2ad.visit_result IS NULL);";
    public static final String SELECT_MED_PRESCRIPTION_BY_ID
            = "SELECT vam.prescription_id,\n" +
            "       vam.visit_id,\n" +
            "       vam.applied_medicine_id,\n" +
            "       m.name,\n" +
            "       dft.name,\n" +
            "       mc.dosage_mg,\n" +
            "       m.dosage_ml,\n" +
            "       vam.prescription_datetime,\n" +
            "       vam.prescribing_doctor,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vam.dosage_quantity,\n" +
            "       vam.dosage_measure_unit_id,\n" +
            "       vam.application_datetime,\n" +
            "       vam.executor_staff,\n" +
            "       executorStaffAsPerson.first_name,\n" +
            "       executorStaffAsPerson.middle_name,\n" +
            "       executorStaffAsPerson.last_name,\n" +
            "       vam.execution_datetime,\n" +
            "       vam.execution_description,\n" +
            "       vam.patient_agreement_mark,\n" +
            "       vam.patient_disagreement_description,\n" +
            "       vam.patient_disagreement_datetime,\n" +
            "       patient.person_id,\n" +
            "       patient.first_name,\n" +
            "       patient.middle_name,\n" +
            "       patient.last_name\n" +
            "\n" +
            "FROM hospital.visit_applied_medicines vam\n" +
            "         JOIN hospital.medicines m " +
            "ON vam.applied_medicine_id = m.medicine_id\n" +
            "         JOIN hospital.dosage_form_types dft " +
            "ON m.dosage_form_type_id = dft.dosage_form_type_id\n" +
            "         JOIN hospital.medicine_components mc " +
            "ON m.main_medicine_component_id = mc.medicine_component_id\n" +
            "\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vam.prescribing_doctor = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsPerson.person_id = doctorAsStaff.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff executorStaffAsStaff " +
            "ON vam.executor_staff = executorStaffAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorStaffAsPerson\n" +
            "ON executorStaffAsPerson.person_id = executorStaffAsStaff.person_id\n" +
            "\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON vam.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.persons patient " +
            "ON v2ad.person_id = patient.person_id\n" +
            "WHERE vam.prescription_id = ?;";
    public static final String UPDATE_EXECUTE_MED_PRESCRIPTION_BY_ID
            = "UPDATE hospital.visit_applied_medicines vam\n" +
            "SET vam.execution_datetime    = CURRENT_TIMESTAMP,\n" +
            "    vam.execution_description = ?,\n" +
            "    vam.executor_staff        =?\n" +
            "WHERE vam.prescription_id = ?;";
    public static final String SELECT_NON_MEDICINE_UNEXECUTED_PRESCRIPTIONS
            = "SELECT vpr.prescription_id,\n" +
            "       vpr.visit_id,\n" +
            "       vpr.prescription_datetime,\n" +
            "       vpr.prescribing_staff,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vpr.prescription_description,\n" +
            "       vpr.responsible_paramedical_staff_id,\n" +
            "       responsibleAsPerson.first_name,\n" +
            "       responsibleAsPerson.middle_name,\n" +
            "       responsibleAsPerson.last_name,\n" +
            "       vpr.executor_staff,\n" +
            "       executorAsPerson.first_name,\n" +
            "       executorAsPerson.middle_name,\n" +
            "       executorAsPerson.last_name,\n" +
            "       vpr.execution_datetime,\n" +
            "       vpr.execution_result,\n" +
            "       vpr.patient_agreement_mark,\n" +
            "       vpr.patient_disagreement_description,\n" +
            "       vpr.patient_disagreement_datetime,\n" +
            "       patient.person_id,\n" +
            "       patient.first_name,\n" +
            "       patient.middle_name,\n" +
            "       patient.last_name\n" +
            "\n" +
            "FROM hospital.visit_prescription_records vpr\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vpr.prescribing_staff = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff responsibleAsStaff\n" +
            "                         " +
            "ON vpr.responsible_paramedical_staff_id = responsibleAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons responsibleAsPerson\n" +
            "                         " +
            "ON responsibleAsStaff.person_id = responsibleAsPerson.person_id\n" +
            "         LEFT OUTER JOIN hospital.staff executorAsStaff " +
            "ON vpr.executor_staff = executorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorAsPerson " +
            "ON executorAsPerson.person_id = executorAsStaff.person_id\n" +
            "\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON vpr.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.persons patient " +
            "ON v2ad.person_id = patient.person_id\n" +
            "WHERE (vpr.executor_staff IS NULL\n" +
            "    AND vpr.execution_datetime IS NULL\n" +
            "    AND vpr.execution_result IS NULL)\n" +
            "  AND (vpr.patient_agreement_mark\n" +
            "    AND vpr.patient_disagreement_datetime IS NULL\n" +
            "    AND vpr.patient_disagreement_description IS NULL)\n" +
            "  AND (v2ad.visit_result IS NULL)" +
            "AND (vpr.responsible_paramedical_staff_id IS NULL);";
    public static final String UPDATE_CONTROL_PRESCRIPTION
            = "UPDATE hospital.visit_prescription_records vpr\n" +
            "SET vpr.responsible_paramedical_staff_id = ?\n" +
            "WHERE vpr.prescription_id = ?;";
    public static final String
            SELECT_CONTROLLED_NON_MEDICINE_UNEXECUTED_PRESCRIPTIONS
            = "SELECT vpr.prescription_id,\n" +
            "       vpr.visit_id,\n" +
            "       vpr.prescription_datetime,\n" +
            "       vpr.prescribing_staff,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vpr.prescription_description,\n" +
            "       vpr.responsible_paramedical_staff_id,\n" +
            "       responsibleAsPerson.first_name,\n" +
            "       responsibleAsPerson.middle_name,\n" +
            "       responsibleAsPerson.last_name,\n" +
            "       vpr.executor_staff,\n" +
            "       executorAsPerson.first_name,\n" +
            "       executorAsPerson.middle_name,\n" +
            "       executorAsPerson.last_name,\n" +
            "       vpr.execution_datetime,\n" +
            "       vpr.execution_result,\n" +
            "       vpr.patient_agreement_mark,\n" +
            "       vpr.patient_disagreement_description,\n" +
            "       vpr.patient_disagreement_datetime,\n" +
            "       patient.person_id,\n" +
            "       patient.first_name,\n" +
            "       patient.middle_name,\n" +
            "       patient.last_name\n" +
            "\n" +
            "FROM hospital.visit_prescription_records vpr\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vpr.prescribing_staff = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff responsibleAsStaff\n" +
            "ON vpr.responsible_paramedical_staff_id = responsibleAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons responsibleAsPerson\n" +
            "ON responsibleAsStaff.person_id = responsibleAsPerson.person_id\n" +
            "         LEFT OUTER JOIN hospital.staff executorAsStaff " +
            "ON vpr.executor_staff = executorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorAsPerson " +
            "ON executorAsPerson.person_id = executorAsStaff.person_id\n" +
            "\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON vpr.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.persons patient " +
            "ON v2ad.person_id = patient.person_id\n" +
            "WHERE (vpr.executor_staff IS NULL\n" +
            "    AND vpr.execution_datetime IS NULL\n" +
            "    AND vpr.execution_result IS NULL)\n" +
            "  AND (vpr.patient_agreement_mark\n" +
            "    AND vpr.patient_disagreement_datetime IS NULL\n" +
            "    AND vpr.patient_disagreement_description IS NULL)\n" +
            "  AND (v2ad.visit_result IS NULL)\n" +
            "  AND vpr.responsible_paramedical_staff_id = ?;";
    public static final String SELECT_NON_MED_PRESCRIPTION_BY_ID
            = "SELECT vpr.prescription_id,\n" +
            "       vpr.visit_id,\n" +
            "       vpr.prescription_datetime,\n" +
            "       vpr.prescribing_staff,\n" +
            "       doctorAsPerson.first_name,\n" +
            "       doctorAsPerson.middle_name,\n" +
            "       doctorAsPerson.last_name,\n" +
            "       vpr.prescription_description,\n" +
            "       vpr.responsible_paramedical_staff_id,\n" +
            "       responsibleAsPerson.first_name,\n" +
            "       responsibleAsPerson.middle_name,\n" +
            "       responsibleAsPerson.last_name,\n" +
            "       vpr.executor_staff,\n" +
            "       executorAsPerson.first_name,\n" +
            "       executorAsPerson.middle_name,\n" +
            "       executorAsPerson.last_name,\n" +
            "       vpr.execution_datetime,\n" +
            "       vpr.execution_result,\n" +
            "       vpr.patient_agreement_mark,\n" +
            "       vpr.patient_disagreement_description,\n" +
            "       vpr.patient_disagreement_datetime,\n" +
            "       patient.person_id,\n" +
            "       patient.first_name,\n" +
            "       patient.middle_name,\n" +
            "       patient.last_name\n" +
            "\n" +
            "FROM hospital.visit_prescription_records vpr\n" +
            "         JOIN hospital.staff doctorAsStaff " +
            "ON vpr.prescribing_staff = doctorAsStaff.staff_id\n" +
            "         JOIN hospital.persons doctorAsPerson " +
            "ON doctorAsStaff.person_id = doctorAsPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff responsibleAsStaff\n" +
            "                         " +
            "ON vpr.responsible_paramedical_staff_id = responsibleAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons responsibleAsPerson\n" +
            "                         " +
            "ON responsibleAsStaff.person_id = responsibleAsPerson.person_id\n" +
            "         LEFT OUTER JOIN hospital.staff executorAsStaff " +
            "ON vpr.executor_staff = executorAsStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons executorAsPerson " +
            "ON executorAsPerson.person_id = executorAsStaff.person_id\n" +
            "\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON vpr.visit_id = v2ad.visit_id\n" +
            "         JOIN hospital.persons patient " +
            "ON v2ad.person_id = patient.person_id\n" +
            "WHERE vpr.prescription_id = ?;";
    public static final String UPDATE_EXECUTE_NON_MED_PRESCRIPTION_BY_ID
            = "UPDATE hospital.visit_prescription_records vpr\n" +
            "SET vpr.execution_datetime = CURRENT_TIMESTAMP,\n" +
            "    vpr.execution_result   = ?,\n" +
            "    vpr.executor_staff     = ?\n" +
            "WHERE vpr.prescription_id = ?\n" +
            "  AND (vpr.execution_datetime IS NULL)\n" +
            "  AND (vpr.patient_disagreement_datetime IS NULL);";
    public static final String SELECT_FULL_VISIT_BY_PERSON_ID
            = "SELECT v2ad.visit_id,\n" +
            "       v2ad.visit_datetime,\n" +
            "       p.last_name,\n" +
            "       p.first_name,\n" +
            "       p.middle_name,\n" +
            "       v2ad.visit_reason_description,\n" +
            "       v2ad.person_id,\n" +
            "       v2ad.visit_reason,\n" +
            "       v2ad.responsible_doctor_id,\n" +
            "       dp.last_name,\n" +
            "       dp.first_name,\n" +
            "       dp.middle_name,\n" +
            "       v2ad.transportation_status,\n" +
            "       v2ad.responsible_paramedical_staff_id,\n" +
            "       paraPerson.last_name,\n" +
            "       paraPerson.first_name,\n" +
            "       paraPerson.middle_name,\n" +
            "       v2ad.complaints_description,\n" +
            "       v2ad.visit_result,\n" +
            "       v2ad.hospitalization_department_id,\n" +
            "       hospDep.name\n" +
            "\n" +
            "FROM hospital.visits_to_admission_department v2ad\n" +
            "         JOIN hospital.persons p ON v2ad.person_id = p.person_id\n" +
            "\n" +
            "         JOIN hospital.staff d ON v2ad.responsible_doctor_id " +
            "= d.staff_id\n" +
            "         JOIN hospital.persons dp ON d.person_id = dp.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital.staff paraStaff " +
            "ON v2ad.responsible_paramedical_staff_id = paraStaff.staff_id\n" +
            "         LEFT OUTER JOIN hospital.persons paraPerson " +
            "ON paraStaff.person_id = paraPerson.person_id\n" +
            "\n" +
            "         LEFT OUTER JOIN hospital_departments hospDep\n" +
            "ON v2ad.hospitalization_department_id = hospDep.hospital_department_id\n" +
            "\n" +
            "\n" +
            "WHERE p.person_id = ?\n" +
            "AND v2ad.visit_result IS NULL\n" +
            "AND v2ad.hospitalization_department_id IS NULL;";

    private SqlStatement() {
    }
}
