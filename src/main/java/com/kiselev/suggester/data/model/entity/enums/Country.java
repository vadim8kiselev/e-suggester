package com.kiselev.suggester.data.model.entity.enums;

public enum Country {

    RUSSIA(1, "Russia"),
    BELARUS(2, "Belarus"),
    UKRAINE(3, "Ukraine"),
    USA(4, "USA"),
    MOLDOVA(5, "Moldova"),
    SOUTH_KOREA(6, "South Korea"),
    KAZAKHSTAN(7, "Kazakhstan"),
    INDIA(8, "India"),
    NEW_ZEALAND(9, "New Zealand"),
    FINLAND(10, "Finland"),
    ESTONIA(11, "Estonia"),
    POLAND(12, "Poland"),
    FRANCE(13, "France"),
    GERMANY(14, "Germany"),
    LITHUANIA(15, "Lithuania"),
    PORTUGAL(16, "Portugal"),
    JAPAN(17, "Japan"),
    CZECH_REPUBLIC(18, "Czech Republic"),
    AZERBAIJAN(19, "Azerbaijan"),
    GEORGIA(20, "Georgia"),
    HUNGARY(21, "Hungary"),
    CUBA(22, "Cuba"),
    IRELAND(23, "Ireland"),
    SWEDEN(24, "Sweden"),
    GREECE(25, "Greece"),
    ZIMBABWE(26, "Zimbabwe"),
    TURKEY(27, "Turkey"),
    AUSTRALIA(28, "Australia"),
    CANADA(29, "Canada"),
    UNITED_KINGDOM(30, "United Kingdom"),
    AUSTRIA(31, "Austria"),
    NETHERLANDS(32, "Netherlands"),
    PUERTO_RICO(33, "Puerto Rico"),
    SERBIA(34, "Serbia"),
    JAMAICA(35, "Jamaica"),
    ITALY(36, "Italy"),
    UZBEKISTAN(37, "Uzbekistan"),
    SPAIN(38, "Spain"),
    AFGHANISTAN(39, "Afghanistan"),
    LATVIA(40, "Latvia"),
    MEXICO(41, "Mexico"),
    UNITED_ARAB_EMIRATES(42, "United Arab Emirates"),
    THAILAND(43, "Thailand"),
    BULGARIA(44, "Bulgaria"),
    BONAIRE_SINT_EUSTATIUS_AND_SABA(45, "Bonaire, Sint Eustatius and Saba"),
    ISRAEL(46, "Israel"),
    MOROCCO(47, "Morocco"),
    ARMENIA(48, "Armenia"),
    BAHRAIN(49, "Bahrain");

    private Integer code;

    private String title;

    Country(Integer code, String title) {
        this.code = code;
        this.title = title;
    }

    public static String byCode(Integer code) {
        for (Country country : values()) {
            if (country.code.equals(code)) {
                return country.title;
            }
        }
        return "";
    }

    public static Integer byTitle(String title) {
        for (Country country : values()) {
            if (country.title.equals(title)) {
                return country.code;
            }
        }
        if (!title.isEmpty()) {
            String s = title.toUpperCase().replaceAll(" ", "_").replaceAll("-", "_");
            System.out.println(s + "(1, \"" + title + "\"),");
        }
        return 0;
    }
}
