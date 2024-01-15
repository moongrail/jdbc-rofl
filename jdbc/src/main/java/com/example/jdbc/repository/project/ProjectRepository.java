package com.example.jdbc.repository.project;

import com.example.jdbc.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void save(Project project);

    void update(Project project);

    void deleteById(Long id);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void deleteAll();
}
