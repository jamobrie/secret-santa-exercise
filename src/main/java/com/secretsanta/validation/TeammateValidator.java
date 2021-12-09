package com.secretsanta.validation;

import com.secretsanta.exception.InvalidTeammateException;
import com.secretsanta.exception.SecretSantaExceptionHandler;
import com.secretsanta.exception.SecretSantaWasAlreadyOrganisedForYearException;
import com.secretsanta.exception.TeammateAlreadyExistsForThatYearException;
import com.secretsanta.model.v1.Teammate;
import com.secretsanta.repo.SecretSantaRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TeammateValidator {

    private final SecretSantaRepository secretSantaRepository;

    private static final ch.qos.logback.classic.Logger LOGGER =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(SecretSantaExceptionHandler.class);

    public void validateNewTeammate(Teammate teammate) {
        LOGGER.info("Inside TeammateValidator.validateNewTeammate() ... preparing to validate new teammate");
        if (teammate == null) {
            throw new InvalidTeammateException("New Teammate Cannot be Null");
        }

        if (!StringUtils.isBlank(teammate.getSecretSanta())) {
            throw new InvalidTeammateException("A new teammate cannot have a secretsanta pre-selected as it requires all teammates present before choosing");
        }

        validateAttribute(teammate.getFirstName());
        validateAttribute(teammate.getSecondName());
        validateYear(teammate.getYearOfSecretSanta());

        if (!StringUtils.isBlank(teammate.getSecretSanta())) {
            throw new InvalidTeammateException("A new teammate cannot have a secretsanta pre-selected as it requires all teammates present before choosing");
        }

        verifyNewTeammateDoesNotAlreadyExistForYear(teammate);

    }

    private void validateYear(Integer year) {
        if (year == null) {
            throw new InvalidTeammateException("A new teammate cannot have an empty year of " + year);
        }
        if (year < 2000 || year > 2025) {
            throw new InvalidTeammateException("A new teammate for the given year must be within the range of 2000 and 2025");
        }
    }

    private void validateAttribute(String attribute) {
        if (StringUtils.isBlank(attribute)) {
            throw new InvalidTeammateException("A new teammate cannot have an empty attribute of " + attribute);
        }
    }

    public void verifyNewTeammateDoesNotAlreadyExistForYear(Teammate teammateModel) {
        List<com.secretsanta.entity.Teammate> teammateEntityList = secretSantaRepository.findAll();
        teammateEntityList.stream().filter(teammateEntity -> findTeamWithSameNameForGivenYear(teammateEntity, teammateModel))
                .findAny()
                .ifPresent(exception -> {
                    throw new TeammateAlreadyExistsForThatYearException(teammateModel);
                });
    }

    private Boolean findTeamWithSameNameForGivenYear(com.secretsanta.entity.Teammate teammateEntity, com.secretsanta.model.v1.Teammate teammateModel) {
        return teammateEntity.getFirstName().equalsIgnoreCase(teammateModel.getFirstName()) &&
                teammateEntity.getLastName().equalsIgnoreCase(teammateModel.getSecondName()) &&
                teammateEntity.getYear().equals(teammateModel.getYearOfSecretSanta());
    }

    public void validateSecretSantaForGivenYear(List<Teammate> allTeammateModel, Integer year) {
        allTeammateModel.stream()
                .filter(teammate -> findPreviousSecretSantaForGivenYear(teammate, year))
                .findAny()
                .ifPresent(exception -> {
                    throw new SecretSantaWasAlreadyOrganisedForYearException(year);
                });
    }

    private boolean findPreviousSecretSantaForGivenYear(Teammate teammate, Integer year) {
        return year.equals(teammate.getYearOfSecretSanta()) &&
                !StringUtils.isBlank(teammate.getSecretSanta());
    }

}
