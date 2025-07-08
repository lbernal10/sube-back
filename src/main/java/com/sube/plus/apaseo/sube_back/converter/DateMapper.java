package com.sube.plus.apaseo.sube_back.converter;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DateMapper {

    @Named("localToZoned")
    public ZonedDateTime asZonedDateTime(LocalDateTime localDateTime) {
        return localDateTime != null
                ? localDateTime.atZone(ZoneId.of("America/Mexico_City"))
                : null;
    }

    @Named("zonedToLocal")
    public LocalDateTime asLocalDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime != null
                ? zonedDateTime.toLocalDateTime()
                : null;
    }
}
