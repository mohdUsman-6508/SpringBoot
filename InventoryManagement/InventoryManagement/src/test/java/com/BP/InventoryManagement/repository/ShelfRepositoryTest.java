package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.Shelf;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataNeo4jTest
public class ShelfRepositoryTest {

    @Autowired
    private ShelfRepository shelfRepository;
    private Shelf shelf;

    @BeforeEach
    public void setup() {
        shelf = new Shelf(1l, "shelf-1", "type-A", null);
    }

    @Test
    @Transactional
    void testSaveShelf() {
        Shelf shelf1 = shelfRepository.save(shelf);
        assertNotNull(shelf.getName());
        assertNotNull(shelf.getId());
        assertNotNull(shelf.getShelfType());
    }

    @Test
    @Transactional
    void testGetshelf() {
        Shelf getshelf = shelfRepository.findById(1l).get();
        assertNotNull(getshelf);
    }

    @Test
    @Transactional
    void testGetAllshelf() {
        List<Shelf> allShelf = shelfRepository.findAll();
        assertNotNull(allShelf);
    }

    @AfterEach
    void Cleanup() {
        shelf = null;
    }
}

