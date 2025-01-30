package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import com.BP.InventoryManagement.repository.ShelfPositionRepository;
import com.BP.InventoryManagement.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;
    @Autowired
    private DeviceService deviceService;

    @Override
    public ResponseEntity<ShelfPosition> saveShelfPosition(ShelfPosition shelfPosition) {
        try {
            if (shelfPosition != null) {
                return new ResponseEntity<>(shelfPositionRepository.save(shelfPosition), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<ShelfPosition>> getShelfPosition() {
        return new ResponseEntity<>(shelfPositionRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Shelf> saveShelf(Shelf shelf) {
        try {
            if (shelf != null) {
                return new ResponseEntity<>(shelfRepository.save(shelf), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Shelf>> getShelf() {
        return new ResponseEntity<>(shelfRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        try {
            Device device = deviceService.getDevice(deviceId).getBody();
            Optional<ShelfPosition> shelfPosition = shelfPositionRepository.findById(shelfPositionId);
            if (device != null && shelfPosition.isPresent()) {
                device.getShelfPositions().add(shelfPosition.get());
            }
            deviceService.saveDevice(device);
            shelfPositionRepository.save(shelfPosition.get());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addShelfToShelfPosition(Long shelfId, Long shelfPositionId) {
        ShelfPosition shelfPosition = shelfPositionRepository.findById(shelfPositionId).get();
        Shelf shelf = shelfRepository.findById(shelfId).get();
        if (shelfPosition != null && shelf != null) {
            shelfPosition.getShelf().setShelfPosition(shelfPosition);
        }
        shelfPositionRepository.save(shelfPosition);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Shelf> getShelfById(Long id) {
        try {
            if (isShelfExist(id)) {
                return new ResponseEntity<>(shelfRepository.findById(id).get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ShelfPosition> getShelfPositionById(Long id) {
        try {
            if (isShelfPositionExist(id)) {
                return new ResponseEntity<>(shelfPositionRepository.findById(id).get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public boolean isShelfExist(Long id) {
        return shelfRepository.findById(id).isPresent();
    }

    public boolean isShelfPositionExist(Long id) {
        return shelfPositionRepository.findById(id).isPresent();
    }
}
