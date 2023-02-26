package com.enes.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.enes.account.dto.CustomerDto;
import com.enes.account.dto.CustomerDtoConverter;
import com.enes.account.exception.CustomerNotFoundException;
import com.enes.account.model.Customer;
import com.enes.account.repository.CustomerRepository;

public class CustomerServiceTest {
	
	private CustomerService service;
	private CustomerRepository customerRepository;
	private CustomerDtoConverter converter;
	
	@BeforeEach
	public void setUp() {
		customerRepository = mock(CustomerRepository.class);
		converter = mock(CustomerDtoConverter.class);
		service = new CustomerService(customerRepository, converter);
	}
	
	@Test
	public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
		Customer customer = new Customer("id", "name", "surname", Set.of());//değerler test için önemli değil
		
		Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));//when clause yazıyorum ki yönetimim dışındaki seyler çagırıldığında ne yapacağını bilsin
		//yukarısı senaryo için, bundan sonra gerçek yürütme
		
		Customer result = service.findCustomerById("id");
		/*System.out.println(result);
		System.out.println(customer);*/
		assertEquals(result,
				customer);
		
	}
	@Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));

	    }
	@Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer(){
        Customer customer = new Customer("id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("customer-id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("customer-id");

        assertEquals(result,
                customerDto);
    }
	@Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                () -> service.getCustomerById("id"));

        Mockito.verifyNoInteractions(converter);
    }
}
