package com.example.powerset.pset;

import com.example.powerset.error_handling.InvalidSetInputException;
import com.example.powerset.error_handling.SetNotFoundException;
import com.example.powerset.user.User;
import com.example.powerset.user.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin("http://localhost:8081/")
public class PowersetController {
    private final PSetRepository repo;
    private final UserRepository userRepo;
    private final ObjectModelAssembler obj;
    PowersetController(PSetRepository repo, UserRepository userRepo, ObjectModelAssembler obj){
        this.repo = repo;
        this.userRepo = userRepo;
        this.obj = obj;
    }

    @GetMapping("/set")
    ResponseEntity<List<PSet>> all(Principal principal){
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        List<PSet> usersSets = repo.findAllByUser(user).orElseThrow();
        return ResponseEntity.ok(usersSets);
    }

    @PostMapping("/set")
    ResponseEntity<PSet> newPSet(@RequestBody PSet set, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        set.setUser(user);
        if(set.getWeight()==null || set.getType()==null || set.getReps()==null){
            throw new InvalidSetInputException(set);
        }else {
            repo.save(set);
            return new ResponseEntity<>(set, HttpStatus.CREATED);
        }


    }

    @GetMapping("/set/id={id}")
    ResponseEntity<PSet> getPSet(@PathVariable Long id, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();

        PSet set = repo.findByIdAndUser(id, user).orElseThrow(
                () ->
                new SetNotFoundException(id)
        );

        return new ResponseEntity<>(set, HttpStatus.OK);
    }

    @GetMapping("/set/types")
    ResponseEntity<HashMap<String, List<PSet>>> getAllTypes(Principal principal){
        //  list of PSet.type
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        List<String> allTypes = repo.findAllByUser(user)
                .orElseThrow(() -> new SetNotFoundException(Long.valueOf(-1)))
                .stream()
                .map(PSet::getType)
                .toList();

        Set<String> typeSet = new HashSet<>(allTypes);

        HashMap<String, List<PSet>> setsByType = new HashMap<>();

        for(String type:typeSet){
            Optional<List<PSet>> byType = repo.findAllByTypeAndUser(type, user);
            byType.ifPresent(pSets -> setsByType.put(type, pSets));
        }

        return new ResponseEntity<>(setsByType, HttpStatus.OK);
    }

    @PutMapping("/set/id={id}")
    ResponseEntity<?> insertPSet(@RequestBody PSet newSet,
                                 @PathVariable Long id,
                                 Principal principal){
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();

        PSet pset = repo.findById(id).map(set -> {
            set.setDate(newSet.getDate());
            set.setType(newSet.getType());
            set.setWeight(newSet.getWeight());
            set.setReps(newSet.getReps());
            set.setUser(user);
            return repo.save(set);
        }).orElseGet(() -> {
            newSet.setId(id);
            return repo.save(newSet);
        });
        return new ResponseEntity<>(pset, HttpStatus.CREATED);

    }

    @DeleteMapping("/set/id={id}")
    ResponseEntity<?> deletePSet(@PathVariable Long id, Principal principal){
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();

        repo.deleteByIdAndUser(id, user);

        return ResponseEntity.noContent().build();
    }

    // TODO: update to select by users
    @GetMapping("/set/date={date}")
    Optional<List<PSet>> selectDate(@PathVariable LocalDate date) {
        return repo.findAllPSetsByDate(date);
    }

    @GetMapping("/set/type={type}")
    ResponseEntity<List<PSet>> findByType(@PathVariable String type, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        Optional<List<PSet>> byType = repo.findAllByTypeAndUser(type, user);
        return ResponseEntity.ok(byType.orElseThrow(() -> new SetNotFoundException(type)));
    }
}
