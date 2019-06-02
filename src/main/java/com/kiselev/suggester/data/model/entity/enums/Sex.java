package com.kiselev.suggester.data.model.entity.enums;

public enum Sex {

    UNKNOWN(0, "UNKNOWN"),
    MALE(1, "MALE"),
    FEMALE(2, "FEMALE");

    private Integer code;

    private String title;

    Sex(Integer code, String title) {
        this.code = code;
        this.title = title;
    }

    public static String byCode(Integer code) {
        for (Sex sex : values()) {
            if (sex.code.equals(code)) {
                return sex.title;
            }
        }
        throw new RuntimeException("Not found");
    }

    public static Integer byTitle(String title) {
        for (Sex sex : values()) {
            if (sex.title.equals(title)) {
                return sex.code;
            }
        }
        throw new RuntimeException("Not found");
    }
}
