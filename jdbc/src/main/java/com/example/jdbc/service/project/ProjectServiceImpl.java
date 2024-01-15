package com.example.jdbc.service.project;

import com.example.jdbc.model.Project;
import com.example.jdbc.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        return project;
    }

    @Override
    public void update(Long id, Project project) {
        projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

        if (Objects.nonNull(project.getName())) {
            projectRepository.update(project);
        }
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
