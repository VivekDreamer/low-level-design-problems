package com.vivek.parkingLot;

import java.util.Scanner;

import com.vivek.parkingLot.entity.Command;
import com.vivek.parkingLot.entity.DisplayType;
import com.vivek.parkingLot.entity.ParkingLot;
import com.vivek.parkingLot.entity.VehicleType;
import com.vivek.parkingLot.service.ParkingLotService;

public class Client 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ParkingLotService parkingLotService = new ParkingLotService();
        while(true){
            Scanner sc = new Scanner(System.in);
            String str = sc.next();
            System.out.println(str);
            Command type = Command.of(str);
            switch(type){
                case CREATE_PARKING_LOT:
                    parkingLotService.createParkingLot(new ParkingLot(sc.next(), sc.nextInt(), sc.nextInt()));
                    break;
                case PARK_VEHICLE:
                    parkingLotService.parkVehicle(VehicleType.valueOf(sc.next()), sc.next(), sc.next());
                    break;
                case UNPARK_VEHICLE:
                    parkingLotService.unParkVehicle(sc.next());
                    break;
                case DISPLAY:
                    parkingLotService.display(DisplayType.of(sc.next()), VehicleType.valueOf(sc.next()));
                    break;
                case EXIT:
                    return;
            }
        }
    }
}
