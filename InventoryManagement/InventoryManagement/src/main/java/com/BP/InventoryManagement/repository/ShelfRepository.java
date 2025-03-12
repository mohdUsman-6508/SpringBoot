package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.Shelf;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ShelfRepository extends Neo4jRepository<Shelf, Long> {
    public Optional<Shelf> findByName(String name);
    @Query("""
                  MATCH (shelf:Shelf)
                  WHERE ID(shelf)=$shelfId
                  WITH shelf.shelfPositionId AS spId,shelf
                  OPTIONAL MATCH (shelfPosition:ShelfPosition)
                  WHERE ID(shelfPosition)=spId
                  WITH shelfPosition AS sp,shelf AS s
                  OPTIONAL MATCH (device:Device)-[:HAS]->(sp)
                  RETURN  {
                             shelfName: s.name,
                             shelfType: s.shelfType,
                             shelfPositionName: sp.name,
                             shelfPositionId: s.shelfPositionId,
                             deviceName: device.name,
                             deviceType: device.deviceType
                           } AS result
            """)
    List<Map<String, Object>> getShelfSummary(Long shelfId);
}
