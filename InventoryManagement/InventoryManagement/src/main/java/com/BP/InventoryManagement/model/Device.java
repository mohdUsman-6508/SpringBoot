//package com.bp.inventory-management.model;
package com.BP.InventoryManagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

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
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 25, message = "Name must be between 3 to 25 characters")
    private String name;
    @NotBlank(message = "Device type is required")
    private String deviceType;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<ShelfPosition> shelfPositions = new HashSet<>();

}
