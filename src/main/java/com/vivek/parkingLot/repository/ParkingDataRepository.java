package com.vivek.parkingLot.repository;

import java.util.ArrayList;
import java.util.List;

import com.vivek.parkingLot.entity.ParkingFloor;
import com.vivek.parkingLot.entity.ParkingSlot;
import com.vivek.parkingLot.entity.VehicleType;


public class ParkingDataRepository {
    List<ParkingSlot> parkingSlotData;
    List<ParkingFloor> parkingFloors;
     
    public List<ParkingFloor> initialiseData(int noOfFloors, int noOfSlots){
        parkingFloors = new ArrayList<>(noOfFloors);
        for(int i = 0; i < noOfFloors; i++){
            initialiseSlots(noOfSlots);
            List<ParkingSlot> parkingSlots = getAllParkingSlotData(i);
            parkingFloors.add(new ParkingFloor(parkingSlots));
        }
        return parkingFloors;
    }

    private List<ParkingSlot> getAllParkingSlotData(int floorId) {
        parkingSlotData.forEach(parkingSlot -> parkingSlot.setFloorId(floorId));
        return parkingSlotData;
    }

    public void initialiseSlots(int noOfSlots) {
        parkingSlotData = new ArrayList<>(noOfSlots);
        if(noOfSlots >= 1)
            parkingSlotData.add(getTruckData());
        if(noOfSlots >= 3)
            for(int i = 1; i < 3; i++)
                parkingSlotData.add(getBikeData(i));
        if(noOfSlots > 3)
            for(int i = 3; i < noOfSlots; i++)
                parkingSlotData.add(getCarData(i));
    }

    private ParkingSlot getCarData(int i) {
        return new ParkingSlot(i, true, VehicleType.CAR);
    }

    private ParkingSlot getBikeData(int i) {
        return new ParkingSlot(i, true , VehicleType.BIKE);
    }

    private ParkingSlot getTruckData() {
        return new ParkingSlot(0, true, VehicleType.TRUCK);
    }
}
