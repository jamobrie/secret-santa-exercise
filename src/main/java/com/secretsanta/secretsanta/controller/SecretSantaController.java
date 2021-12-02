package com.secretsanta.secretsanta.controller;

import com.secretsanta.secretsanta.service.SecretSantaService;
import lombok.AllArgsConstructor;
import com.secretsanta.secretsanta.model.Teammate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SecretSantaController {

    private final SecretSantaService secretSantaService;

    @RequestMapping("/getAllSecretSanta/{year}")
    public List<Teammate> getAllSecretSanta(@PathVariable Integer year) {

        List<Teammate> teammateList = secretSantaService.getAllSecretSantasInGivenYear(year);

        return teammateList;
    }

}
