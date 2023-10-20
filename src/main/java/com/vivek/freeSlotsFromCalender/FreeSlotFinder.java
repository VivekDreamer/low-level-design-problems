package com.vivek.freeSlotsFromCalender;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FreeSlotFinder {
    public static List<TimeSlot> findFreeSlots(List<TimeSlot> busySlots, LocalDateTime startTime, LocalDateTime endTime, int slotDurationMinutes) {
        // Sort the busy slots based on start time
        busySlots.sort((slot1, slot2) -> slot1.getStartTime().compareTo(slot2.getStartTime()));

        List<TimeSlot> freeSlots = new ArrayList<>();

        LocalDateTime currentStartTime = startTime;

        for (TimeSlot busySlot : busySlots) {
            LocalDateTime busyStartTime = busySlot.getStartTime();
            LocalDateTime busyEndTime = busySlot.getEndTime();

            if (busyStartTime.isAfter(currentStartTime)) {
                LocalDateTime freeSlotEndTime = busyStartTime.truncatedTo(ChronoUnit.MINUTES);
                addFreeSlot(freeSlots, currentStartTime, freeSlotEndTime, slotDurationMinutes);
            }

            currentStartTime = busyEndTime;
        }

        if (currentStartTime.isBefore(endTime)) {
            addFreeSlot(freeSlots, currentStartTime, endTime, slotDurationMinutes);
        }

        return freeSlots;
    }

    private static void addFreeSlot(List<TimeSlot> freeSlots, LocalDateTime startTime, LocalDateTime endTime, int slotDurationMinutes) {
        LocalDateTime currentStartTime = startTime;
        LocalDateTime currentEndTime = startTime.plusMinutes(slotDurationMinutes);

        while (currentEndTime.isBefore(endTime) || currentEndTime.isEqual(endTime)) {
            freeSlots.add(new TimeSlot(currentStartTime, currentEndTime));
            currentStartTime = currentEndTime;
            currentEndTime = currentStartTime.plusMinutes(slotDurationMinutes);
        }
    }

    // Helper class to represent a time slot
    public static class TimeSlot {
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }
    }

    // Example usage
    public static void main(String[] args) {
        List<TimeSlot> busySlots = new ArrayList<>();
        busySlots.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 9, 0), LocalDateTime.of(2023, 6, 28, 10, 0)));
        busySlots.add(new TimeSlot(LocalDateTime.of(2023, 6, 28, 14, 0), LocalDateTime.of(2023, 6, 28, 16, 0)));

        LocalDateTime startTime = LocalDateTime.of(2023, 6, 28, 8, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 6, 28, 18, 0);
        int slotDurationMinutes = 60;

        List<TimeSlot> freeSlots = findFreeSlots(busySlots, startTime, endTime, slotDurationMinutes);

        for (TimeSlot freeSlot : freeSlots) {
            System.out.println("Free Slot: " + freeSlot.getStartTime() + " - " + freeSlot.getEndTime());
        }
    }
}

//free slots available
// Free Slot: 2023-06-28T08:00 - 2023-06-28T09:00
// Free Slot: 2023-06-28T10:00 - 2023-06-28T11:00
// Free Slot: 2023-06-28T11:00 - 2023-06-28T12:00
// Free Slot: 2023-06-28T12:00 - 2023-06-28T13:00
// Free Slot: 2023-06-28T13:00 - 2023-06-28T14:00
// Free Slot: 2023-06-28T16:00 - 2023-06-28T17:00
// Free Slot: 2023-06-28T17:00 - 2023-06-28T18:00


//occupied slots
// 28 june 2023 at 9 AM to 28 june 2023 at 10 AM
// 28 june 2023 at 2 PM to 28 june 2023 at 4 PM