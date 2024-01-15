package com.example.jdbc.controller;

import com.example.jdbc.service.DropDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Drop DATA API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/drop")
public class DropDataController {
    private final DropDataService dropDataService;

    @ApiOperation("Удалить всё из базы")
    @GetMapping
    public String drop() {
        dropDataService.drop();
        return "Zachem";
    }
}
