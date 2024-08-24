package com.husony.device_service.formatters;

import com.husony.device_service.pojo.Manufacturer;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ManufacturerFormatter implements Formatter<Manufacturer> {
    @Override
    public String print(Manufacturer manu, Locale locale) {
        return String.valueOf(manu.getId());
    }

    @Override
    public Manufacturer parse(String manufacturerId, Locale locale) throws ParseException {
        Manufacturer m = new Manufacturer();
        m.setId(Long.parseLong(manufacturerId));

        return m;
    }
}
