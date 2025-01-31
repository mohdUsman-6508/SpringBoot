package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.ShelfPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataNeo4jTest
class ShelfPositionRepositoryTest {

    @Autowired
    private ShelfPositionRepository shelfPositionRepository;
    private ShelfPosition shelfPosition;

    @BeforeEach
    public void setup() {
        shelfPosition = new ShelfPosition(1l, "shelfPosition-1", null, null);
    }

    @Test
    @Transactional
    void testSaveShelfPosition() {
        ShelfPosition shelfPosition1 = shelfPositionRepository.save(shelfPosition);
        assertNotNull(shelfPosition1.getName());
        assertNotNull(shelfPosition1.getId());
    }

    @Test
    @Transactional
    void testGetShelfPosition() {
        Optional<ShelfPosition> shelfPosition = shelfPositionRepository.findById(7l);
        assertNotNull(shelfPosition.get());
    }

    @Test
    @Transactional
    void testGetAllShelfPosition() {
        List<ShelfPosition> allShelfPosition = shelfPositionRepository.findAll();
        assertNotNull(allShelfPosition);
    }

    @AfterEach
    void Cleanup() {
        shelfPosition = null;
    }
}

