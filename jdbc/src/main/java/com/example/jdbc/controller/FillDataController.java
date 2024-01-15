package com.example.jdbc.controller;

import com.example.jdbc.service.FillDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "FILL DATA API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/fill")
public class FillDataController {
    private final FillDataService fillDataService;

    @ApiOperation("Заполнить всю базу")
    @GetMapping
    public String fill() {
        fillDataService.fill();
        return "KRUTO";
    }
}
