package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Operation;
import fr.sgcib.fatime.kata.account.exception.AccountNotFoundException;
import fr.sgcib.fatime.kata.account.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OperationService {
    @Autowired
    AccountService accountService;

    @Autowired
    OperationRepository operationRepository;

    List<Operation> fetchOperations(String number) {
        Account account = accountService.fetchAccountByNumber(number)
                .orElseThrow(() -> new AccountNotFoundException(String.format("The account %d don't exist!", number)));

        return operationRepository.findByAccount(account);
    }
}
