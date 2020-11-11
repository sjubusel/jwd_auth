package by.epamtc.jwd.auth.model.user_info;

import java.io.Serializable;
import java.time.LocalDate;

public class AllergicMedicineReaction implements Serializable {
    private static final long serialVersionUID = -2125682709149065573L;

    private int reactionId;
    private int medicineId;
    private String medicineDescription;
    private LocalDate detectionDate;
    private String allergicReaction;

    public AllergicMedicineReaction(int reactionId, int medicineId,
            String medicineDescription, LocalDate detectionDate,
            String allergicReaction) {
        this.reactionId = reactionId;
        this.medicineId = medicineId;
        this.medicineDescription = medicineDescription;
        this.detectionDate = detectionDate;
        this.allergicReaction = allergicReaction;
    }

    public AllergicMedicineReaction() {
    }

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public LocalDate getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(LocalDate detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getAllergicReaction() {
        return allergicReaction;
    }

    public void setAllergicReaction(String allergicReaction) {
        this.allergicReaction = allergicReaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AllergicMedicineReaction that = (AllergicMedicineReaction) o;

        if (reactionId != that.reactionId) {
            return false;
        }
        if (medicineId != that.medicineId) {
            return false;
        }
        if ((medicineDescription != null)
            ? !medicineDescription.equals(that.medicineDescription)
            : that.medicineDescription != null) {
            return false;
        }
        if ((detectionDate != null)
            ? !detectionDate.equals(that.detectionDate)
            : that.detectionDate != null) {
            return false;
        }
        return (allergicReaction != null)
               ? allergicReaction.equals(that.allergicReaction)
               : that.allergicReaction == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + reactionId;
        hash = 31 * hash + medicineId;
        hash = 31 * hash + (medicineDescription != null
                            ? medicineDescription.hashCode()
                            : 0);
        hash = 31 * hash + (detectionDate != null
                            ? detectionDate.hashCode()
                            : 0);
        hash = 31 * hash + (allergicReaction != null
                            ? allergicReaction.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AllergicMedicineReaction{" +
                "reactionId=" + reactionId +
                ", medicineId=" + medicineId +
                ", medicineDescription='" + medicineDescription + '\'' +
                ", detectionDate=" + detectionDate +
                ", allergicReaction='" + allergicReaction + '\'' +
                '}';
    }
}
