package com.BP.InventoryManagement.controller;


import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.service.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")

public class DeviceController {
    @Autowired
    private DeviceServiceImpl service;

    @PostMapping("/add")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        return service.saveDevice(device);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        return service.getDevice(id);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Device> modifyDevice(@RequestBody Device device, @PathVariable Long id) {
        return service.modifyDevice(device, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Device> deleteDevice(@PathVariable Long id) {
        return service.deleteDevice(id);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return service.getAllDevices();
    }

}
