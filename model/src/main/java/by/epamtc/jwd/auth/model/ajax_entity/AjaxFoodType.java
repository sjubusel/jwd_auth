package by.epamtc.jwd.auth.model.ajax_entity;

public class AjaxFoodType implements java.io.Serializable {
    private static final long serialVersionUID = 6122195126890709440L;

    private int foodTypeId;
    private String foodTypeName;

    public AjaxFoodType(int foodTypeId, String foodTypeName) {
        this.foodTypeId = foodTypeId;
        this.foodTypeName = foodTypeName;
    }

    public AjaxFoodType() {
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxFoodType that = (AjaxFoodType) o;

        if (foodTypeId != that.foodTypeId) {
            return false;
        }
        return (foodTypeName != null) ? foodTypeName.equals(that.foodTypeName)
                                      : that.foodTypeName == null;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + foodTypeId;
        hash = 31 * hash + (foodTypeName != null ? foodTypeName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxFoodType{" +
                "foodTypeId=" + foodTypeId +
                ", foodTypeName='" + foodTypeName + '\'' +
                '}';
    }
}
