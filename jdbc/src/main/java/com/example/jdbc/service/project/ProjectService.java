package com.example.jdbc.service.project;

import com.example.jdbc.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project getProjectById(Long id);

    void save(Project project);

    void deleteById(Long id);

    void update(Long id, Project project);
}
