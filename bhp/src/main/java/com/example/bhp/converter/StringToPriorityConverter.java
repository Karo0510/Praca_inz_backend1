package com.example.bhp.converter;

import com.example.bhp.entity.RegistryOfAccidents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToPriorityConverter implements Converter<String, RegistryOfAccidents.Accident_priority> {

    @Override
    public com.example.bhp.entity.RegistryOfAccidents.Accident_priority convert(String source) {

        String sourceUpper = source.toUpperCase();

        try {
            return com.example.bhp.entity.RegistryOfAccidents.Accident_priority.valueOf(sourceUpper);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
