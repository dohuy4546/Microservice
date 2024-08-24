package com.husony.device_service.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dluxogrmn",
                        "api_key", "958699327246893",
                        "api_secret", "sL64XQ_2Djr4kg8Gi2vZyR-f5MA",
                        "secure", true));
        return cloudinary;
    }
}
