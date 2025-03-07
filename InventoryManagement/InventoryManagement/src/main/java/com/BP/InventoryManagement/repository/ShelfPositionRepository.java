package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.ShelfPosition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelfPositionRepository extends Neo4jRepository<ShelfPosition, Long> {

    public Optional<ShelfPosition> findByName(String name);
}
