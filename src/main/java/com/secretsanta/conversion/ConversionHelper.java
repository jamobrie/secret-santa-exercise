package com.secretsanta.conversion;

import com.secretsanta.model.v1.Teammate;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ConversionHelper {

    private final Mapper mapper;

    public List<Teammate> convertToModel(List<com.secretsanta.entity.Teammate> allTeammateEntity) {
        List<com.secretsanta.model.v1.Teammate> allTeammateModel = new ArrayList<>();

        allTeammateEntity.forEach(teammateEntity ->
                allTeammateModel.add(convertToModel(teammateEntity))
        );

        return allTeammateModel;
    }

    public Teammate convertToModel(com.secretsanta.entity.Teammate teammateEntity) {
        return mapper.map(teammateEntity, com.secretsanta.model.v1.Teammate.class);
    }

    public com.secretsanta.entity.Teammate convertToEntity(com.secretsanta.model.v1.Teammate teammate) {
        return mapper.map(teammate, com.secretsanta.entity.Teammate.class);
    }

}
