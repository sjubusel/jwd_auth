package by.epamtc.jwd.auth.model.user_info;

public enum TransportationStatus {
    WALKING("передвигается самостоятельно"),
    WHEELCHAIR("на инвалидной-коляске"),
    STRETCHER("на носилках");

    private String description;

    TransportationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
