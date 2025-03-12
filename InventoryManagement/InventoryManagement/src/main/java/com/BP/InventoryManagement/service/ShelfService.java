package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ShelfService {

    ResponseEntity<ShelfPosition> saveShelfPosition(ShelfPosition shelfPosition);

    ResponseEntity<List<ShelfPosition>> getShelfPosition();

    ResponseEntity<Shelf> saveShelf(Shelf shelf);

    ResponseEntity<List<Shelf>> getShelf();

    ResponseEntity<?> addShelfPositionToDevice(Long deviceId, Long shelfPositionId);

    ResponseEntity<?> addShelfToShelfPosition(Long shelfId, Long shelfPositionId);

    ResponseEntity<List<Map<String, Object>>> getShelfSummary(Long shelfId);
}
