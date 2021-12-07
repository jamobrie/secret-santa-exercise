package com.secretsanta.service;

import com.secretsanta.conversion.ConversionHelper;
import com.secretsanta.entity.Teammate;
import com.secretsanta.model.v1.Teammates;
import com.secretsanta.repo.SecretSantaRepository;
import com.secretsanta.validation.TeammateValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SecretSantaServiceImpl implements SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;

    private final TeammateValidator teammateValidator;

    private final ConversionHelper conversionHelper;

    @Override
    public Teammates getAllSecretSantaInGivenYear(Integer year) {

        List<Teammate> allTeammateEntity = secretSantaRepository.findTeammatesByYear(year);

        List<com.secretsanta.model.v1.Teammate> allTeammateModel = conversionHelper.convertToModel(allTeammateEntity);

        Teammates teammatesModel = assignSecretSanta(allTeammateModel, year);

        teammatesModel.forEach(teammate -> secretSantaRepository.save(conversionHelper.convertToEntity(teammate)));

        return teammatesModel;
    }

    public Teammates assignSecretSanta(List<com.secretsanta.model.v1.Teammate> allTeammateModel, Integer year) {
        teammateValidator.validateSecretSanta(allTeammateModel, year);

        List<com.secretsanta.model.v1.Teammate> secretSantaList = new ArrayList<>();

        Collections.shuffle(allTeammateModel, new Random());

        //TODO Investigate thread safety
        AtomicInteger i = new AtomicInteger();
        while (i.get() < allTeammateModel.size()) {
            //Shift to new method
            com.secretsanta.model.v1.Teammate teammate = allTeammateModel.get(i.get());

            if (i.get() + 1 == allTeammateModel.size()) {
                teammate.setSecretSanta(allTeammateModel.get(0).getFirstName() + " " + allTeammateModel.get(0).getSecondName());
            } else {
                teammate.setSecretSanta(allTeammateModel.get(i.get() + 1).getFirstName() + " " + allTeammateModel.get(i.get() + 1).getSecondName());
            }

            secretSantaList.add(teammate);

            i.getAndIncrement();

            allTeammateModel.stream()
                    .filter(this::isTeammateTheirOwnSecretSanta)
                    .findAny()
                    .ifPresent(resetList -> {
                        i.set(0);
                        Collections.shuffle(allTeammateModel, new Random());
                    });

            //TODO validate that teammate can only be secret santa every third year
            //To finish this part out
//            allTeammateModel.stream()
//                    .map(teammate -> teammate.get)
//                    .filter(this::haveTeammatesBeenSecretSantaWithinThreeYears)
//                    .findAny()
//                    .ifPresent(resetList -> {
//                        i.set(0);
//                        Collections.shuffle(allTeammateModel, new Random());
//                    });
        }

        System.out.println(secretSantaList.stream());


        return allTeammateModel.stream().collect(Collectors.toCollection(Teammates::new));

    }

    private boolean haveTeammatesBeenSecretSantaWithinThreeYears(com.secretsanta.model.v1.Teammate teammate) {
        return true;
    }

    private boolean isTeammateTheirOwnSecretSanta(com.secretsanta.model.v1.Teammate teammate) {
        String fullTeammateName = teammate.getFirstName() + teammate.getSecondName();
        String secretSanta = teammate.getSecretSanta().trim();
        return fullTeammateName.equals(secretSanta);
    }


    @Override
    public com.secretsanta.model.v1.Teammate createNewTeammate(com.secretsanta.model.v1.Teammate teammateModel) {
        teammateValidator.validateNewTeammate(teammateModel);

        return saveTeammate(teammateModel);
    }

    private com.secretsanta.model.v1.Teammate saveTeammate(com.secretsanta.model.v1.Teammate teammateModel) {
        Teammate teammateEntity = conversionHelper.convertToEntity(teammateModel);

        return conversionHelper.convertToModel(secretSantaRepository.save(teammateEntity));
    }

}
