package com.codegym.casestady4.service.impl;

import com.codegym.casestady4.model.Services;
import com.codegym.casestady4.repository.ServiceRepository;
import com.codegym.casestady4.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Services> findAll(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Page<Services> findByIdAndName(String input, Pageable pageable) {
        return serviceRepository.findServiceByServiceIdContainingOrServiceNameContaining(input, input, pageable);
    }

    @Override
    public Iterable<Services> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Services findById(String id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Services services) {
        serviceRepository.save(services);
    }

    @Override
    public void deleteById(String id) {
        serviceRepository.deleteById(id);
    }
}
