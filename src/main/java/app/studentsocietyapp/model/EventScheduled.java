package app.studentsocietyapp.model;

import java.util.Date;

public class EventScheduled {
    private int scheduleId;
    private int eventId;
    private int venueId;
    private Date startTime;
    private Date endTime;

    public EventScheduled(int scheduleId, int eventId, int venueId, Date startTime, Date endTime) {
        this.scheduleId = scheduleId;
        this.eventId = eventId;
        this.venueId = venueId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }

    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
}
