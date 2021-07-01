package com.example.testcontainerintegration.repositories;

import com.example.testcontainerintegration.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = TransactionRepositoryTest.DockerPostgreDataSourceInitializer.class)
class TransactionRepositoryTest {

    public static MySQLContainer container = new MySQLContainer<>("mysql:8.0.23");

    static {
        container.start();
    }

    public static class DockerPostgreDataSourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword()
            );
        }
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