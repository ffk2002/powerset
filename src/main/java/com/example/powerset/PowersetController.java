package com.example.powerset;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PowersetController {
    private final PSetRepository repo;
    private final ObjectModelAssembler obj;

    PowersetController(PSetRepository repo, ObjectModelAssembler obj){
        this.repo = repo;
        this.obj = obj;
    }

    @GetMapping("/set")
    CollectionModel<EntityModel<PSet>> all(){
        List<EntityModel<PSet>> all = repo.findAll().stream()
                .map(obj::toModel).toList();

        return CollectionModel.of(all, linkTo(methodOn(PowersetController.class).all()).withSelfRel());
    }

    @PostMapping("/set")
    ResponseEntity<?> newPSet(@RequestBody PSet set){
        EntityModel<PSet> pset = obj.toModel(repo.save(set));

        return ResponseEntity.created(pset.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(pset);
    }

    @GetMapping("/set/id={id}")
    EntityModel<PSet> getPSet(@PathVariable Long id){
        PSet set = repo.findById(id).orElseThrow(
                () ->
                new SetNotFoundException(id)
        );

        return obj.toModel(set);
    }

    @GetMapping("/set/types")
    HashMap<String, List<PSet>> getAllTypes(){
        //  list of PSet.type
        List<String> allTypes = repo.findAll()
                .stream()
                .map(PSet::getType)
                .toList();

        Set<String> typeSet = new HashSet<>(allTypes);

        HashMap<String, List<PSet>> setsByType = new HashMap<>();

        for(String type:typeSet){
            Optional<List<PSet>> byType = repo.findAllByType(type);
            byType.ifPresent(pSets -> setsByType.put(type, pSets));
        }
        return setsByType;
    }

    @PutMapping("/set/id={id}")
    ResponseEntity<?> insertPSet(@RequestBody PSet newSet, @PathVariable Long id){
        PSet pset = repo.findById(id).map(set -> {
            set.setDate(newSet.getDate());
            set.setType(newSet.getType());
            set.setWeight(newSet.getWeight());
            set.setReps(newSet.getReps());
            return repo.save(set);
        }).orElseGet(() -> {
            newSet.setId(id);
            return repo.save(newSet);
        });

        EntityModel<PSet> pSetEntityModel = obj.toModel(pset);

        return ResponseEntity.created(pSetEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(pSetEntityModel);
    }

    @DeleteMapping("/set/id={id}")
    ResponseEntity<?> deletePSet(@PathVariable Long id){
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/set/date={date}")
    Optional<List<PSet>> selectDate(@PathVariable LocalDate date) {
        return repo.findAllPSetsByDate(date);
    }

    @GetMapping("/set/type={type}")
    Optional<List<PSet>> findByType(@PathVariable String type) {
        Optional<List<PSet>> byType = repo.findAllByType(type);
        if (byType.get().isEmpty()){
            throw new SetNotFoundException(type);
        }else {
            return byType;
        }
    }
}
