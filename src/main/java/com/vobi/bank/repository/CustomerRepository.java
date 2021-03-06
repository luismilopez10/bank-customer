package com.vobi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vobi.bank.domain.Customer;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findByEnable(String enable);
	List<Customer> findByNameLike(String name);
}
