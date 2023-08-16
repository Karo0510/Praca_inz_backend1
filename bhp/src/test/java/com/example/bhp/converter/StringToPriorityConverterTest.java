package com.example.bhp.converter;

import com.example.bhp.entity.RegistryOfAccidents;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StringToPriorityConverterTest {

    @Autowired
    private ConversionService conversionService;

    @Test
    public void testStringToStatusConversion() {
        RegistryOfAccidents.Accident_priority f = conversionService.convert("fatal", RegistryOfAccidents.Accident_priority.class);
        assertEquals(RegistryOfAccidents.Accident_priority.FATAL, f);

        RegistryOfAccidents.Accident_priority n = conversionService.convert("feads", RegistryOfAccidents.Accident_priority.class);
        assertEquals(null, n);
    }


}