package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Customer;
import fr.sgcib.fatime.kata.account.domain.Deposit;
import fr.sgcib.fatime.kata.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @Spy
    @InjectMocks
    AccountService accountService;


    @Test
    public void should_make_a_deposit_in_a_given_account() {
        Customer customer = Customer.builder().build();
        Account account = Account.builder()
                .solde(1000L)
                .customer(customer)
                .number("A123456B")
                .build();
        Account updatedAccount = Account.builder()
                .solde(2000L)
                .customer(customer)
                .number("A123456B")
                .build();
        doReturn(updatedAccount).when(accountRepository).save(updatedAccount);

        Account expectedAccount = accountService.depose(new Deposit(1000L), account);

        verify(accountRepository, times(1)).save(Mockito.any(Account.class));
        assertThat(expectedAccount).isEqualToComparingFieldByField(updatedAccount);
    }
}
