package com.example.bhp.data_initializer;

import com.example.bhp.converter.StringToPriorityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.example.bhp.converter")
public class Converters implements WebMvcConfigurer {

    private ConversionService mConversionService;

    @Autowired
    private StringToPriorityConverter stringToPriorityConverter;

    @Autowired
    public Converters(@Lazy ConversionService conversionService) {
        mConversionService = conversionService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToPriorityConverter);
    }
}




