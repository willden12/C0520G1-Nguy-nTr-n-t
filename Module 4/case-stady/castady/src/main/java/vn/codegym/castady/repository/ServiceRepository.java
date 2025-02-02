package vn.codegym.castady.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.codegym.castady.model.Services;


public interface ServiceRepository extends JpaRepository<Services, String> {
    Page<Services> findServiceByServiceIdContainingOrServiceNameContaining(String id, String name, Pageable pageable);
}
