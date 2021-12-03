package com.secretsanta.service;

import com.secretsanta.entity.Teammate;
import com.secretsanta.model.v1.Teammates;
import com.secretsanta.repo.SecretSantaRepository;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SecretSantaServiceImpl implements SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;

    private final Mapper mapper;

    @Override
    public Teammates getAllSecretSantasInGivenYear(Integer year) {

        List<Teammate> allTeammateEntity = secretSantaRepository.findAll(year);
        List<com.secretsanta.model.v1.Teammate> allTeammateModel = convertToModel(allTeammateEntity);

        assignSecretSanta(allTeammateModel);

        //TODO - Persist to DB
//      secretSantaRepository.save();

        return null;
    }

    private List<com.secretsanta.model.v1.Teammate> convertToModel(List<Teammate> allTeammateEntity) {
        List<com.secretsanta.model.v1.Teammate> allTeammateModel = new ArrayList<>();

        allTeammateEntity.forEach(teammateEntity ->
                allTeammateModel.add(mapper.map(teammateEntity, com.secretsanta.model.v1.Teammate.class))
        );

        return allTeammateModel;
    }

    @Override
    public com.secretsanta.model.v1.Teammate createNewTeammate(com.secretsanta.model.v1.Teammate teammate) {
        //TODO validate secretSanta is not allowed for creating new teammates

        //TODO validate firstName, secondName and year are provided

        //TODO persist to DB

        return teammate;
    }

    public Teammates assignSecretSanta(List<com.secretsanta.model.v1.Teammate> allTeammateModel) {
        //TODO validate that teammate is not their own secretSanta
        //TODO validate that teammate can only be secret santa every 4th year
        //TODO validate that secret santa cannot be done twice for a given year

        allTeammateModel.forEach(teammate -> {
            teammate.setSecretSanta("");
        });

        List<com.secretsanta.model.v1.Teammate> teammateList1 = new ArrayList<>(allTeammateModel);
        Teammates teammates = teammateList1.stream().collect(Collectors.toCollection(Teammates::new));

        return teammates;
    }

}
