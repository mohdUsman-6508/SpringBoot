package com.lineup.lineup.repository;
import com.lineup.lineup.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User,Long> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
