package com.lineup.lineup.model;

import com.lineup.lineup.enums.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed = false;
    private String categoryName;

    @Relationship(type = "HAS_PRIORITY", direction = Relationship.Direction.OUTGOING)
    private Category category;

}
