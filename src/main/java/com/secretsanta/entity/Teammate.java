package com.secretsanta.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.dozer.Mapping;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "teammate")
public class Teammate implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Mapping("secondName")
    @Column(name = "second_name")
    private String lastName;

    @Mapping("yearOfSecretSanta")
    @Column(name = "year")
    private Integer year;

    @Column(name = "secret_santa")
    private String secretSanta;

    protected Teammate() {}

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }


}

