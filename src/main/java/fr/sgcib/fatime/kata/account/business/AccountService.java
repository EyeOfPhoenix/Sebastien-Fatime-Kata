package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.exception.InsufficientBalanceException;
import fr.sgcib.fatime.kata.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationService operationService;

    public Account depose(Amount amount, Account account) {
        Account updatedAccount = Account.builder()
                .customer(account.getCustomer())
                .solde(account.getSolde() + amount.getAmount())
                .number(account.getNumber())
                .id(account.getId())
                .build();

        operationService.saveDeposit(amount, account);
        return accountRepository.save(updatedAccount);
    }

    public Account withdrawal(Amount amount, Account account) {
        if(amount.getAmount() > account.getSolde()) {
            throw new InsufficientBalanceException(String.format("Votre solde actuel est de %d, impossible de continuer votre demande.", account.getSolde()));
        }


        Account updatedAccount = Account.builder()
                .customer(account.getCustomer())
                .solde(account.getSolde() - amount.getAmount())
                .number(account.getNumber())
                .id(account.getId())
                .build();

        operationService.saveWithdrawal(amount, account);
        return accountRepository.save(updatedAccount);
    }

    public Optional<Account> fetchAccountByNumber(String number) {
        return Optional.of(accountRepository.findByNumber(number));
    }
}
