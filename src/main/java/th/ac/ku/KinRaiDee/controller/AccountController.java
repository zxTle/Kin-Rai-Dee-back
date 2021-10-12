package th.ac.ku.KinRaiDee.controller;


import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.KinRaiDee.model.Account;
import th.ac.ku.KinRaiDee.service.AccountService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public String setAccount(@RequestBody Account account) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return accountService.create_editAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccount() throws ExecutionException, InterruptedException {
        return accountService.getAccAll();
    }

    @GetMapping("/accounts/{userId}")
    public Account getAccount(@PathVariable String userId) throws ExecutionException, InterruptedException {
        return accountService.getAccount(userId);
    }

    @PutMapping("/accounts")
    public String updateAccount(@RequestBody Account account) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return accountService.create_editAccount(account);
    }
}
