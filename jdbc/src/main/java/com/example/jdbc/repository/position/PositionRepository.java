package com.example.jdbc.repository.position;

import com.example.jdbc.model.Position;

import java.util.List;

public interface PositionRepository {
    void save(Position position);

    List<Position> findAll();
}
