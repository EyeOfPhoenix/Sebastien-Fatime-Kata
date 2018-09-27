package fr.sgcib.fatime.kata.account.business;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Amount;
import fr.sgcib.fatime.kata.account.domain.Customer;
import fr.sgcib.fatime.kata.account.exception.InsufficientBalanceException;
import fr.sgcib.fatime.kata.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @Mock
    OperationService operationService;

    @Spy
    @InjectMocks
    AccountService accountService;

    @Test
    public void should_make_a_deposit_in_a_given_account() {
        Customer customer = Customer.builder().build();
        Account account = Account.builder()
                .balance(1000L)
                .customer(customer)
                .number("A123456B")
                .build();
        Account updatedAccount = Account.builder()
                .balance(2000L)
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
                .balance(2000L)
                .customer(customer)
                .number("A123456B")
                .build();
        Account expectedAccount = Account.builder()
                .balance(1000L)
                .customer(customer)
                .number("A123456B")
                .build();
        doReturn(expectedAccount).when(accountRepository).save(expectedAccount);

        Account updatedAccount = accountService.withdrawal(new Amount(1000L), account);

        verify(accountRepository, times(1)).save(Mockito.any(Account.class));
        assertThat(updatedAccount).isEqualToComparingFieldByField(expectedAccount);
    }

    @Test
    public void should_unauthorized_a_customer_to_make_a_widthdrawal_if_the_balance_is_below_the_amount() {
        Account account = Account.builder()
                .balance(1000L)
                .customer(Customer.builder().build())
                .build();

        assertThatThrownBy(() -> accountService.withdrawal(Amount.builder().amount(2000L).build(), account))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage(String.format("Votre solde actuel est de %d, impossible de continuer votre demande.", account.getBalance()));
    }

    @Test
    public void should_fetch_an_account_by_number() {
        Account expectedAccount = Account.builder()
                .balance(3000L)
                .customer(Customer.builder().build())
                .number("A123456B")
                .build();
        doReturn(expectedAccount).when(accountRepository).findByNumber("A123456B");

        Account account = accountService.fetchAccountByNumber("A123456B").get();

        verify(accountRepository, times(1)).findByNumber("A123456B");
        verify(accountRepository).findByNumber("A123456B");
        assertThat(account).isEqualToComparingFieldByField(expectedAccount);
    }

    @Test
    public void should_save_a_deposit_operation_in_the_history() {
        Amount amount = Amount.builder().amount(1000L).build();
        Account account = Account.builder().balance(3000L).build();

        accountService.depose(amount, account);

        verify(operationService, times(1)).saveDeposit(amount, account);
    }

    @Test
    public void should_save_a_withdrawal_operation_in_the_history() {
        Amount amount = Amount.builder().amount(1000L).build();
        Account account = Account.builder().balance(3000L).build();

        accountService.withdrawal(amount, account);

        verify(operationService, times(1)).saveWithdrawal(amount, account);
    }
}
