package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.model.Shelf;
import com.BP.InventoryManagement.model.ShelfPosition;
import com.BP.InventoryManagement.repository.ShelfPositionRepository;
import com.BP.InventoryManagement.repository.ShelfRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;
    private final ShelfPositionRepository shelfPositionRepository;
    private final DeviceServiceImpl deviceService;

    public ShelfServiceImpl(ShelfRepository shelfRepository, ShelfPositionRepository shelfPositionRepository, DeviceServiceImpl deviceService) {
        this.shelfRepository = shelfRepository;
        this.shelfPositionRepository = shelfPositionRepository;
        this.deviceService = deviceService;
    }

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

    // One ShelfPosition can hold only one Device
    // Add ShelfPosition to Device only when the ShelfPosition does not belong to other device
    @Override
    public ResponseEntity<?> addShelfPositionToDevice(Long deviceId, Long shelfPositionId) {
        try {
            Device device = deviceService.getDevice(deviceId).getBody();
            Optional<ShelfPosition> shelfPosition = shelfPositionRepository.findById(shelfPositionId);
            if (device != null && shelfPosition.isPresent() && shelfPosition.get().getDeviceId() == null) {
                device.getShelfPositions().add(shelfPosition.get());
                shelfPosition.get().setDeviceId(deviceId);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            deviceService.saveDevice(device);
            shelfPosition.ifPresent(position -> saveShelfPosition(position));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // One Shelf can hold only one ShelfPosition
    // Add Shelf to ShelfPosition only when the Shelf does not belong to other ShelfPosition
    @Override
    public ResponseEntity<?> addShelfToShelfPosition(Long shelfId, Long shelfPositionId) {
        try {
            Optional<ShelfPosition> shelfPosition = shelfPositionRepository.findById(shelfPositionId);
            Optional<Shelf> shelf = shelfRepository.findById(shelfId);
            if (shelfPosition.isPresent() && shelf.isPresent() && shelf.get().getShelfPositionId() == null) {
                shelfPosition.get().setShelf(shelf.get());
                shelf.get().setShelfPositionId(shelfPositionId);
                shelfPositionRepository.save(shelfPosition.get());
                shelfRepository.save(shelf.get());
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
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
