package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Operation;
import fr.sgcib.fatime.kata.account.repository.OperationRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static fr.sgcib.fatime.kata.account.domain.OperationType.DEPOSIT;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {
    @Mock
    OperationRepository operationRepository;

    @Mock
    AccountService accountService;

    @Spy
    @InjectMocks
    OperationService operationService;

    @Test
    public void should_get_all_operations_from_an_account() {
        Account accountExcepted = Account.builder()
                .number("A123456B")
                .solde(1000L)
                .build();
        List<Operation> operationsExpected = asList(Operation.builder()
                .date(new Date(987654L))
                .amount(2000L)
                .account(accountExcepted)
                .operationType(DEPOSIT)
                .build(), Operation.builder()
                .date(new Date(987654L))
                .amount(4000L)
                .account(accountExcepted)
                .operationType(DEPOSIT)
                .build());
        doReturn(Optional.of(accountExcepted)).when(accountService).fetchAccountByNumber("A123456B");
        doReturn(operationsExpected).when(operationRepository).findByAccount(accountExcepted);

        List<Operation> operations = operationService.fetchOperations("A123456B");

        verify(accountService, times(1)).fetchAccountByNumber("A123456B");
        verify(operationRepository, times(1)).findByAccount(accountExcepted);

        assertThat(operations)
                .hasSize(2)
                .contains(operationsExpected.get(0), operationsExpected.get(1));
    }
}
