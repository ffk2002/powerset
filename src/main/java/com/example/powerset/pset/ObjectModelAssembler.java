package com.example.powerset.pset;

import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ObjectModelAssembler implements RepresentationModelAssembler<PSet, EntityModel<PSet>> {
    //return entity containing json format response for current request and links to all sets and current set
    @Override
    public @NotNull EntityModel<PSet> toModel(@NotNull PSet set){
        Principal p = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return EntityModel.of(set,
                linkTo(methodOn(PowersetController.class).getPSet(set.getId(), p)).withSelfRel(),
                linkTo(methodOn(PowersetController.class).all(p)).withRel("set")
                );
    }

//    public EntityModel<HashMap<String, List<PSet>>> toModel(HashMap<String, List<PSet>> setsByType) {
//        return EntityModel.of(setsByType);
//    }
}
