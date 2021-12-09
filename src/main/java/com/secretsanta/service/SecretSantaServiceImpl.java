package com.secretsanta.service;

import com.secretsanta.conversion.ConversionHelper;
import com.secretsanta.entity.Teammate;
import com.secretsanta.exception.SecretSantaExceptionHandler;
import com.secretsanta.model.v1.Teammates;
import com.secretsanta.repo.SecretSantaRepository;
import com.secretsanta.validation.TeammateValidator;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@AllArgsConstructor
public class SecretSantaServiceImpl implements SecretSantaService {

    private final SecretSantaRepository secretSantaRepository;

    private final TeammateValidator teammateValidator;

    private final ConversionHelper conversionHelper;

    private static final ch.qos.logback.classic.Logger LOGGER =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(SecretSantaExceptionHandler.class);

    //TODO Investigate for thread safety

    @Override
    public Teammates getAllSecretSantaInGivenYear(Integer year) {

        List<Teammate> allTeammateEntityForGivenYear = secretSantaRepository.findTeammatesByYear(year);

        List<com.secretsanta.model.v1.Teammate> secretSantaListForGivenYear = findTeammatesForSelectedYear(year, allTeammateEntityForGivenYear);

        boolean secretSantaListIsValid = false;
        List<com.secretsanta.model.v1.Teammate> tempSecretSantaList = null;
        int randomCounter = 0;
        while (!secretSantaListIsValid) {
            tempSecretSantaList = randomizeAndFetchTempSecretSanta(secretSantaListForGivenYear);
            secretSantaListIsValid = isSecretSantaListValid(tempSecretSantaList);

            randomCounter++;
            if (randomCounter > 1000) {
                throw new RuntimeException("The randomization of secretSantas has happened 1000 times which is very unlikely, exception thrown as a result!");
            }
        }

        LOGGER.info("Number of random attempts made was: " + randomCounter);

        updateExistingTableRecord(allTeammateEntityForGivenYear, tempSecretSantaList);

        return tempSecretSantaList.stream().collect(Collectors.toCollection(Teammates::new));
    }

    private void updateExistingTableRecord(List<Teammate> allTeammateEntityForGivenYear, List<com.secretsanta.model.v1.Teammate> tempSecretSantaList) {
        allTeammateEntityForGivenYear.forEach(abc -> {
            abc.setSecretSanta(tempSecretSantaList.stream().filter(aaa -> aaa.getFirstName().equals(abc.getFirstName())).findFirst().get().getSecretSanta());
        });

        int year = allTeammateEntityForGivenYear.get(0).getYear();

        LOGGER.info("Inside SecretSantaServiceImpl.getAllSecretSantaInGivenYear() ... preparing to save secret santa list for year of " + year);
        allTeammateEntityForGivenYear.forEach(secretSantaRepository::save);
        LOGGER.info("Secret santa list for year of " + year + " has been saved successfully");
    }

    private List<com.secretsanta.model.v1.Teammate> findTeammatesForSelectedYear(Integer year, List<Teammate> allTeammateEntity) {
        List<com.secretsanta.model.v1.Teammate> secretSantaListForGivenYear = conversionHelper.convertToModel(allTeammateEntity);

        teammateValidator.validateSecretSantaForGivenYear(secretSantaListForGivenYear, year);

        return secretSantaListForGivenYear;
    }

    private List<com.secretsanta.model.v1.Teammate> randomizeAndFetchTempSecretSanta(List<com.secretsanta.model.v1.Teammate> incomingThisYear) {
        Collections.shuffle(incomingThisYear, new Random());
        List<com.secretsanta.model.v1.Teammate> tempThisYear = new ArrayList<>();
        incomingThisYear.forEach(randomTeammate -> tempThisYear.add(newTeammateCopy(randomTeammate)));

        int counter = 0;
        while (counter != tempThisYear.size()) {
            if (counter + 1 == tempThisYear.size()) {
                tempThisYear.get(counter).setSecretSanta(tempThisYear.get(0).getFirstName());
            } else {
                tempThisYear.get(counter).setSecretSanta(tempThisYear.get(counter + 1).getFirstName());
            }
            counter++;
        }
        return tempThisYear;

    }

    private boolean isSecretSantaListValid(List<com.secretsanta.model.v1.Teammate> tempSecretSantaList) {
        boolean isTheirOwnSecretSanta = tempSecretSantaList.stream()
                .anyMatch(this::isTeammateTheirOwnSecretSanta);

        boolean isSecretSantaNotWithinThreeYears = tempSecretSantaList.stream().anyMatch(this::verifyIfWithinThreeYears);

        return !isTheirOwnSecretSanta && !isSecretSantaNotWithinThreeYears;
    }

    private boolean verifyIfWithinThreeYears(com.secretsanta.model.v1.Teammate tempSecretSanta) {
        List<Teammate> previousSecretSantaMatchingThisTeammate = secretSantaRepository.findByFirstNameAndSecretSanta(tempSecretSanta.getFirstName(), tempSecretSanta.getSecretSanta());
        List<com.secretsanta.model.v1.Teammate> specificTeammateModelList = conversionHelper.convertToModel(previousSecretSantaMatchingThisTeammate);

        boolean haveAnyPreviousSecretSantaHappened = specificTeammateModelList.stream().anyMatch(teammate -> !isBlank(teammate.getSecretSanta()));

        if(!haveAnyPreviousSecretSantaHappened){
            return false;
        }

        return specificTeammateModelList.stream().anyMatch(specificTeammate -> verifyBasedOnYearAndSecretSanta(specificTeammate, tempSecretSanta));
    }

    private boolean verifyBasedOnYearAndSecretSanta(com.secretsanta.model.v1.Teammate previousYear, com.secretsanta.model.v1.Teammate tempSecretSanta) {
        int yearDiff = Math.abs(previousYear.getYearOfSecretSanta()-tempSecretSanta.getYearOfSecretSanta());
        if (previousYear.getSecretSanta().equals(tempSecretSanta.getSecretSanta()) && previousYear.getFirstName().equals(tempSecretSanta.getFirstName())) {
            return yearDiff <= 3 && yearDiff >= -3;
        }
        return false;
    }

    //Especially investigate safety here
    private com.secretsanta.model.v1.Teammate newTeammateCopy(com.secretsanta.model.v1.Teammate randomTeammate) {
        com.secretsanta.model.v1.Teammate tempTeammate = new com.secretsanta.model.v1.Teammate();
        tempTeammate.setFirstName(randomTeammate.getFirstName());
        tempTeammate.setSecondName(randomTeammate.getSecondName());
        tempTeammate.setYearOfSecretSanta(randomTeammate.getYearOfSecretSanta());
        tempTeammate.setSecretSanta(randomTeammate.getSecretSanta());

        return tempTeammate;
    }

    private boolean isTeammateTheirOwnSecretSanta(com.secretsanta.model.v1.Teammate teammate) {
        String fullTeammateName = teammate.getFirstName();
        String secretSanta = teammate.getSecretSanta();
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
