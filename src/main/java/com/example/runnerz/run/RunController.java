package com.example.runnerz.run;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/runs")
public class RunController {
  
  // In order to get a list of all the runs, we need to get an instance of that RunRepository
  // But Spring is an Inversion of Control framework, so anytime we are using the "new" keyword we must think about what we are doing. 
  // This is something that Spring manages in the Application Context, and unless we are using a type we created in this application, i.e. Run.java, we let Spring handle the instances of new classes.
  
  private final RunRepository runRepository;
  
  public RunController(RunRepository runRepository) {
    // We don't want to create a new instance every time, we want to use Dependency Injection. We are gonna ask for an instance of a class from Spring in the contructor of the controller.
    this.runRepository = runRepository;
  }
  
  @GetMapping("")
  List<Run> findAll() {
    return runRepository.findAll();
  }

  @GetMapping("/{id}")
  Run findById(@PathVariable Integer id) {

    Optional<Run> run =  runRepository.findById(id);
    if (run.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return run.get();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void create(@RequestBody Run run) {
    runRepository.create(run);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  void update(@RequestBody Run run, @PathVariable Integer id) {
    runRepository.update(run, id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  void delete(@PathVariable Integer id) {
    runRepository.delete(id);
  } 

}
