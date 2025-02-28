package com.lineup.lineup.repository;

import com.lineup.lineup.model.Category;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category, Long> {
    public Optional<Category> findByCategoryName(String categoryName);
}
