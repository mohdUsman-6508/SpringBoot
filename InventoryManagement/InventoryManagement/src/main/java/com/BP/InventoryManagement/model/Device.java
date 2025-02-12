//package com.bp.inventory-management.model;
package com.BP.InventoryManagement.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<com.BP.InventoryManagement.model.ShelfPosition> shelfPositions = new HashSet<>();

}
