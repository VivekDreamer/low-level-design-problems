package com.vivek.parkingLot.entity;

import java.util.HashMap;
import java.util.Map;

public enum DisplayType {
    FREE_COUNT("free_count"),
    FREE_SLOTS("free_slots"),
    OCCUPIED_SLOTS("occupied_slots");
    private final String display;
    DisplayType(String s) { display = s; }
    public String toString() { return this.display; }
    private static final Map<String, DisplayType> map = new HashMap<>(values().length, 1);
    static{
        for(DisplayType c : values()) map.put(c.display, c);
    }
    public static DisplayType of(String name){ return map.get(name); }
}
