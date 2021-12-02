package com.secretsanta.controller;

import com.secretsanta.api.v1.SecretsantaApi;
import com.secretsanta.model.v1.Teammates;
import com.secretsanta.service.SecretSantaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecretSantaController implements SecretsantaApi {

    private final SecretSantaService secretSantaService;

    @Override
    public ResponseEntity<Teammates> getAllSecretSanta(Integer year) {
        Teammates teammateList = secretSantaService.getAllSecretSantasInGivenYear(year);
        return ResponseEntity.ok(teammateList);
    }

    @Override
    public ResponseEntity<Teammates> registerTeammate() {
        return null;
    }

}
