package com.example.jdbc.repository.employee;

import com.example.jdbc.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryJdbcTemplateImpl implements EmployeeRepository {
    public static final String SQL_DELETE_BY_ID = "DELETE FROM employee WHERE id = :id";
    public static final String SQL_UPDATE = "UPDATE employee SET name = :name WHERE id = :id";
    //language=SQL
    private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee (name) VALUES (:name)";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name FROM employee WHERE id = :id";
    private static final String SQL_SELECT_ALL = "SELECT id, name FROM employee";
    private static final String SQL_DELETE_ALL = "DELETE FROM employee CASCADE";
    private static final RowMapper<Employee> employeeMapper = (rs, rowNum) -> Employee.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Employee employee) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", employee.getName());

        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(params);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(SQL_INSERT_EMPLOYEE,
                mapSqlParameterSource,
                keyHolder,
                new String[]{"id"});

        employee.setId(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public void update(Employee employee) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", employee.getId());
        params.put("name", employee.getName());

        namedParameterJdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_BY_ID, params);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id),
                    employeeMapper));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcTemplate().execute(SQL_DELETE_ALL);
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, employeeMapper);
    }
}
