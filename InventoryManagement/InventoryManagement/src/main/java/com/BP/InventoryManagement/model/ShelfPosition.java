package com.BP.InventoryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShelfPosition {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long deviceId;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Shelf shelf;

}
