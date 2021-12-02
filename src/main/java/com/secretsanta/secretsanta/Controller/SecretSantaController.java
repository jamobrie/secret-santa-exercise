package com.secretsanta.secretsanta.Controller;

import model.Teammate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SecretSantaController {

    @RequestMapping("/getAllSecretSanta")
    public List<Teammate> getAllSecretSanta() {
        Teammate teammate = new Teammate("James", "O'Brien", "Tom", 2020);

        List<Teammate> teammateList = new ArrayList<>();
        teammateList.add(teammate);
        return teammateList;
    }

}
