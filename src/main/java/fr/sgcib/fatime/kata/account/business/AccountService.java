package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Deposit;
import fr.sgcib.fatime.kata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account depose(Deposit deposit, Account account) {
        Account updatedAccount = Account.builder()
                .customer(account.getCustomer())
                .solde(account.getSolde() + deposit.getDeposit())
                .number(account.getNumber())
                .id(account.getId())
                .build();

        return accountRepository.save(updatedAccount);
    }
}
