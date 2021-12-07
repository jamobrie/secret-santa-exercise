package com.secretsanta.exception;

import com.secretsanta.model.v1.Teammate;

public class TeammateAlreadyExistsForThatYearException extends RuntimeException {
    public TeammateAlreadyExistsForThatYearException(Teammate teammate) {
        super("A Teammate already exists with the following first and second names: " + teammate.getFirstName() + " " + teammate.getSecondName() + " in the year of " + teammate.getYearOfSecretSanta());
    }
}
