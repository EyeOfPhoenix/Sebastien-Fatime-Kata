package fr.sgcib.fatime.kata.account.repository;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;


@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    private Account expectedAccount;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void init() {
        expectedAccount = Account.builder()
                .id(1L)
                .number("A123456A")
                .balance(1000L)
                .customer(Customer.builder()
                        .id(1L)
                        .firstname("Sébastien")
                        .name("Fatime")
                        .build())
                .build();

        Customer customer = Customer.builder().firstname("Sébastien").name("Fatime").build();
        Account account = Account.builder().number("A123456A").balance(1000L).build();

        customer.setAccount(account);
        account.setCustomer(customer);

        testEntityManager.persist(account);
    }

    @Test
    public void should_fetch_account_by_number() {
        Account account = accountRepository.findByNumber("A123456A");

        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(expectedAccount.getId());
        assertThat(account.getNumber()).isEqualTo(expectedAccount.getNumber());
        assertThat(account.getBalance()).isEqualTo(expectedAccount.getBalance());
        assertThat(account.getCustomer().getId()).isEqualTo(expectedAccount.getCustomer().getId());
        assertThat(account.getCustomer().getFirstname()).isEqualTo(expectedAccount.getCustomer().getFirstname());
        assertThat(account.getCustomer().getName()).isEqualTo(expectedAccount.getCustomer().getName());
    }

    @Test
    public void should_save_an_account_with_a_number() {
        Account account = Account.builder()
                .id(1L)
                .customer(Customer.builder().id(1L).build())
                .balance(1000L)
                .build();

        accountRepository.save(account);

        assertThat(testEntityManager.find(Account.class, account.getId())
                .getBalance()).isEqualTo(account.getBalance());
    }
}
