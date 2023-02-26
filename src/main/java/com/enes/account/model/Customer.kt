package com.enes.account.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import org.hibernate.annotations.GenericGenerator
import javax.persistence.OneToMany
import javax.persistence.FetchType
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Customer (
	@Id
	@GeneratedValue(generator="UUID")//tahmin edilebilir Idler olmaması için. her yeni kayıtta otomatik olarak oluşturulsun
	@GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
	val id:String?,
	
	val name:String?,
	val surname:String?,
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER)
	val accounts:Set<Account>
){
	
	
	override fun equals(other: Any?):Boolean{
		if(this === other) return true
		if(javaClass != other?.javaClass) return false
		
		other as Customer
		
		if (id != other.id) return false
		if (name != other.name) return false
		if (surname != other.surname) return false
		if (accounts != other.accounts) return false
		
		return true
	}
	
	override fun hashCode(): Int{
		var result = id?.hashCode()?:0
		result= 31 * result +(name?.hashCode()?:0)
		result= 31 * result +(surname?.hashCode()?:0)
		return result
		
	}
}
