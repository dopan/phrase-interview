package com.phrase.ldopan.assignment.controller;

import com.phrase.ldopan.assignment.model.Config;
import com.phrase.ldopan.assignment.service.ConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ConfigController {
    @Autowired
    ConfigService configService;

    @GetMapping("/config")
    public String config(Model model) {
        if (!configService.getConfig().isPresent())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User configuration not found!");
        Config current = configService.getConfig().get();
        model.addAttribute("currentConfig", current);
        model.addAttribute("config", new Config(current.getUsername(), current.getPassword())); // update config candidate
        return "config";
    }

    @PostMapping("/config")
    public String editConfig(@Valid Config config, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            configService.updateConfig(config);
        }
        model.addAttribute("currentConfig", configService.getConfig().get());
        return "config";
    }

}
