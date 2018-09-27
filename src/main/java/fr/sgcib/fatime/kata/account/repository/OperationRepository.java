package fr.sgcib.fatime.kata.account.repository;

import fr.sgcib.fatime.kata.account.domain.Account;
import fr.sgcib.fatime.kata.account.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByAccount(Account account);
}
