package com.BP.InventoryManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

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

}
