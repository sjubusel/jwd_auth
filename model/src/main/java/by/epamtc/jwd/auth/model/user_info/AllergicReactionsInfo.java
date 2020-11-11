package by.epamtc.jwd.auth.model.user_info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AllergicReactionsInfo implements Serializable {
    private static final long serialVersionUID = -3400153394511042549L;

    private List<AllergicFoodReaction> allergicFoodReactions
            = new ArrayList<>();
    private List<AllergicMedicineReaction> allergicMedicineReactions
            = new ArrayList<>();

    public AllergicReactionsInfo(List<AllergicFoodReaction> allergicFoodReactions,
            List<AllergicMedicineReaction> allergicMedicineReactions) {
        this.allergicFoodReactions = allergicFoodReactions;
        this.allergicMedicineReactions = allergicMedicineReactions;
    }

    public AllergicReactionsInfo() {
    }

    public List<AllergicFoodReaction> getAllergicFoodReactions() {
        return allergicFoodReactions;
    }

    public void setAllergicFoodReactions(List<AllergicFoodReaction>
            allergicFoodReactions) {
        this.allergicFoodReactions = allergicFoodReactions;
    }

    public List<AllergicMedicineReaction> getAllergicMedicineReactions() {
        return allergicMedicineReactions;
    }

    public void setAllergicMedicineReactions(List<AllergicMedicineReaction>
            allergicMedicineReactions) {
        this.allergicMedicineReactions = allergicMedicineReactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AllergicReactionsInfo that = (AllergicReactionsInfo) o;

        if ((allergicFoodReactions == null && that.allergicFoodReactions != null)
                || (allergicFoodReactions != null && that.allergicFoodReactions == null)) {
            return false;
        }

        if (allergicFoodReactions.size() != that.allergicFoodReactions.size()) {
            return false;
        }

        for (int i = 0; i < allergicFoodReactions.size(); i++) {
            if (!allergicFoodReactions.get(i).equals(that.allergicFoodReactions.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + ((allergicFoodReactions != null)
                            ? allergicFoodReactions.hashCode()
                            : 0);
        hash = 31 * hash + ((allergicMedicineReactions != null)
                            ? allergicMedicineReactions.hashCode()
                            : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AllergicReactionsInfo{" +
                "allergicFoodReactions=" + Arrays.toString(allergicFoodReactions
                .toArray()) +
                ", allergicMedicineReactions=" + Arrays.toString
                (allergicMedicineReactions.toArray()) +
                '}';
    }
}
