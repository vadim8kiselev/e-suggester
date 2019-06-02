package com.kiselev.suggester.network.vk.converter.implementation;

public class VKPropertyMapper {

    private enum SmokingAndAlcohol {
        VERY_NEGATIVE(1, "Very negative"),
        NEGATIVE(2, "Negative"),
        COMPROMISABLE(3, "Compromisable"),
        NEUTRAL(4, "Neutral"),
        POSITIVE(5, "Positive");

        private Integer code;

        private String title;

        SmokingAndAlcohol(Integer code, String title) {
            this.code = code;
            this.title = title;
        }
        
        public static String byCode(Integer code) {
            for (SmokingAndAlcohol record : values()) {
                if (record.code.equals(code)) {
                    return record.title;
                }
            }
            return "";
        }

        public static Integer byTitle(String title) {
            for (SmokingAndAlcohol record : values()) {
                if (record.title.equals(title)) {
                    return record.code;
                }
            }
            return 0;
        }
    }

    private enum PoliticalViews {
        COMMUNIST(1, "Communist"),
        SOCIALIST(2, "Socialist"),
        MODERATE(3, "Moderate"),
        LIBERAL(4, "Liberal"),
        CONSERVATIVE(5, "Conservative"),
        MONARCHIST(6, "Monarchist"),
        ULTRACONSERVATIVE(7, "Ultraconservative"),
        APATHETIC(8, "Apathetic"),
        LIBERTARIAN(9, "Libertarian");

        private Integer code;

        private String title;

        PoliticalViews(Integer code, String title) {
            this.code = code;
            this.title = title;
        }

        public static String byCode(Integer code) {
            for (PoliticalViews record : values()) {
                if (record.code.equals(code)) {
                    return record.title;
                }
            }
            return "";
        }

        public static Integer byTitle(String title) {
            for (PoliticalViews record : values()) {
                if (record.title.equals(title)) {
                    return record.code;
                }
            }
            return 0;
        }
    }

    private enum ImportantInOthers {
        INTELLECT(1, "Intellect and creativity"),
        KINDNESS(2, "Kindness and honesty"),
        HEALTH(3, "Health and beauty"),
        WEALTH(4, "Wealth and power"),
        COURAGE(5, "Courage and persistence"),
        HUMOR(6, "Humor and love for life");

        private Integer code;

        private String title;

        ImportantInOthers(Integer code, String title) {
            this.code = code;
            this.title = title;
        }

        public static String byCode(Integer code) {
            for (ImportantInOthers record : values()) {
                if (record.code.equals(code)) {
                    return record.title;
                }
            }
            return "";
        }

        public static Integer byTitle(String title) {
            for (ImportantInOthers record : values()) {
                if (record.title.equals(title)) {
                    return record.code;
                }
            }
            return 0;
        }
    }

    private enum PersonalPriority {
        Family(1, "Family and children"),
        Career(2, "Career and money"),
        Entertainment(3, "Entertainment and leisure"),
        Science(4, "Science and research"),
        Improving(5, "Improving the world"),
        Personal(6, "Personal development"),
        Beauty(7, "Beauty and art"),
        Fame(8, "Fame and influence");

        private Integer code;

        private String title;

        PersonalPriority(Integer code, String title) {
            this.code = code;
            this.title = title;
        }

        public static String byCode(Integer code) {
            for (PersonalPriority record : values()) {
                if (record.code.equals(code)) {
                    return record.title;
                }
            }
            return "";
        }

        public static Integer byTitle(String title) {
            for (PersonalPriority record : values()) {
                if (record.title.equals(title)) {
                    return record.code;
                }
            }
            return 0;
        }
    }

    private enum Relation {
        Single(1, "Single"),
        relationship(2, "In a relationship"),
        Engaged(3, "Engaged"),
        Married(4, "Married"),
        complicated(5, "It's complicated"),
        Actively(6, "Actively searching"),
        love(7, "In love");

        private Integer code;

        private String title;

        Relation(Integer code, String title) {
            this.code = code;
            this.title = title;
        }

        public static String byCode(Integer code) {
            for (Relation record : values()) {
                if (record.code.equals(code)) {
                    return record.title;
                }
            }
            return "";
        }

        public static Integer byTitle(String title) {
            for (Relation record : values()) {
                if (record.title.equals(title)) {
                    return record.code;
                }
            }
            return 0;
        }
    }

    public static String getSmokingAndAlcohol(Integer code) {
        return SmokingAndAlcohol.byCode(code);
    }

    public static String getPoliticalViews(Integer code) {
        return PoliticalViews.byCode(code);
    }

    public static String getImportantInOthers(Integer code) {
        return ImportantInOthers.byCode(code);
    }

    public static String getPersonalPriority(Integer code) {
        return PersonalPriority.byCode(code);
    }

    public static String getRelation(Integer code) {
        return Relation.byCode(code);
    }



    public static Integer getSmokingAndAlcohol(String title) {
        return SmokingAndAlcohol.byTitle(title);
    }

    public static Integer getPoliticalViews(String title) {
        return PoliticalViews.byTitle(title);
    }

    public static Integer getImportantInOthers(String title) {
        return ImportantInOthers.byTitle(title);
    }

    public static Integer getPersonalPriority(String title) {
        return PersonalPriority.byTitle(title);
    }

    public static Integer getRelation(String title) {
        return Relation.byTitle(title);
    }
}
