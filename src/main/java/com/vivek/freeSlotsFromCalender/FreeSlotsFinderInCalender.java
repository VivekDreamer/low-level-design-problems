package com.vivek.freeSlotsFromCalender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FreeSlotsFinderInCalender {
    public static void main(String[] args) {
        List<TimeSlot> busySchedule = new ArrayList<>();
        busySchedule.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 9, 0, 0), LocalDateTime.of(2023, 6, 28, 10, 0, 0)));
        busySchedule.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 14, 0, 0), LocalDateTime.of(2023, 6, 28, 16, 0, 0)));
        LocalDateTime startTime = LocalDateTime.of(2023, 6, 28, 8, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 6, 28, 18, 0, 0);
        int slotDurationMin = 60;
        List<TimeSlot> freeSlots = addFreeSlot(busySchedule,startTime,endTime,slotDurationMin);
        for(TimeSlot slot : freeSlots){
            System.out.println("Free Slot : startTime = "+slot.getStarTime()+" , endTime = "+slot.getEndTime());
        }
    }
    private static List<TimeSlot> addFreeSlot(List<TimeSlot> busySchedule, LocalDateTime startTime,
            LocalDateTime endTime, int slotDurationMin) {
                List<TimeSlot> freeSlots = new ArrayList<>();
                LocalDateTime currStartTime = startTime;
                LocalDateTime currEndTime = endTime;
                for(TimeSlot busySlot : busySchedule){
                    LocalDateTime busyStartTime = busySlot.getStarTime();
                    LocalDateTime busyEndTime = busySlot.getEndTime();
                    if(currStartTime.isBefore(busyStartTime)){
                        LocalDateTime freeSlotEndTime = busyStartTime.truncatedTo(ChronoUnit.MINUTES);
                        addToList(freeSlots,currStartTime,freeSlotEndTime,slotDurationMin);
                    }
                    currStartTime = busyEndTime;
                }

                if(currStartTime.isBefore(endTime)){
                    addToList(freeSlots, currStartTime, endTime, slotDurationMin);
                }
        return freeSlots;
    }
    private static void addToList(List<TimeSlot> freeSlots, LocalDateTime currStartTime, LocalDateTime freeSlotEndTime,
            int slotDurationMin) {
            LocalDateTime start = currStartTime;
            LocalDateTime end = currStartTime.plusMinutes(slotDurationMin);
            while(end.isBefore(freeSlotEndTime) || end.isEqual(freeSlotEndTime)){
                freeSlots.add(new TimeSlot(start, end));
                start = end;
                end = end.plusMinutes(slotDurationMin);
            }
    }
    static class TimeSlot{
        LocalDateTime starTime;
        LocalDateTime endTime;
        public TimeSlot(LocalDateTime starTime, LocalDateTime endTime) {
            this.starTime = starTime;
            this.endTime = endTime;
        }
        public LocalDateTime getStarTime() {
            return starTime;
        }
        public LocalDateTime getEndTime() {
            return endTime;
        }
        public void setStarTime(LocalDateTime starTime) {
            this.starTime = starTime;
        }
        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
    }
}
