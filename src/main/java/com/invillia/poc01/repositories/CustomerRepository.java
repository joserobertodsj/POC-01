package com.invillia.poc01.repositories;

import com.invillia.poc01.models.CustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
    boolean existsByEmail(String email);

    boolean existsByDocumentNumber(String documentNumber);

    Page<CustomerModel> findByNameIgnoreCase (String name, Pageable pageable);


}
