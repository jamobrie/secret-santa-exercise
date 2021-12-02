package com.secretsanta.service;

import com.secretsanta.model.v1.Teammate;
import com.secretsanta.model.v1.Teammates;

public interface SecretSantaService {

    Teammates getAllSecretSantasInGivenYear(Integer year);


    Teammate createNewTeammate(Teammate teammate);
}
