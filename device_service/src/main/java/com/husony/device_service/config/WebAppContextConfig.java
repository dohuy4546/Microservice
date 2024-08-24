package com.husony.device_service.config;

import com.husony.device_service.formatters.CategoryFormatter;
import com.husony.device_service.formatters.ManufacturerFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppContextConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter());
        registry.addFormatter(new ManufacturerFormatter());
    }
}
