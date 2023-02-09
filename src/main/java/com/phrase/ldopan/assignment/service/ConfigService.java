package com.phrase.ldopan.assignment.service;

import com.phrase.ldopan.assignment.model.Config;
import com.phrase.ldopan.assignment.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepo;

    public void updateConfig(Config config) {
        configRepo.save(config);
    }

    public Optional<Config> getConfig() {
        return configRepo.findById(1L);
    }
}