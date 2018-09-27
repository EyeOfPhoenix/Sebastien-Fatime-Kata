package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.domain.Operation;
import fr.sgcib.fatime.kata.account.exception.AccountNotFoundException;
import fr.sgcib.fatime.kata.account.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static fr.sgcib.fatime.kata.account.domain.OperationType.DEPOSIT;
import static fr.sgcib.fatime.kata.account.domain.OperationType.WITHDRAWAL;

@Service
@Transactional
public class OperationService {
    @Autowired
    AccountService accountService;

    @Autowired
    OperationRepository operationRepository;

    public List<Operation> fetchOperations(String number) {
        Account account = accountService.fetchAccountByNumber(number)
                .orElseThrow(() -> new AccountNotFoundException(String.format("The account %d don't exist!", number)));

        return operationRepository.findByAccount(account);
    }

    public void saveDeposit(Amount amount, Account account) {
        Operation operation = Operation.builder()
                .date(new Date())
                .amount(amount.getAmount())
                .account(account)
                .operationType(DEPOSIT)
                .build();

        operationRepository.save(operation);
    }

    public void saveWithdrawal(Amount amount, Account account) {
        Operation operation = Operation.builder()
                .date(new Date())
                .amount(amount.getAmount())
                .account(account)
                .operationType(WITHDRAWAL)
                .build();

        operationRepository.save(operation);
    }
}
