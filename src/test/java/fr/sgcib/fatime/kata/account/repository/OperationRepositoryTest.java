package fr.sgcib.fatime.kata.account.repository;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Customer;
import fr.sgcib.fatime.kata.account.domain.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static fr.sgcib.fatime.kata.account.domain.OperationType.DEPOSIT;
import static fr.sgcib.fatime.kata.account.domain.OperationType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OperationRepositoryTest {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    Account account;
    Operation deposit;
    Operation withdrawal;

    @Before
    public void init() {
        account = Account.builder()
                .customer(Customer.builder().build())
                .solde(1000L)
                .number("A123456B")
                .build();
        deposit = Operation.builder()
                .operationType(DEPOSIT)
                .account(account)
                .amount(3000L)
                .date(new Date(98765L))
                .build();
        withdrawal = Operation.builder()
                .operationType(WITHDRAWAL)
                .account(account)
                .amount(1000L)
                .date(new Date(98765L))
                .build();
        testEntityManager.persist(account);
        testEntityManager.persist(deposit);
        testEntityManager.persist(withdrawal);
    }


    @Test
    public void should_get_all_operations_from_an_account() {
        List<Operation> operations = operationRepository.findByAccount(account);

        assertThat(operations).isNotNull()
                .hasSize(2)
                .containsExactly(deposit, withdrawal);
    }
}