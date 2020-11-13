package by.epamtc.jwd.auth.model.med_info;

public enum MedicineMeasureUnit {
    TABLET(1, "ТАБЛ.", "ТАБЛЕТКА(И)"),
    AMPOULE(2, "АМП.", "АМПУЛА(Ы)"),
    VIAL(3,"ФЛК.", "ФЛАКОН(Ы)"),
    SYRETTE(4, "ШПРИЦ-ТЮБ.", "ШПРИЦ-ТЮБИК(И)");

    private int measureUnitId;
    private String shortName;
    private String fullName;

    MedicineMeasureUnit(int measureUnitId, String shortName, String fullName) {
        this.measureUnitId = measureUnitId;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    MedicineMeasureUnit() {
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getMeasureUnitId() {
        return measureUnitId;
    }

    @Override
    public String toString() {
        return "MedicineMeasureUnit{" +
                "measureUnitId=" + measureUnitId +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
