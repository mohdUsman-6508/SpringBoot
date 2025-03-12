package com.BP.InventoryManagement.controller;

import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import com.BP.InventoryManagement.repository.ShelfSummary;
import com.BP.InventoryManagement.service.ShelfServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelfService")
@CrossOrigin(origins = "http://localhost:4200")

public class ShelfServiceController {

    private final ShelfServiceImpl service;

    public ShelfServiceController(ShelfServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/addShelfPosition")
    public ResponseEntity<?> addShelfPosition(@Valid @RequestBody ShelfPosition shelfPosition, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getAllErrors()
            );
        }
        return service.saveShelfPosition(shelfPosition);
    }

    @PostMapping("/addShelf")
    public ResponseEntity<?> addShelf(@Valid @RequestBody Shelf shelf, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getAllErrors()
            );
        }
        return service.saveShelf(shelf);
    }

    @GetMapping("/getShelf/{name}")
    public ResponseEntity<Shelf> getShelfByName(@PathVariable String name) {
        return service.getShelfByName(name);
    }

    @GetMapping("/getShelfPosition/{name}")
    public ResponseEntity<ShelfPosition> getShelfPositionByName(@PathVariable String name) {
        return service.getShelfPositionByName(name);
    }

    @GetMapping("/getAllShelves")
    public ResponseEntity<List<Shelf>> getAllShelves() {
        return service.getShelf();
    }

    @GetMapping("/getAllShelfPosition")
    public ResponseEntity<List<ShelfPosition>> getAllShelfPosition() {
        return service.getShelfPosition();
    }

    @GetMapping("/getShelfById/{id}")
    public ResponseEntity<Shelf> getShelfById(@PathVariable Long id) {
        return service.getShelfById(id);
    }

    @GetMapping("/getShelfPositionById/{id}")
    public ResponseEntity<ShelfPosition> getShelfPositionById(@PathVariable Long id) {
        return service.getShelfPositionById(id);
    }

    @PostMapping("/addShelfPositionToDevice/{deviceId}/{shelfPositionId}")
    public ResponseEntity<?> addShelfPositionToDevice(@Valid @PathVariable Long deviceId, @PathVariable Long shelfPositionId, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getAllErrors()
            );
        }
        return service.addShelfPositionToDevice(deviceId, shelfPositionId);
    }

    @PostMapping("/addShelfToShelfPosition/{shelfId}/{shelfPositionId}")
    public ResponseEntity<?> addShelfToShelfPosition(@Valid @PathVariable Long shelfId, @PathVariable Long shelfPositionId, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getAllErrors()
            );
        }
        return service.addShelfToShelfPosition(shelfId, shelfPositionId);
    }

    @GetMapping("/shelfSummary/{shelfId}")
    public ResponseEntity<Object> getShelfSummary(@PathVariable Long shelfId) {
        return service.getShelfSummary(shelfId);
    }
}

