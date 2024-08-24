package com.husony.device_service.service.impl;

import com.husony.device_service.pojo.Manufacturer;
import com.husony.device_service.repository.ManufacturerRepository;
import com.husony.device_service.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getManufacturer(Map<String, String> params) {
        return this.manufacturerRepository.getManufacturer(params);
    }

    @Override
    public void addOrUpdate(Manufacturer m) {
        this.manufacturerRepository.addOrUpdate(m);
    }

    @Override
    public Manufacturer getManufacturerById(long id) {
        return this.manufacturerRepository.getManufacturerById(id);
    }

    @Override
    public void deleteManufacturer(long id) {
        this.manufacturerRepository.deleteManufacturer(id);
    }
}
