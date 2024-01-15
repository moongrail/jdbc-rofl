package com.example.jdbc.repository.project;

import com.example.jdbc.model.Project;
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
public class ProjectRepositoryImpl implements ProjectRepository {
    private static final String SQL_INSERT_PROJECT = "INSERT INTO project (name) VALUES (:name)";
    private static final String SQL_UPDATE_PROJECT = "UPDATE project SET name = :name WHERE id = :id";
    private static final String SQL_DELETE_PROJECT = "DELETE FROM project WHERE id = :id";
    private static final String SQL_SELECT_PROJECT_BY_ID = "SELECT id, name FROM project WHERE id = :id";
    private static final String SQL_SELECT_ALL_PROJECTS = "SELECT id, name FROM project";
    private static final String SQL_DELETE_ALL = "DELETE FROM project CASCADE";
    private static final RowMapper<Project> projectMapper = (rs, rowNum) -> Project.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Project project) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", project.getName());

        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(params);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(SQL_INSERT_PROJECT,
                mapSqlParameterSource,
                keyHolder,
                new String[]{"id"});

        project.setId(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public void update(Project project) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", project.getId());
        params.put("name", project.getName());

        namedParameterJdbcTemplate.update(SQL_UPDATE_PROJECT, params);
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_PROJECT, params);
    }

    @Override
    public Optional<Project> findById(Long id) {
        try {
            return Optional.of(namedParameterJdbcTemplate.queryForObject(SQL_SELECT_PROJECT_BY_ID,
                    Collections.singletonMap("id", id),
                    projectMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcTemplate().execute(SQL_DELETE_ALL);
    }

    @Override
    public List<Project> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL_PROJECTS, projectMapper);
    }
}
