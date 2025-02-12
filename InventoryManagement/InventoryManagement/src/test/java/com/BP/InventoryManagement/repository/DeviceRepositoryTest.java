package com.BP.InventoryManagement.repository;

import com.BP.InventoryManagement.model.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataNeo4jTest
public class DeviceRepositoryTest {
    @Autowired
    private com.BP.InventoryManagement.repository.DeviceRepository deviceRepository;
    private Device device;

    @BeforeEach
    public void setup() {
        device = new Device(1l, "device-1", "type-1", null);
    }

    @Test
    @Transactional
    void testSaveDevice() {
        Device savedDevice = deviceRepository.save(device);
        assertNotNull(device.getName());
        assertNotNull(device.getId());
        assertNotNull(device.getDeviceType());
    }

    @Test
    @Transactional
    void testGetDevice() {
        Device getDevice = deviceRepository.findById(3l).get();
        assertNotNull(getDevice);
    }

    @Test
    @Transactional
    void testGetAllDevice() {
        List<Device> allDevice = deviceRepository.findAll();
        assertNotNull(allDevice);
    }

    @AfterEach
    void Cleanup() {
        device = null;
    }
}
