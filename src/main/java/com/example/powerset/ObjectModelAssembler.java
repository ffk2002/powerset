package com.example.powerset;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ObjectModelAssembler implements RepresentationModelAssembler<PSet, EntityModel<PSet>> {
    @Override
    public EntityModel<PSet> toModel(PSet set){
        return EntityModel.of(set,
                linkTo(methodOn(PowersetController.class).getPSet(set.getId())).withSelfRel(),
                linkTo(methodOn(PowersetController.class).all()).withRel("set")
                );
    }
}
