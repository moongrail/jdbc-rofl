package com.example.jdbc.repository.position;

import com.example.jdbc.model.Position;
import com.example.jdbc.model.PositionType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {
    private static final String SQL_INSERT_POSITION = "INSERT INTO position (position_type) VALUES (:position_type)";
    private static final String SQL_SELECT_ALL = "SELECT id, name FROM position";
    private static final RowMapper<Position> positionMapper = (rs, rowNum) -> {
        Position position = new Position();
        position.setId(rs.getLong("id"));
        position.setPositionType(PositionType.valueOf(rs.getString("position_type")));
        return position;
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Position position) {
        Map<String, Object> params = new HashMap<>();
        params.put("position_type", position.getPositionType().toString());

        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(params);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(SQL_INSERT_POSITION,
                mapSqlParameterSource,
                keyHolder,
                new String[]{"id"});

        position.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Position> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, positionMapper);
    }
}
