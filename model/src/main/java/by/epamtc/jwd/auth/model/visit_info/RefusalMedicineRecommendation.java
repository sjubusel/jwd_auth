package by.epamtc.jwd.auth.model.visit_info;

import java.time.LocalDateTime;

public class RefusalMedicineRecommendation implements java.io.Serializable{
    private static final long serialVersionUID = -1976116610233669206L;

    private int recommendationId;
    private int visitId;
    private LocalDateTime recommendationDateTime;
    private int medicineId;
    private String medicineInfo;
    private String intakeInstructions;

    public RefusalMedicineRecommendation(int recommendationId, int visitId,
            LocalDateTime recommendationDateTime, int medicineId,
            String medicineInfo, String intakeInstructions) {
        this.recommendationId = recommendationId;
        this.visitId = visitId;
        this.recommendationDateTime = recommendationDateTime;
        this.medicineId = medicineId;
        this.medicineInfo = medicineInfo;
        this.intakeInstructions = intakeInstructions;
    }

    public RefusalMedicineRecommendation() {
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(int recommendationId) {
        this.recommendationId = recommendationId;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public LocalDateTime getRecommendationDateTime() {
        return recommendationDateTime;
    }

    public void setRecommendationDateTime(LocalDateTime recommendationDateTime) {
        this.recommendationDateTime = recommendationDateTime;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(String medicineInfo) {
        this.medicineInfo = medicineInfo;
    }

    public String getIntakeInstructions() {
        return intakeInstructions;
    }

    public void setIntakeInstructions(String intakeInstructions) {
        this.intakeInstructions = intakeInstructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RefusalMedicineRecommendation that = (RefusalMedicineRecommendation) o;

        if (recommendationId != that.recommendationId) {
            return false;
        }
        if (visitId != that.visitId) {
            return false;
        }
        if (medicineId != that.medicineId) {
            return false;
        }
        if ((recommendationDateTime != null)
            ? !recommendationDateTime.equals(that.recommendationDateTime)
            : (that.recommendationDateTime != null)) {
            return false;
        }
        if (medicineInfo != null ? !medicineInfo.equals(that.medicineInfo)
                                 : that.medicineInfo != null) {
            return false;
        }
        return (intakeInstructions != null)
               ? intakeInstructions.equals(that.intakeInstructions)
               : (that.intakeInstructions == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + recommendationId;
        hash = 31 * hash + visitId;
        hash = 31 * hash + (recommendationDateTime != null
                            ? recommendationDateTime.hashCode()
                            : 0);
        hash = 31 * hash + medicineId;
        hash = 31 * hash + (medicineInfo != null ? medicineInfo.hashCode()
                                                 : 0);
        hash = 31 * hash + (intakeInstructions != null
                            ? intakeInstructions.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "RefusalMedicineRecommendation{" +
                "recommendationId=" + recommendationId +
                ", visitId=" + visitId +
                ", recommendationDateTime=" + recommendationDateTime +
                ", medicineId=" + medicineId +
                ", medicineInfo='" + medicineInfo + '\'' +
                ", intakeInstructions='" + intakeInstructions + '\'' +
                '}';
    }
}
