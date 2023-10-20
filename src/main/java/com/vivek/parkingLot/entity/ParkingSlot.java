package com.vivek.parkingLot.entity;

public class ParkingSlot {
    int floorId;
    int slotId;
    Vehicle vehicle;
    boolean isFree;
    VehicleType vehicleType;
    public ParkingSlot(int slotId, boolean isFree, VehicleType vehicleType) {
        this.slotId = slotId;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }
    public int getFloorId() {
        return floorId;
    }
    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
    public int getSlotId() {
        return slotId;
    }
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public boolean isFree() {
        return isFree;
    }
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
     
}
