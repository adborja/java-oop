package com.edu.cedesistemas.oop.model.vehicle;

// Lesson 3 -- Enumerations
public enum CylinderType {
    STRAIGHT(1),
    INLINE(2),
    V(3),
    FLAT(4);

    private final int value;

    CylinderType(int value) {
        this.value = value;
    }

    public static CylinderType findByValue(int value) {
        CylinderType[] values = values();
        for (CylinderType c : values) {
            if (c.value == value) {
                return c;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
