package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.Shelf;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfRepository extends Neo4jRepository<Shelf, Long> {

}
