package com.kiselev.suggester.data.model.converter;

import java.util.List;

public interface Converter<From, To> {

    To convert(From from);

    List<To> convert(List<From> froms);
}
