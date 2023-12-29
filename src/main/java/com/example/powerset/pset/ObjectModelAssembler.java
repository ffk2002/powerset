package com.example.powerset.pset;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ObjectModelAssembler implements RepresentationModelAssembler<PSet, EntityModel<PSet>> {
    //return entity containing json format response for current request and links to all sets and current set
    @Override
    public EntityModel<PSet> toModel(PSet set){
        return EntityModel.of(set,
                linkTo(methodOn(PowersetController.class).getPSet(set.getId())).withSelfRel(),
                linkTo(methodOn(PowersetController.class).all()).withRel("set")
                );
    }

    public EntityModel<HashMap<String, List<PSet>>> toModel(HashMap<String, List<PSet>> setsByType) {
        return EntityModel.of(setsByType);
    }
}
