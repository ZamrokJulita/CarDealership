package com.carDealership.carDealership.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    GOTÓWKA("gotówka"),
    KARTA("karta kredytowa"),
    PRZELEW("przelew");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public static PaymentMethod fromDisplayName(String displayName) {
        for (PaymentMethod type : values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown car type: " + displayName);
    }
}
