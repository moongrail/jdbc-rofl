package com.example.jdbc.controller.customer;

import com.example.jdbc.model.Customer;
import com.example.jdbc.service.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Customer API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @ApiOperation("Вывести главную по покупателям")
    @GetMapping
    public String getCustomersPage(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }

    @ApiOperation("Вывести конкретного покупателя")
    @GetMapping("/{id}")
    public String getCustomer(@PathVariable @Positive Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer";
    }

    @ApiOperation("Добавить покупателя")
    @PostMapping(value = "/add/json", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Customer> save(@RequestBody Customer customer) {
        customerService.save(customer);
        return customerService.findAll();
    }
}
