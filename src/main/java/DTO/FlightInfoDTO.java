/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author jacobfolkehildebrandt
 */
public class FlightInfoDTO {
    private String id;
    private String startDestination;
    private String endDestination;
    private String departure;
    private String arrival;
    private long duration;
    private long price;
    private String deeplinkUrl;

    public FlightInfoDTO() {
    }

    public FlightInfoDTO(String id, String startDestination, String endDestination, String departure, String arrival, long duration, long price, String deeplinkUrl) {
        this.id = id;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.price = price;
        this.deeplinkUrl = deeplinkUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDeeplinkUrl() {
        return deeplinkUrl;
    }

    public void setDeeplinkUrl(String deeplinkUrl) {
        this.deeplinkUrl = deeplinkUrl;
    }

    @Override
    public String toString() {
        return "FlightInfoDTO{" + "id=" + id + ", startDestination=" + startDestination + ", endDestination=" + endDestination + ", departure=" + departure + ", arrival=" + arrival + ", duration=" + duration + ", price=" + price + ", deeplinkUrl=" + deeplinkUrl + '}';
    }
    
    
}