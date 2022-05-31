package org.acme.api;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.acme.repository.TransactionRepository;
import org.acme.transaction.model.Transaction;
import org.acme.transaction.model.TransactionType;





@Path("/transactions")
@Consumes("application/json")
@Produces("application/json")
public class TransactionResource {

    

    @Inject
    TransactionRepository transactionRepository;

    @POST
    public Transaction addTransaction(Transaction transaction) {
        transactionRepository.persist(transaction);
        return transaction;
    }

    @GET
    @Path("/{account}")
    public List<Transaction> getTransactionsByAccount(@PathParam("account") String account) {
        return transactionRepository.findByAccount(account);
    }

    @GET
    @Path("/{account}/date/{date}")
    public List<Transaction> getTransactionsByAccountAndDate(@PathParam("account") String account, @PathParam("date") String dateString) {
        

       
        LocalDate localDate = LocalDate.parse(dateString);
        
        return transactionRepository.findByAccountAndDate(account, localDate);
    }

    @GET
    @Path("/description/{description}")
    public List<Transaction> getTransactionsByDescription(@PathParam("description") String description) {
        return transactionRepository.findByDescription(description);
    }

    @GET
    @Path("/balance/{account}")
    public Double getBalance(@PathParam("account") String account) {
        return calculateBalance(transactionRepository.findByAccount(account));
    }

    private Double calculateBalance(List<Transaction> transactions) {
        Double balance = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.CREDIT) {
                balance += transaction.getValue();
            } else {
                balance -= transaction.getValue();
            }
        }
        return balance;
    }

    
}
