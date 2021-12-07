package com.secretsanta.exception;

public class SecretSantaWasAlreadyOrganisedForYearException extends RuntimeException {

    public SecretSantaWasAlreadyOrganisedForYearException(Integer year) {
        super("Secret santa cannot be done twice for the same year and you have provided the year of: " + year);
    }

}
