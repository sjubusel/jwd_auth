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


    private AjaxSqlStatement() {
    }
}
