package com.example.jdbc.repository.customer;

import com.example.jdbc.model.Customer;
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
public class CustomerRepositoryImpl implements CustomerRepository {
    public static final String SQL_DELETE_BY_ID = "DELETE FROM customer WHERE id = :id";
    public static final String SQL_UPDATE = "UPDATE customer SET name = :name WHERE id = :id";
    private static final String SQL_INSERT_CUSTOMER = "INSERT INTO customer (name) VALUES (:name)";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name FROM customer WHERE id = :id";
    private static final String SQL_SELECT_ALL = "SELECT id, name FROM customer";
    private static final String SQL_DELETE_ALL = "DELETE FROM customer CASCADE";
    private static final RowMapper<Customer> customerMapper = (rs, rowNum) -> Customer.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Customer customer) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", customer.getName());

        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(params);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(SQL_INSERT_CUSTOMER,
                mapSqlParameterSource,
                keyHolder,
                new String[]{"id"});

        customer.setId(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcTemplate().execute(SQL_DELETE_ALL);
    }

    @Override
    public void update(Customer customer) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", customer.getId());
        params.put("name", customer.getName());

        namedParameterJdbcTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_BY_ID, params);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                    Collections.singletonMap("id", id),
                    customerMapper));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, customerMapper);
    }
}