package com.company.maboa.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.company.maboa.services.impl.DBService;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DevProfile {

	private final DBService dbService;

    public DevProfile(DBService dbService) {
        this.dbService = dbService;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @PostConstruct
    public boolean instanceDB() {
        if ("update".equals(ddl)) {
            this.dbService.instanceDB();
        }

        return false;
    }
}
