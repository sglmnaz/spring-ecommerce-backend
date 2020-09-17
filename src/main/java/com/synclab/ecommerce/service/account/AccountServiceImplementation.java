package com.synclab.ecommerce.service.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synclab.ecommerce.model.Account;
import com.synclab.ecommerce.repository.AccountRepository;
import com.synclab.ecommerce.utility.exception.RecordNotFoundException;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account insert(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account UpdateById(Long id, Account account) throws Exception {
        
        if(findById(id) == null)
            throw new RecordNotFoundException(); 

        Account newAccount = account;
        newAccount.setAccountId(id);
        accountRepository.save(newAccount);
        
        return account  ;
    }

    @Override
    public Account PatchById(Long id, Account account) throws Exception {
       
        Account newAccount = findById(id).get();
        if( newAccount == null)
            throw new RecordNotFoundException(); 

        if(account.getIsBanned() != null)
            newAccount.setIsBanned(account.getIsBanned());
        if(account.getIsSuspended() != null)
            newAccount.setIsSuspended(account.getIsSuspended());
        if(account.getAccountId() != null)
            newAccount.setAccountId(account.getAccountId());


        accountRepository.save(newAccount);
    
        return account  ;
    }

    @Override
    public void DeleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

  
    

}
