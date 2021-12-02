package com.secretsanta.controller;

import com.secretsanta.api.v1.SecretsantaApi;
import com.secretsanta.model.v1.Teammate;
import com.secretsanta.model.v1.Teammates;
import com.secretsanta.service.SecretSantaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecretSantaController implements SecretsantaApi {

    private final SecretSantaServiceImpl secretSantaServiceImpl;

    @Override
    public ResponseEntity<Teammates> getAllSecretSanta(Integer year) {
        Teammates teammateList = secretSantaServiceImpl.getAllSecretSantasInGivenYear(year);
        return ResponseEntity.ok(teammateList);
    }

    @Override
    public ResponseEntity<Teammate> registerTeammate(Teammate teammate) {
        Teammate teammateToCreate = secretSantaServiceImpl.createNewTeammate(teammate);
        return ResponseEntity.ok(teammateToCreate);
    }

}
