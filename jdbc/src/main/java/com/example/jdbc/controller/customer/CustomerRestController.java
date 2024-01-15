package com.example.jdbc.controller.customer;

import com.example.jdbc.model.Customer;
import com.example.jdbc.service.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Customer API")
@RequiredArgsConstructor
@RequestMapping("/customer")
@RestController
@Validated
public class CustomerRestController {
    private final CustomerService customerService;

    @ApiOperation("Удалить клиента по ID")
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        customerService.deleteById(id);
    }

    @ApiOperation("Обновить информацию о клиенте")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") Long id,
                       @RequestBody @Valid Customer customer) {
        customerService.update(id, customer);
    }
}
