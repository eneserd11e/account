package com.enes.account.dto

import java.math.BigDecimal
import java.time.LocalDateTime

//her modele karşılık gelen dto olmasına gerek yok ama api üzerinden dışarıya göndereceğim modellerin dtosunu oluşturmak önemlidir.
//Aynısı modellde var niye bir daha oluşturuyuz diye düşünmeyin. modelimiizin içerisinde olan ama dışarıya göndermizi gerektirmeyen bilgileri buraya eklemeyebiliriz.
//yada modelin içerisinde olmayan ama dto içerisine koymak isteğim başka değerliride koyabilirim.
//dtolar api responslarında daha esnek olmamızı daha kontrolu elde tutmamızı sağlıyor.
//burda yeni bir özellik eklemek istediğimizde veya silmek isteğimizde kolayca ekleyebiliriz. ama bu dto classını oluşturmadığımızda bütn kodu değiştirmek zorunda kalabiliriz.
//böylece tek bir yerden istediğim değişikliği yapmış olacağım. dto'nun önemi bu. esneklik sağlayabilmek. ileriye yönelik değişkenliğe açık olması
//SOLID: OPEN CLOSED ÖZELLİĞİ. DEĞİSİKLİKLERE AÇIK OLMASIDIR.
data class AccountDto (
		val id:String?,
		val balance: BigDecimal?,
		val creationDate: LocalDateTime?,
		val customer: AccountCustomerDto?,
		val transactions: Set<TransactionDto>?
)
