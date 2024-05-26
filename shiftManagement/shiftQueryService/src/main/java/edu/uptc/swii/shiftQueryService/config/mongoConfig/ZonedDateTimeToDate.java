package edu.uptc.swii.shiftQueryService.config.mongoConfig;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.ZonedDateTime;
import java.util.Date;

@WritingConverter
enum ZonedDateTimeToDate implements Converter<ZonedDateTime, Date> {

    INSTANCE;
    @Override
    public Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }
}
