package by.epamtc.jwd.auth.model.ajax;

public final class AjaxSqlStatement {

    public static final String SELECT_COUNTRIES = "SELECT h.country_id, h.short_country_name FROM hospital.countries h WHERE h.short_country_name LIKE CONCAT('%', ?, '%')";
    public static final String SELECT_REGIONS = "SELECT r.region_id, r.region_name, rt.region_type_name, c.short_country_name\n" +
            "FROM hospital.regions r\n" +
            "         JOIN hospital.region_types rt ON r.region_type_id = rt.region_type_id\n" +
            "         JOIN hospital.countries c ON r.country_id = c.country_id\n" +
            "WHERE r.country_id = ? AND r.region_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_AREAS = "SELECT a.area_id, a.area_name, at.area_type_name, r.region_name\n" +
            "FROM hospital.areas a\n" +
            "         JOIN hospital.area_types at ON a.area_type_id = at.area_type_id\n" +
            "         JOIN hospital.regions r ON a.region_id = r.region_id\n" +
            "WHERE a.region_id = ?\n" +
            "  AND a.area_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_SETTLEMENTS = "SELECT s.settlement_id, s.settlement_name, st.settlement_type_name, a.area_name\n" +
            "FROM hospital.settlements s\n" +
            "         JOIN hospital.settlement_types st ON s.settlement_type_id = st.settlement_type_id\n" +
            "         JOIN hospital.areas a ON s.area_id = a.area_id\n" +
            "WHERE s.area_id = ?\n" +
            "  AND s.settlement_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_ROADS = "SELECT r.road_id, r.road_name, rt.road_type_name, s.settlement_name\n" +
            "FROM hospital.roads r\n" +
            "         JOIN hospital.road_types rt ON r.road_type_id = rt.road_type_id\n" +
            "         JOIN hospital.settlements s ON r.settlement_id = s.settlement_id\n" +
            "WHERE r.settlement_id = ?\n" +
            "  AND r.road_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_PERSONS = "SELECT p.person_id, p.first_name, p.middle_name, p.last_name\n" +
            "FROM hospital.persons p\n" +
            "WHERE p.first_name LIKE CONCAT('%', ?, '%')\n" +
            "UNION\n" +
            "SELECT p2.person_id, p2.first_name, p2.middle_name, p2.last_name\n" +
            "FROM hospital.persons p2\n" +
            "WHERE p2.middle_name LIKE CONCAT('%', ?, '%')\n" +
            "UNION\n" +
            "SELECT p3.person_id, p3.first_name, p3.middle_name, p3.last_name\n" +
            "FROM hospital.persons p3\n" +
            "WHERE p3.last_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_FOOD_TYPES
            = "SELECT ft.food_type_id, ft.name from hospital.food_types ft\n" +
            "WHERE ft.name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_MEDICINE_TYPES
            = "SELECT ps.atc_pharmaceutical_substance_id, ps.atc_international_nonproprietary_name\n" +
            "FROM hospital.atc_pharmaceutical_substances ps\n" +
            "WHERE ps.atc_international_nonproprietary_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_EXTREMELY_HAZARDOUS_DISEASES
            = "SELECT ehd.extremely_hazardous_disease_id, d.icd_10_disease_name\n" +
            "FROM hospital.extremely_hazardous_diseases ehd\n" +
            "         JOIN hospital.icd_10_diseases d ON ehd.icd_10_disease_id = d.icd_10_disease_id\n" +
            "WHERE d.icd_10_disease_name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_DISEASES
            = "SELECT icdD.icd_10_disease_id, icdD.icd_10_disease_name\n" +
            "FROM hospital.icd_10_diseases icdD\n" +
            "WHERE icdD.icd_10_disease_name LIKE CONCAT('%', ?, '%')";
    public static final String SELECT_MEDICINES
            = "SELECT m.medicine_id, m.name, dft.name, mc.dosage_mg, m.dosage_ml\n" +
            "FROM hospital.medicines m\n" +
            "         JOIN hospital.dosage_form_types dft ON m.dosage_form_type_id = dft.dosage_form_type_id\n" +
            "         JOIN hospital.medicine_components mc ON m.main_medicine_component_id = mc.medicine_component_id\n" +
            "WHERE m.name LIKE CONCAT('%', ?, '%');";
    public static final String SELECT_NUMBER_OF_REFUFAL_REFERENCES_OF_DOCTOR
            = "SELECT COUNT(rr.refusal_reference_id)\n" +
            "FROM hospital.refusal_references rr\n" +
            "WHERE rr.doctor_id = ?;";
    public static final String SELECT_NUMBER_OF_REFUFAL_REFERENCES_OF_PATIENT
            = "SELECT COUNT(rr.refusal_reference_id)\n" +
            "FROM hospital.refusal_references rr\n" +
            "         JOIN hospital.visits_to_admission_department v2ad " +
            "ON rr.visit_id = v2ad.visit_id\n" +
            "WHERE v2ad.person_id = ?;";

    private AjaxSqlStatement() {
    }
}
