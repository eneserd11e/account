 package com.enes.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enes.account.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
//Crudrepositroyde kullanılıyor bazı yerlerde. jparepositoryden farkı findAll gibi bir sorgu attıgımızda jpa liste döner crud ise iterator döner.
//jpa nın kullanımı daha kolaydır liste donduğu icin iteroter daha zahmetlidir liste cevirecen, iterate edecen hasnext yapacan filan zahmetlidir ama daha güvenli ve daha hızlıdır.