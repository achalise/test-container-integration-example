package com.example.testcontainerintegration.repositories;

import com.example.testcontainerintegration.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class TransactionRepositoryTestWithoutContextConfiguration {

    @Container
    static MySQLContainer container = new MySQLContainer<>("mysql:8.0.23");

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testEntityCanBeSavedAndRetrieved() {
        Transaction tx = new Transaction();
        tx.setId("test1");
        tx.setName("TestTransaction");
        tx.setDescription("TestDescription");
        Transaction saved = transactionRepository.save(tx);
        assertEquals(saved.getDescription(), tx.getDescription());
    }
}
