package fr.sgcib.fatime.kata.account.repository;

import fr.sgcib.fatime.kata.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long>{
    Account findByNumber(String number);
}
