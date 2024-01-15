package com.example.jdbc.dto.employee;

import com.example.jdbc.dto.ProjectPositionEmployeeDto;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeResponseFull {
    private Long id;
    private String name;
    private String position;
    private List<ProjectPositionEmployeeDto> projectPositionEmployeeDtoList;
}
