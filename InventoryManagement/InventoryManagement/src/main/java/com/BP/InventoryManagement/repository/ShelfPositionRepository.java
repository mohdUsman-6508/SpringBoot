package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.ShelfPosition;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfPositionRepository extends Neo4jRepository<ShelfPosition, Long> {

}
