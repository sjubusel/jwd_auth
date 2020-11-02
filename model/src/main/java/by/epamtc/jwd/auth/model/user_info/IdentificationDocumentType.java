package by.epamtc.jwd.auth.model.user_info;

public enum IdentificationDocumentType {
    PASSPORT(1), BELARUS_RESIDENCE_VISA(2), REFUGEE_IDENTITY_CARD(3);

    private int typeId;
    private String description;

    IdentificationDocumentType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
