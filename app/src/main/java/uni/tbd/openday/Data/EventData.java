package uni.tbd.openday.Data;

public class EventData {
    public String EventName, EventSummary, EventPlace, EventTime, EventLecturer;
    public String Link;
    public int Image;
    public EventData(String eventName,String eventSummary,String eventPlace,String eventTime,String eventLecturer, String link, int image) {
        this.EventName = eventName;
        this.EventSummary = eventSummary;
        this.EventPlace = eventPlace;
        this.EventTime = eventTime;
        this.EventLecturer = eventLecturer;
        this.Link = link;
        this.Image = image;
    }
    public EventData(String eventName,String eventSummary,String eventPlace,String eventTime,String eventLecturer) {
        this.EventName = eventName;
        this.EventSummary = eventSummary;
        this.EventPlace = eventPlace;
        this.EventTime = eventTime;
        this.EventLecturer = eventLecturer;
    }
}
