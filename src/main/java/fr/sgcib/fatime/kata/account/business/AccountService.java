package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account depose(Amount amount, Account account) {
        Account updatedAccount = Account.builder()
                .customer(account.getCustomer())
                .solde(account.getSolde() + amount.getAmount())
                .number(account.getNumber())
                .id(account.getId())
                .build();

        return accountRepository.save(updatedAccount);
    }

    public Account withdrawal(Amount amount, Account account) {
        Account updatedAccount = Account.builder()
                .customer(account.getCustomer())
                .solde(account.getSolde() - amount.getAmount())
                .number(account.getNumber())
                .id(account.getId())
                .build();

        return accountRepository.save(updatedAccount);
    }

    public Optional<Account> fetchAccountByNumber(String number) {
        return Optional.of(accountRepository.findByNumber(number));
    }
}
