package com.example.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
// we are using the @Repository annotation to tell Spring that we want it to "manage" this class in the Application Context, an instance of this class
public class RunRepository {

  private List<Run> runs = new  ArrayList<>();

  List<Run> findAll() {
    return runs;
  }

  Optional<Run> findById(Integer id) {
    return runs.stream()
      .filter(run -> run.id() == id)
      .findFirst();
  }

  void create(Run run) {
    runs.add(run);
  }

  void update(Run run, Integer id) {
    Optional<Run> existingRun = findById(id);
    if (existingRun.isPresent()) {
      runs.set(runs.indexOf(existingRun.get()), run);
    }
  }

  void delete(Integer id) {
    runs.removeIf(run -> run.id().equals(id));
  }

  @PostConstruct
  private void init() {

    runs.add(new Run(1, "Monday morning run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 2, Location.INDOOR));
    runs.add(new Run(2, "Wednesday eve run", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 5, Location.INDOOR));
  }
 
}
