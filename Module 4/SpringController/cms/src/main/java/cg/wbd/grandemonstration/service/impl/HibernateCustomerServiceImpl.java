package cg.wbd.grandemonstration.service.impl;

import cg.wbd.grandemonstration.model.Customer;
import cg.wbd.grandemonstration.service.CustomerService;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

public class HibernateCustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Customer findOne(Long id) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> save(List<Customer> customers) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Customer> findAll(List<Long> ids) {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public void delete(Customer customer) {
    }

    @Override
    public void delete(List<Customer> customers) {
    }

    @Override
    public void deleteAll() {
    }
    @Bean
    public CustomerService customerService() {
        return new HibernateCustomerServiceImpl();
    }
}
