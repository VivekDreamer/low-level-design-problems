package com.vivek.freeSlotsFromCalender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
        List<TimeSlot> busySchedule = new ArrayList<>();
        busySchedule.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 9, 0, 0), LocalDateTime.of(2023, 6, 28, 10, 0, 0)));
        busySchedule.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 14, 0, 0), LocalDateTime.of(2023, 6, 28, 16, 0, 0)));
        int slotDurationMinutes = 60;
        LocalDateTime startTime = LocalDateTime.of(2023, 6, 28, 8, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023,6,28,18,0,0);
        List<TimeSlot> freeSlots = findFreeSlots(busySchedule,startTime,endTime,slotDurationMinutes);
        for(TimeSlot timeSlot : freeSlots){
            System.out.println("Free Slot : startTime = "+timeSlot.getStartTime()+" , endTime = "+timeSlot.getEndDateTime());
        }
    }

    private static List<TimeSlot> findFreeSlots(List<TimeSlot> busySchedule, LocalDateTime startTime,
            LocalDateTime endTime, int slotDurationMinutes) {
                busySchedule.sort((t1,t2)->t1.getStartTime().compareTo(t2.getStartTime()));
                List<TimeSlot> freeSlots = new ArrayList<>();
                LocalDateTime currDateTime = startTime;
                for(TimeSlot busySlot : busySchedule){
                    LocalDateTime busyStartTime = busySlot.getStartTime();
                    if(busyStartTime.isAfter(currDateTime)){
                        LocalDateTime freeSlotEndTime = busyStartTime.truncatedTo(ChronoUnit.MINUTES);
                        addFreeSlots(freeSlots,currDateTime,freeSlotEndTime,slotDurationMinutes);
                    }
                    currDateTime = busySlot.getEndDateTime();
                }
                if(currDateTime.isBefore(endTime)){
                    addFreeSlots(freeSlots, currDateTime, endTime, slotDurationMinutes);
                }

        return freeSlots;
    }

    private static void addFreeSlots(List<TimeSlot> freeSlots, LocalDateTime currDateTime,
            LocalDateTime freeSlotEndTime, int slotDurationMinutes) {
                LocalDateTime currStartTime = currDateTime;
                LocalDateTime currEndTime = currStartTime.plusMinutes(slotDurationMinutes);
                while(currEndTime.isBefore(freeSlotEndTime) || currEndTime.isEqual(freeSlotEndTime)){
                    freeSlots.add(new TimeSlot(currStartTime, currEndTime));
                    currStartTime = currEndTime;
                    currEndTime = currStartTime.plusMinutes(slotDurationMinutes);
                }
    }
      
    static class TimeSlot{
        LocalDateTime startTime;
        LocalDateTime endDateTime;
        public TimeSlot(LocalDateTime startTime, LocalDateTime endDateTime) {
            this.startTime = startTime;
            this.endDateTime = endDateTime;
        }
        public LocalDateTime getStartTime() {
            return startTime;
        }
        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }
        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }
        public void setEnDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }
    }
}
