package com.carDealership.carDealership.enums;

import lombok.Getter;

@Getter
public enum CarDrivetrainType {
    NAPĘD_TYLNY("Napęd tylny"),
    NA_WSZYSTKIE_KOŁA("na wszystkie koła"),
    NAPĘD_PRZEDNI("Napęd przedni");

    private final String displayName;

    CarDrivetrainType(String displayName) {
        this.displayName = displayName;
    }

    public static CarDrivetrainType fromDisplayName(String displayName) {
        for (CarDrivetrainType type : values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown car type: " + displayName);
    }
}
