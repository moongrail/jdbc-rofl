package com.example.jdbc.dto.employee;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeResponse {
    private Long id;
    private String name;
}
