package fr.sgcib.fatime.kata;

import fr.sgcib.fatime.kata.account.business.AccountService;
import fr.sgcib.fatime.kata.account.business.OperationService;
import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan({"fr.sgcib.fatime.kata.account"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories
public class KataApplication implements CommandLineRunner {
    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    public static void main(String[] args) {
        SpringApplication.run(KataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //*********//
        //   US1   //
        //*********//

        if (args.length == 2 && Long.valueOf(args[2]) == 1) {
            String accountNumber = args[0];
            Amount amount = new Amount(Long.valueOf(args[1]));
            Account account = accountService.fetchAccountByNumber(accountNumber)
                    .orElseThrow(() -> new AccountNotFoundException(String.format("The account %d not found!", accountNumber)));

            accountService.depose(amount, account);
        }

        //*********//
        //   US2   //
        //*********//

        if (args.length == 3 && Long.valueOf(args[2]) == 2) {
            String accountNumber = args[0];
            Amount amount = new Amount(Long.valueOf(args[1]));
            Account account = accountService.fetchAccountByNumber(accountNumber)
                    .orElseThrow(() -> new AccountNotFoundException(String.format("The account %d not found!", accountNumber)));

            accountService.withdrawal(amount, account);
        }

        //*********//
        //   US3   //
        //*********//

        if (args.length == 2 && Long.valueOf(args[1]) == 3) {
            String accountNumber = args[0];

            operationService.fetchOperations(accountNumber);
        }
    }
}
