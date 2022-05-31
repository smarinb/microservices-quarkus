package org.acme.repository;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.acme.transaction.model.Transaction;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class TransactionRepository implements PanacheMongoRepository<Transaction> {

    public List<Transaction> findByAccount(String account) {
       return list("account", account);
    }

    public List<Transaction> findByAccountAndDate(String account, LocalDate date) {
        return find("account = ?1 and date = ?2", account, date).list();
    }

    public List<Transaction> findByDescription(String description) {
        String regex ="(?i).*" + description + ".*";
        return find("description = ?1", regex).list();
    }
    
}
    

