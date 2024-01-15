package com.example.jdbc.controller.project;

import com.example.jdbc.model.Project;
import com.example.jdbc.service.project.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "project API")
@RequiredArgsConstructor
@RequestMapping("/project")
@Controller
@Validated
public class ProjectController {
    private final ProjectService projectService;

    @ApiOperation("Вывести главную страницу по проектам")
    @GetMapping
    public String getProjects(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return "projects";
    }

    @ApiOperation("Добавить проект")
    @PostMapping(value = "/add/json", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Project> save(@RequestBody Project project) {
        projectService.save(project);
        return projectService.getAll();
    }

    @ApiOperation("Вывести конкретную страницу по проектам")
    @GetMapping("/{id}")
    public String getProject(Model model,
                             @PathVariable @Positive Long id) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "project";
    }
}
