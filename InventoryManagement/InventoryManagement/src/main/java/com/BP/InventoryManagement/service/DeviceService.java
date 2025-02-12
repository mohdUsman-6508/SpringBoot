package com.BP.InventoryManagement.service;

import com.BP.InventoryManagement.model.Device;
import org.springframework.http.ResponseEntity;

public interface DeviceService {
    ResponseEntity<Device> saveDevice(Device device);

    ResponseEntity<Device> getDevice(Long id);

    ResponseEntity<Device> modifyDevice(Device device, Long id);

    ResponseEntity<Device> deleteDevice(Long id);

}
