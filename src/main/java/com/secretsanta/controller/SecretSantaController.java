package com.secretsanta.controller;

import com.secretsanta.model.v1.Teammate;
import com.secretsanta.service.SecretSantaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SecretSantaController {

//    com.secretsanta.api.v1.GetAllSecretSantasApi


    //implements com.secretsanta.api.v1GetAllSecretSantasApi{

    private final SecretSantaService secretSantaService;

    @RequestMapping("/getAllSecretSanta/{year}")
    public List<Teammate> getAllSecretSanta(@PathVariable Integer year) {

        List<Teammate> teammateList = secretSantaService.getAllSecretSantasInGivenYear(year);

        return teammateList;
    }

}
