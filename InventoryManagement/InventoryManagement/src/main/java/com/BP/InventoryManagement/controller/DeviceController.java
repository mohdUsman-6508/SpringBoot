package com.BP.InventoryManagement.controller;
import com.BP.InventoryManagement.model.Device;
import com.BP.InventoryManagement.service.DeviceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@CrossOrigin(origins = "http://localhost:4200")

public class DeviceController {
    private final DeviceServiceImpl service;

    public DeviceController(DeviceServiceImpl service){
        this.service=service;
    }

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

    @GetMapping("/")
    public ResponseEntity<List<Device>> getAllDevices() {
        return service.getAllDevices();
    }

}
