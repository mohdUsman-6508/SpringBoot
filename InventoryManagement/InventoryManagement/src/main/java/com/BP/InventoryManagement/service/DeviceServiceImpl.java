package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class DeviceServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public ResponseEntity<Device> saveDevice(Device device) {
        try {
            return new ResponseEntity(inventoryRepository.save(device), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Device> getDevice(Long id) {
        try {
            if (isDeviceExist(id)) {
                return new ResponseEntity<>(inventoryRepository.findById(id).get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Device> modifyDevice(Device device, Long id) {
        try {
            if (isDeviceExist(id)) {
                Optional<Device> deviceById = inventoryRepository.findById(id);
                if (!device.getName().isEmpty()) {
                    deviceById.get().setName(device.getName());
                }
                if (!device.getDeviceType().isEmpty()) {
                    deviceById.get().setDeviceType(device.getDeviceType());
                }
                saveDevice(deviceById.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Device> deleteDevice(Long id) {
        try {
            if (isDeviceExist(id)) {
                inventoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<Device> getAllDevices() {
        return inventoryRepository.findAll();
    }

    public boolean isDeviceExist(Long id) {
        return inventoryRepository.findById(id).isPresent();
    }

}
