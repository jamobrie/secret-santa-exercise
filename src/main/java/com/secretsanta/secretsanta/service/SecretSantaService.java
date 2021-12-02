package com.secretsanta.secretsanta.service;

import com.secretsanta.secretsanta.model.Teammate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretSantaService {
    public List<Teammate> getAllSecretSantasInGivenYear(Integer year) {
        Teammate james = new Teammate("James", "O'Brien", "Tom", 2020);
        Teammate tom = new Teammate("Tom", "Barry", "james", 2021);

        List<Teammate> teammateList = new ArrayList<>();
        teammateList.add(james);
        teammateList.add(tom);

        return teammateList.stream().filter(teammate -> teammate.getYearOfSecretSanta().equals(year)).collect(Collectors.toList());
    }
}
