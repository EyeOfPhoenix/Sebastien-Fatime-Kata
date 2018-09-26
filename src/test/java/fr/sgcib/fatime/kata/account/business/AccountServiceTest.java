package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.domain.Customer;
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

        Account expectedAccount = accountService.depose(new Amount(1000L), account);

        verify(accountRepository, times(1)).save(Mockito.any(Account.class));
        assertThat(expectedAccount).isEqualToComparingFieldByField(updatedAccount);
    }

    @Test
    public void should_make_a_withdrawal_in_a_given_account() {
        Customer customer = Customer.builder().build();
        Account account = Account.builder()
                .solde(2000L)
                .customer(customer)
                .number("A123456B")
                .build();
        Account expectedAccount = Account.builder()
                .solde(1000L)
                .customer(customer)
                .number("A123456B")
                .build();
        doReturn(expectedAccount).when(accountRepository).save(expectedAccount);

        Account updatedAccount = accountService.withdrawal(new Amount(1000L), account);

        verify(accountRepository, times(1)).save(Mockito.any(Account.class));
        assertThat(updatedAccount).isEqualToComparingFieldByField(expectedAccount);
    }

    @Test
    public void should_fetch_an_account_by_number() {
        Account expectedAccount = Account.builder()
                .solde(3000L)
                .customer(Customer.builder().build())
                .number("A123456B")
                .build();
        doReturn(expectedAccount).when(accountRepository).findByNumber("A123456B");

        Account account = accountService.fetchAccountByNumber("A123456B").get();

        verify(accountRepository, times(1)).findByNumber("A123456B");
        verify(accountRepository).findByNumber("A123456B");
        assertThat(account).isEqualToComparingFieldByField(expectedAccount);
    }
}
