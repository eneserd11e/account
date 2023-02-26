package com.enes.account.dto

import java.math.BigDecimal
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Min

//account servicesine hizmet eden farklı requestlerimiz olabilir. örneğin update. aynı fieldları içere bilir bu requestler fakat birbirinden farklı olması kodun 
//okunabiliriğini artırır.createAccountRequest create sürecinde kullanılırken updateAccountRequest update sürecinde kullanılır. yarın ben createAccountRequestine başka bir
// field eklemek istediğimde update sürecini etkilemesin eğer ortak kullanırsam.
data class CreateAccountRequest (
	//field kotlin sınıfı olduğu için yazıldı.normalde yazmıyoruz
		@field:NotBlank //:string'in boş olmaması için
		val customerId: String,
		@field:Min(0)//0 dan küçük olamaz 
		val initialCredit: BigDecimal,
)
