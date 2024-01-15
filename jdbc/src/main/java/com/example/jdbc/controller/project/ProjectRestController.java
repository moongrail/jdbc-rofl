package com.example.jdbc.controller.project;

import com.example.jdbc.model.Project;
import com.example.jdbc.service.project.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "project API")
@RequiredArgsConstructor
@RequestMapping("/project")
@RestController
@Validated
public class ProjectRestController {
    private final ProjectService projectService;

    @ApiOperation("Удалить проект по айди")
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        projectService.deleteById(id);
    }

    @ApiOperation("Обновить проект по айди")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") @Positive Long id,
                       @RequestBody @Valid Project project) {
        projectService.update(id, project);
    }
}


