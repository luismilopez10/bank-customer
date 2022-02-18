package com.vobi.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.domain.DocumentType;
import com.vobi.bank.repository.DocumentTypeRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class CustomerServiceIT {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;
	
	@Test
	@Order(1)
	void debeValidarLasDependencias() {
		assertNotNull(customerService);
		assertNotNull(documentTypeRepository);
	}
	
	@Test
	@Order(2)
	void debeCrearUnCustomer() throws Exception {
		//Arrange
		Integer idDocumentType = 1;
		Integer IdCustomer = 14836554;
		
		Customer customer = null;
		DocumentType documentType = documentTypeRepository.findById(idDocumentType).get();
		
		customer = new Customer();
		customer.setAddress("Avenida siempre viva 123");
		customer.setCustId(IdCustomer);
		customer.setDocumentType(documentType);
		customer.setEmail("hjsimpson@gmail.com");
		customer.setEnable("Y");
		customer.setName("Homero J Simpson");
		customer.setPhone("555555555555");
		customer.setToken("awyegakwbdjlaw");
		
		//Act
		customer = customerService.save(customer);
		
		//Assert
		assertNotNull(customer,"El customer es nulo, no se puedo grabar");
	}
	
	@Test
	@Order(3)
	void debeModificarUnCustomer() throws Exception {
		//Arrange
		Integer IdCustomer = 14836554;		
		Customer customer = null;

		customer = customerService.findById(IdCustomer).get();

		customer.setEnable("N");
		
		//Act
		customer = customerService.update(customer);
		
		//Assert
		assertNotNull(customer,"El customer es nulo, no se puedo modificar");
	}
	
	@Test
	@Order(4)
	void debeBorrarUnCustomer() throws Exception {
		//Arrange
		Integer IdCustomer = 14836554;		
		Customer customer = null;
		Optional<Customer> customerOptional = null;
		
		assertTrue(customerService.findById(IdCustomer).isPresent(),"No encontró el customer");
		
		customer = customerService.findById(IdCustomer).get();
		
		//Act
		customerService.delete(customer);
		customerOptional = customerService.findById(IdCustomer);
		
		//Assert
		assertFalse(customerOptional.isPresent(),"No se pudo borrar el customer");
	}
	
	@Test
	@Order(5)
	void debeConsultarTodosLosCustomers() {
		//Arrange
		List<Customer> customers = null;
		
		//Act
		customers = customerService.findAll();
		customers.forEach(customer->log.info(customer.getName()));
		
		
		//Assert
		assertFalse(customers.isEmpty(),"No consultó Customers");		
	}

}

