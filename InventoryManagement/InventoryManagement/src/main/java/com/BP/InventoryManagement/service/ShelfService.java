package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import com.BP.InventoryManagement.repository.ShelfSummary;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShelfService {

    ResponseEntity<ShelfPosition> saveShelfPosition(ShelfPosition shelfPosition);

    ResponseEntity<List<ShelfPosition>> getShelfPosition();

    ResponseEntity<Shelf> saveShelf(Shelf shelf);

    ResponseEntity<List<Shelf>> getShelf();

    ResponseEntity<?> addShelfPositionToDevice(Long deviceId, Long shelfPositionId);

    ResponseEntity<?> addShelfToShelfPosition(Long shelfId, Long shelfPositionId);

    ResponseEntity<Object> getShelfSummary(Long shelfId);
}
