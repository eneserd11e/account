package com.enes.account.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import org.hibernate.annotations.GenericGenerator
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.CascadeType
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import java.time.LocalDateTime
import java.math.BigDecimal


@Entity
data class Account (
	@Id
	@GeneratedValue(generator="UUID")//tahmin edilebilir Idler olmaması için. her yeni kayıtta otomatik olarak oluşturulsun
	@GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
	val id:String? ="",//val immutable özelliğini barındır.//?=bu alan boş olabilir//kotlin compileri soru işareti görünce bütün kombinasyonlarla consructer üretiyor.biz ihtiyacımız olanı kullanıyoruz
	val balance:BigDecimal? = BigDecimal.ZERO,
	val creationDate: LocalDateTime,
			
	@ManyToOne(fetch= FetchType.LAZY, cascade = [CascadeType.ALL])//all account nesne güncellendiğinde veya silindiğinde veya insert edildiğinde git customerida güncelle demek.
	@JoinColumn(name = "customer_id", nullable= false)
    val customer:Customer?,
			
	@OneToMany(mappedBy="account", fetch= FetchType.EAGER,cascade= [CascadeType.ALL])//mapped by içine yazdığımız ilişki kuracagımız nesnenin ismi olmalı.yani burda Transaction içindeki account
	val transaction: Set<Transaction> = HashSet()
	
	
){
	constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) : this(
        "",
        customer = customer,
        balance = balance,
        creationDate = creationDate
    )
	//onetomany manytoone ilişkilerinde directional bir ilişki kurduğumuz için hashcodemuzu kendimiz üretmemiz gerekiyor öbür türlü loopa girmiş oluyor.hashcodelar üzerinde
	//karşılaştırma yaptığı için
	override fun equals(other: Any?):Boolean{//burda kendi iş modelimizi yazabiliriz. yani bizim örnek olarak id ve balance eşit olması yeterli diğerleri eşit olmasada olur
		//dediğimizde id ve balance yazarız buraya ve equals methodu ile iki nesneyi karşilaştıracağımızda sadece bu ikisini kontrol eder ve bu değerler eşit ve diğerleri farklı
		//olsa bile bu ikisi eşit ise true döner.
		if (this === other) return true
		if(javaClass != other?.javaClass)return false
		
		other as Account
		if (id!= other.id) return false
		if (balance!= other.balance) return false
		if (creationDate!= other.creationDate) return false
		if (customer!= other.customer) return false
		if (transaction!= other.transaction) return false

		return true
	}
	override fun hashCode():Int{//hash veri tabanındaki karşılığı budur demek
		var result = id?.hashCode()?:0
		result = 31 * result + (balance?.hashCode()?:0)
		result = 31 * result + creationDate.hashCode()
		result = 31 * result + (customer?.hashCode()?:0)
		
		return result
	}
	//@entity //veri tabanı table'nı karşılık gelmesi için
	//classın özellikleri hash code ile geliyor
	//immutable data oluşturuyor
	//tostring ile geliyor
	//getter setter gibi şeylerle ugraşmıyoruz
}