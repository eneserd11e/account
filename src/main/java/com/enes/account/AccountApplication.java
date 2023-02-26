package com.enes.account;

import java.time.Clock;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.enes.account.model.Customer;
import com.enes.account.repository.CustomerRepository;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner{
	
	private final CustomerRepository customerRepository;
	
	public AccountApplication(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRepository.save(new Customer("", "hüseyin", "sdf", new HashSet<>()));
		System.out.println(customer);
	}
	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}
	
	// sudo docker build . -t proje:1.0
	
	//docker ps -a // portta çalışan uygulamaları gösterir
	
	//sudo docker run --name account -d -p 9090:8080 account:1.0 //docker projeyi ayaga kaldırıyor
	//sudo docker run --name account -d -p 9090:8080 proje:1.0 //proje diye yazmışım
	
	//docker container rm -f projeid

}
