package com.kiselev.suggester.data.model.entity.enums;

public enum Section {

    FASHION(1, "Fashion"),
    KIDS(2, "Kids & Babies"),
    ELECTRONICS(3, "Electronics"),
    COMPUTERS(4, "Computers"),
    VEHICLES(5, "Vehicles"),
    ESTATE(6, "Real Estate"),
    HOME(7, "Home & Country House"),
    BEAUTY(8, "Beauty & Health"),
    SPORTS(9, "Sports & Recreation"),
    GIFTS(10, "Spare Time & Gifts"),
    PETS(11, "Pets"),
    FOOD(12, "Food"),
    SERVICES(13, "Services");

    private Integer code;

    private String title;

    Section(Integer code, String title) {
        this.code = code;
        this.title = title;
    }

    public static String byCode(Integer code) {
        for (Section section : values()) {
            if (section.code.equals(code)) {
                return section.title;
            }
        }
        throw new RuntimeException("Not found: " + code);
    }

    public static Integer byTitle(String title) {
        for (Section section : values()) {
            if (section.title.equals(title)) {
                return section.code;
            }
        }
        throw new RuntimeException("Not found: " + title);
    }
}
