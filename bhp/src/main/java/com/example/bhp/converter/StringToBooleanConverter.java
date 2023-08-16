package com.example.bhp.converter;

import com.example.bhp.entity.RegistryOfAccidents;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToBooleanConverter implements Converter<String, Boolean> {
    @Override
    public Boolean convert(String source) {
        try {
            return Boolean.valueOf(source.toLowerCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
