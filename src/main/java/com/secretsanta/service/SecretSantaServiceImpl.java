package com.secretsanta.service;

import com.secretsanta.model.v1.Teammate;
import com.secretsanta.model.v1.Teammates;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretSantaServiceImpl implements SecretSantaService{

    @Override
    public Teammates getAllSecretSantasInGivenYear(Integer year) {
        Teammate james = new Teammate();
        james.setFirstName("James");
        james.setSecondName("O'Brien");
        james.setSecretSanta("Tom");
        james.setYearOfSecretSanta(2020);

        Teammate tom = new Teammate();
        tom.setFirstName("Tom");
        tom.setSecondName("Barry");
        tom.setSecretSanta("james");
        tom.setYearOfSecretSanta(2021);

        List<Teammate> teammateList = new ArrayList<>();
        teammateList.add(james);
        teammateList.add(tom);

        List<Teammate> teammateList1 = teammateList.stream().filter(teammate -> teammate.getYearOfSecretSanta().equals(year)).collect(Collectors.toList());

        Teammates teammates = teammateList1.stream().collect(Collectors.toCollection(Teammates::new));

        return (teammates);
    }

    @Override
    public Teammate createNewTeammate(Teammate teammate) {
        //TODO persist to DB

        return teammate;
    }

}
