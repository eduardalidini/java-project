package java.ryan.flightclass;

public class FlightRequest {
    private String departure;
    private String arrival;
    private String departureDate;

    // Constructor
    public FlightRequest(String departure, String arrival, String departureDate) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
    }

    // Getters and setters

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "FlightRequest{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
