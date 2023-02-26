package com.enes.account.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.enes.account.dto.AccountDto;
import com.enes.account.dto.AccountDtoConverter;
import com.enes.account.dto.CreateAccountRequest;
import com.enes.account.model.Account;
import com.enes.account.model.Customer;
import com.enes.account.model.Transaction;
import com.enes.account.repository.AccountRepository;

@Service //service an. görünce spring accountservice oluşturmam gerekiyor diyecek. consructur'i çalıştıcak ve service nesnesini kullanan ihtiyacımız olan bütün alt injectionları 
//otomatik bir şekilde oluşturup inject ediyor service'nin içerisindeki. private final yapınca onun bir tane değeri olması gerekiyor bir initialize değeri ve bu bir daha değişltirilemez
//demek zaten consructer yapmadığımız zaman derleyici hata verecek
public class AccountService {

	private final AccountRepository accountRepository;//autowired yapıtığımızda immutable olmuyor
	private final CustomerService customerService;
	private final TransactionService transactionService;
	private final AccountDtoConverter converter;//convertı component yaptığım için inject edebiliyorum.eğer component olmasaydı inject edemezdim.
	private final Clock clock;
	
	
	public AccountService(AccountRepository accountRepository, CustomerService customerService,
			TransactionService transactionService, AccountDtoConverter converter,Clock clock) {
		super();
		this.accountRepository = accountRepository;
		this.customerService = customerService;
		this.transactionService = transactionService;
		this.converter = converter;//inject
		this.clock = clock;
	}




	public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
		Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
		
		Account account = new Account(customer,
				createAccountRequest.getInitialCredit(),
				LocalDateTime.now());
		
		//compare > ise 1, eşitse 0, < ise -1
		if(createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
			Transaction transaction = new Transaction(
                    createAccountRequest.getInitialCredit(),
                    getLocalDateTimeNow(),
                    account);
			account.getTransaction().add(transaction);//incele burayı
		}
		return converter.convert(accountRepository.save(account));
	}
	private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
//eskiden servicelerin interfaceleri yazılmasının sebebi injection yapılırken account servici başka bir yere inject ettiğim zaman bu interfacesi kullanıyorduk önceden.
//spring boot içerisinde independcy injection inverjıonof controller ile interface gerek duymadan injectionları yapabilmemizi sağlıyor. account service nesnesini bir kere
//singleton olarak oluşturuyor. butün uygulama lifecycle' da bu account servisin singelton instancesi her yerde kullanılabiliryor. spring boot ile birlikte artık service
//interfacelerine gerek yok. bir design pattern kullanacaksak eğer buna gerek yok. bu hiç bir zaman interface kullanmayın demek değil bir service dışarıya expose etmek ise buna gerek
//yok