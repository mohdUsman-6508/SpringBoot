package com.lineup.lineup.repository;

import com.lineup.lineup.model.Task;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends Neo4jRepository<Task, Long> {

    public Optional<Task> findByTitle(String title);

    public Optional<List<Task>> findByUserId(Long userId);
}
