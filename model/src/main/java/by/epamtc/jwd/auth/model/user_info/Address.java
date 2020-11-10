package by.epamtc.jwd.auth.model.user_info;

public class Address implements java.io.Serializable {
    private static final long serialVersionUID = -5393531158921800562L;

    private int id;
    private String zipCode;
    private String country;
    private String region;
    private String area;
    private String settlement;
    private String road;
    private String house;
    private String building;
    private String room;

    public Address() {
    }

    public Address(int id, String zipCode, String country, String region,
            String area, String settlement, String road, String house,
            String building, String room) {
        this.id = id;
        this.zipCode = zipCode;
        this.country = country;
        this.region = region;
        this.area = area;
        this.settlement = settlement;
        this.road = road;
        this.house = house;
        this.building = building;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (id != address.id) {
            return false;
        }

        if (((zipCode == null) && (address.zipCode != null))
                || ((zipCode != null) && (!zipCode.equals(address.zipCode)))) {
            return false;
        }
        if (((country == null) && (address.country != null))
                || ((country != null) && (!country.equals(address.country)))) {
            return false;
        }
        if (((region == null) && (address.region != null))
                || ((region != null) && (!region.equals(address.region)))) {
            return false;
        }
        if (((area == null) && (address.area != null))
                || ((area != null) && (!area.equals(address.area)))) {
            return false;
        }
        if (((settlement == null) && (address.settlement != null))
                || ((settlement != null) && (!settlement.equals(address.settlement)))) {
            return false;
        }
        if (((road == null) && (address.road != null))
                || ((road != null) && (!road.equals(address.road)))) {
            return false;
        }
        if (((house == null) && (address.house != null))
                || ((house != null) && (!house.equals(address.house)))) {
            return false;
        }
        if (((building == null) && (address.building != null))
                || ((building != null) && (!building.equals(address.building)))) {
            return false;
        }
        return ((room != null) || (address.room == null))
                && ((room == null) || (room.equals(address.room)));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        hash = 31 * hash + (zipCode != null ? zipCode.hashCode() : 0);
        hash = 31 * hash + (country != null ? country.hashCode() : 0);
        hash = 31 * hash + (region != null ? region.hashCode() : 0);
        hash = 31 * hash + (area != null ? area.hashCode() : 0);
        hash = 31 * hash + (settlement != null ? settlement.hashCode() : 0);
        hash = 31 * hash + (road != null ? road.hashCode() : 0);
        hash = 31 * hash + (house != null ? house.hashCode() : 0);
        hash = 31 * hash + (building != null ? building.hashCode() : 0);
        hash = 31 * hash + (room != null ? room.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", area='" + area + '\'' +
                ", settlement='" + settlement + '\'' +
                ", road='" + road + '\'' +
                ", house='" + house + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
