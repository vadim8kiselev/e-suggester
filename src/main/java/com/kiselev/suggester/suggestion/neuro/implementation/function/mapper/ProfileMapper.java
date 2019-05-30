package com.kiselev.suggester.suggestion.neuro.implementation.function.mapper;

public enum ProfileMapper {

    SPORT(1F, "Спорт"),
    MUSIC(2F, "Музыка"),
    FOOTBALL(3F, "Футбол");

    private Float index;

    private String name;

    ProfileMapper(Float index, String name) {
        this.index = index;
        this.name = name;
    }

    public static ProfileMapper name(String name) {
        for (ProfileMapper profileMapper : values()) {
            if (profileMapper.name.equalsIgnoreCase(name)) {
                return profileMapper;
            }
        }
        return null;
    }

    public static Float nameToIndex(String name) {
        ProfileMapper profileMapper = name(name);
        return profileMapper == null ? 0F : profileMapper.index;
    }
}
