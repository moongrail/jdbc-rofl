package com.example.jdbc.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProjectPositionEmployeeDto {
    private String projectName;
    private String positionName;
}
