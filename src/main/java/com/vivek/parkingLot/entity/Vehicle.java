package com.vivek.parkingLot.entity;

public class Vehicle {
    VehicleType vehicleType;
    ParkingSlot ParkingSlot;
    String ticketId;
    String color;
    String vehicleRegisterNum;
    public Vehicle(VehicleType vehicleType, ParkingSlot parkingSlot, String color,
            String vehicleRegisterNum) {
        this.vehicleType = vehicleType;
        ParkingSlot = parkingSlot;
        this.color = color;
        this.vehicleRegisterNum = vehicleRegisterNum;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public ParkingSlot getParkingSlot() {
        return ParkingSlot;
    }
    public void setParkingSlot(ParkingSlot parkingSlot) {
        ParkingSlot = parkingSlot;
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getVehicleRegisterNum() {
        return vehicleRegisterNum;
    }
    public void setVehicleRegisterNum(String vehicleRegisterNum) {
        this.vehicleRegisterNum = vehicleRegisterNum;
    }
    
}
