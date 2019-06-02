package com.kiselev.suggester.suggestion.neuro.implementation.function.implementation;

import com.google.common.collect.Lists;
import com.kiselev.suggester.data.model.entity.Profile;
import com.kiselev.suggester.data.model.entity.enums.City;
import com.kiselev.suggester.data.model.entity.enums.Country;
import com.kiselev.suggester.data.model.entity.enums.Sex;
import com.kiselev.suggester.network.vk.converter.implementation.VKPropertyMapper;
import com.kiselev.suggester.suggestion.neuro.implementation.function.NeuroFunction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class ProfileNeuroFunction implements NeuroFunction<Profile> {

    @Override
    public String process(Profile profile) {
        return profileToVector(profile);
    }

    private String profileToVector(Profile profile) {

        Integer sex = Sex.byTitle(profile.getSex());

        Integer age = toAge(toDate(profile.getBirthday()));

        Integer city = City.byTitle(profile.getCity());

        Integer alcohol = VKPropertyMapper.getSmokingAndAlcohol(profile.getAlcohol());

        Integer smoking = VKPropertyMapper.getSmokingAndAlcohol(profile.getSmoking());

        Integer relation = VKPropertyMapper.getRelation(profile.getRelation());

        Integer political = VKPropertyMapper.getPoliticalViews(profile.getPolitical());

        Integer peopleMain = VKPropertyMapper.getImportantInOthers(profile.getPeopleMain());

        Integer lifeMain = VKPropertyMapper.getPersonalPriority(profile.getLifeMain());

        Integer country = Country.byTitle(profile.getCountry());

        return String.join(";", Lists.newArrayList(
                sex, age, city, country,
                alcohol, smoking, relation,
                political, peopleMain, lifeMain
        ).stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));
    }

    private Integer toAge(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            return Period.between(
                    LocalDate.of(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.DATE)
                    ),
                    LocalDate.now()
            ).getYears();
        } else {
            return 0;
        }
    }

    private Date toDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException exception) {
            return null;
        }
    }
}
