package app.studentsocietyapp.model;

public class Venue {
    private int venueId;
    private String venueName;
    private String location;

    public Venue(int venueId, String venueName, String location) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
    }

    // Getters and setters
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }

    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return venueName;
    }
}
