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
public class Shelf {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String shelfType;
    private Long shelfPositionId;

    @Relationship(type="HAS",direction = Relationship.Direction.INCOMING)
    private ShelfPosition shelfPosition;
}
