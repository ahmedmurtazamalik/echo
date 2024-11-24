package app.studentsocietyapp.model;

import java.util.Date;

import java.util.Date;

public class Event {
    private int eventId;
    private int societyId;
    private String eventName;
    private String eventDescription;
    private int venueId;  // Nullable for proposed events
    private Date date;
    private Date startTime;
    private Date endTime;
    private String approvalStatus;

    public Event(int eventId, int societyId, String eventName, String eventDescription, Integer venueId, Date date, Date startTime, Date endTime, String approvalStatus) {
        this.eventId = eventId;
        this.societyId = societyId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.venueId = venueId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approvalStatus = approvalStatus;
    }

    // Getters and setters
    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getEventDescription() { return eventDescription; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }

    public Integer getVenueId() { return venueId; }
    public void setVenueId(Integer venueId) { this.venueId = venueId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
}

