package edu.uptc.swii.shiftCommandService.config.mongoConfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@ReadingConverter
enum DateToZonedDateTime implements Converter<Date, ZonedDateTime> {

    INSTANCE;

    @Override
    public ZonedDateTime convert(Date date) {
        return date.toInstant()
        //ZoneId.of("America/Bogota")
                .atZone(ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.MILLIS);
    }
}