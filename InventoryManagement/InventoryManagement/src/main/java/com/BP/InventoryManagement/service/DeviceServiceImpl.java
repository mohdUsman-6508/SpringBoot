package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class DeviceServiceImpl implements DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Device> saveDevice(Device device) {
        logger.info("Creating device:{}", device.getName() + device.getId() + device.getDeviceType());
        if (device.getName() == null || device.getName().isEmpty()) {
            logger.warn("Invalid device");
            throw new IllegalArgumentException("Device name cannot be empty");
        }
        try {
            Device savedDevice = deviceRepository.save(device);
            logger.debug("Device created with Id:{}", savedDevice.getId());
            return new ResponseEntity(savedDevice, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Device> getDevice(Long id) {
        try {
            if (isDeviceExist(id)) {
                return new ResponseEntity<>(deviceRepository.findById(id).get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<Device> modifyDevice(Device device, Long id) {
        try {
            if (isDeviceExist(id)) {
                Optional<Device> deviceById = deviceRepository.findById(id);
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
    @Transactional
    public ResponseEntity<Device> deleteDevice(Long id) {
        try {
            if (isDeviceExist(id)) {
                deviceRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Device>> getAllDevices() {
        try {
            return new ResponseEntity<>(deviceRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<Device> getDeviceByName(String name) {
        Optional<Device> device = deviceRepository.findByName(name);
        if (device.isPresent()) {
            return ResponseEntity.ok(device.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public boolean isDeviceExist(Long id) {
        return deviceRepository.findById(id).isPresent();
    }

    public ResponseEntity<Device> getDeviceByType(String deviceType) {
        Optional<Device> d = deviceRepository.findByDeviceType(deviceType);
        if (d.isPresent()) return ResponseEntity.ok(d.get());
        else return ResponseEntity.notFound().build();
    }

}
