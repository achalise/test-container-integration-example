package com.example.testcontainerintegration.repositories;

import com.example.testcontainerintegration.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
}
