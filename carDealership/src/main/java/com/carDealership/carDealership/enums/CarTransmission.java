package com.carDealership.carDealership.enums;

import lombok.Getter;

@Getter
public enum CarTransmission {
    AUTO_7("7-stopniowa, automatyczna"),
    AUTO_1("1-stopniowa, automatyczna"),
    AUTO_8("8-stopniowa, automatyczna"),
    MANUAL_6("6-biegowa skrzynia manualna");

    private final String displayName;

    CarTransmission(String displayName) {
        this.displayName = displayName;
    }

    public static CarTransmission fromDisplayName(String displayName) {
        for (CarTransmission type : values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown car type: " + displayName);
    }
}
