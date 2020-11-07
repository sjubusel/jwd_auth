package by.epamtc.jwd.auth.model.ajax;

import java.io.Serializable;

public class AjaxRoad implements Serializable {
    private static final long serialVersionUID = -8457566622009735330L;

    private int roadId;
    private String roadName;
    private String settlementName;

    public AjaxRoad() {
    }

    public AjaxRoad(int roadId, String roadName, String settlementName) {
        this.roadId = roadId;
        this.roadName = roadName;
        this.settlementName = settlementName;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AjaxRoad ajaxRoad = (AjaxRoad) o;

        if (roadId != ajaxRoad.roadId) {
            return false;
        }
        if ((roadName != null) ? !roadName.equals(ajaxRoad.roadName)
                               : (ajaxRoad.roadName != null)) {
            return false;
        }
        return (settlementName != null)
               ? settlementName.equals(ajaxRoad.settlementName)
               : (ajaxRoad.settlementName == null);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + roadId;
        hash = 31 * hash + ((roadName != null) ? roadName.hashCode()
                                             : 0);
        hash = 31 * hash + ((settlementName != null) ? settlementName.hashCode()
                                                   : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "AjaxRoad{" +
                "roadId=" + roadId +
                ", roadName='" + roadName + '\'' +
                ", settlementName='" + settlementName + '\'' +
                '}';
    }
}
