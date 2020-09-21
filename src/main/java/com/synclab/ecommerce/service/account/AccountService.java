package com.synclab.ecommerce.service.account;

import java.util.List;
import java.util.Optional;

import com.synclab.ecommerce.model.Account;

public interface AccountService {

    //C
    Account insert(Account account);
    //R
	List<Account> findAll();
	Optional<Account> findById(Long id);
	Account findByEmail(String email);
    //U
    Account UpdateById(Long id, Account account) throws Exception;
    Account PatchById(Long id, Account account) throws Exception;
    //D
    void DeleteById(Long id);
    void deleteAll();

}