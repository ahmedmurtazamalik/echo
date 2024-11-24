package app.studentsocietyapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private int eventId;
    private int societyId;
    private String eventName;
    private String eventDescription;
    private Integer venueId;  // Nullable for proposed events
    private Date date;
    private String startTime;  // Stored as a formatted string
    private String endTime;    // Stored as a formatted string
    private String approvalStatus;

    // Static formatter for time in HH:mm:ss format
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public Event(int eventId, int societyId, String eventName, String eventDescription, Integer venueId, Date date, Date startTime, Date endTime, String approvalStatus) {
        this.eventId = eventId;
        this.societyId = societyId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.venueId = venueId;
        this.date = date;
        this.startTime = startTime != null ? timeFormat.format(startTime) : null;
        this.endTime = endTime != null ? timeFormat.format(endTime) : null;
        this.approvalStatus = approvalStatus;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Override
    public String toString() {
        return String.format("Event: %s (Start: %s, End: %s)", eventName, startTime, endTime);
    }
}
