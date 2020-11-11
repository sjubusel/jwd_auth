package by.epamtc.jwd.auth.model.user_info;

import java.io.Serializable;
import java.time.LocalDate;

public class AllergicFoodReaction implements Serializable {
    private static final long serialVersionUID = -1367146403569595925L;

    private int reactionId;
    private int foodTypeId;
    private String foodTypeInfo;
    private LocalDate detectionDate;
    private String allergicDescription;

    public AllergicFoodReaction() {
    }

    public AllergicFoodReaction(int reactionId, int foodTypeId,
            String foodTypeInfo, LocalDate detectionDate, String allergicDescription) {
        this.reactionId = reactionId;
        this.foodTypeId = foodTypeId;
        this.foodTypeInfo = foodTypeInfo;
        this.detectionDate = detectionDate;
        this.allergicDescription = allergicDescription;
    }

    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeInfo() {
        return foodTypeInfo;
    }

    public void setFoodTypeInfo(String foodTypeInfo) {
        this.foodTypeInfo = foodTypeInfo;
    }

    public LocalDate getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(LocalDate detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getAllergicDescription() {
        return allergicDescription;
    }

    public void setAllergicDescription(String allergicDescription) {
        this.allergicDescription = allergicDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AllergicFoodReaction that = (AllergicFoodReaction) o;

        if (reactionId != that.reactionId) {
            return false;
        }
        if (foodTypeId != that.foodTypeId) {
            return false;
        }
        if (foodTypeInfo != null ? !foodTypeInfo.equals(that.foodTypeInfo)
                                 : that.foodTypeInfo != null) {
            return false;
        }
        if (detectionDate != null ? !detectionDate.equals(that.detectionDate)
                                  : that.detectionDate != null) {
            return false;
        }
        return (allergicDescription == null)
               ? (that.allergicDescription == null)
               : allergicDescription.equals(that.allergicDescription);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + reactionId;
        hash = 31 * hash + foodTypeId;
        hash = 31 * hash + (foodTypeInfo != null ? foodTypeInfo.hashCode()
                                                 : 0);
        hash = 31 * hash + (detectionDate != null ? detectionDate.hashCode()
                                                  : 0);
        hash = 31 * hash + (allergicDescription != null
                            ? allergicDescription.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AllergicFoodReaction{" +
                "reactionId=" + reactionId +
                ", foodTypeId=" + foodTypeId +
                ", foodTypeInfo='" + foodTypeInfo + '\'' +
                ", detectionDate=" + detectionDate +
                ", allergicDescription='" + allergicDescription + '\'' +
                '}';
    }
}
