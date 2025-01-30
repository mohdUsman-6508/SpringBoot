package com.BP.InventoryManagement.controller;

import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import com.BP.InventoryManagement.service.DeviceServiceImpl;
import com.BP.InventoryManagement.service.ShelfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelfService")
public class ShelfServiceController {

    @Autowired
    private ShelfServiceImpl service;


    @PostMapping("/addShelfPosition")
    public ResponseEntity<ShelfPosition> addShelfPosition(@RequestBody ShelfPosition shelfPosition) {
        return service.saveShelfPosition(shelfPosition);
    }

    @PostMapping("/addShelf")
    public ResponseEntity<Shelf> addShelf(@RequestBody Shelf shelf) {
        return service.saveShelf(shelf);
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

    @PostMapping("/addShelfPositionToDevice/{deviceId}/{shelfPostionId}")
    public ResponseEntity<?> addShelfPositionToDevice(@PathVariable Long deviceId,@PathVariable Long shelfPositionId){
        return service.addShelfPositionToDevice(deviceId,shelfPositionId);
    }

}

