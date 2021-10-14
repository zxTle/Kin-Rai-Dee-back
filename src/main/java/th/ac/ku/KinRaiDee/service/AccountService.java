package th.ac.ku.KinRaiDee.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.KinRaiDee.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountService {

    public String create_editAccount(Account account) throws ExecutionException, InterruptedException, FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(account.getEmail())
                .setPassword(account.getPassword())
                .setEmailVerified(false);
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        account.setUserId(userRecord.getUid());
        Firestore dbFirestore = FirestoreClient.getFirestore();
        account.setUserId(userRecord.getUid());
        ApiFuture<WriteResult> collectionAccount = dbFirestore.collection("accounts").document(account.getUserId()).set(account);
        return  collectionAccount.get().getUpdateTime().toString();

    }

    public List<Account> getAccAll() throws ExecutionException, InterruptedException {
        List <Account> accounts = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("accounts").get();
        List<QueryDocumentSnapshot> acc = allDoc.get().getDocuments();
        for (DocumentSnapshot docs : acc){
            accounts.add(docs.toObject(Account.class));
        }
        return accounts;
    }

    public Account getAccount(String userId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("accounts").document(userId);

        ApiFuture<DocumentSnapshot> docs = docRef.get();

        DocumentSnapshot acc = docs.get();

        Account account = null;
        if (acc.exists()){
            account = acc.toObject(Account.class);
            return account;
        }
        else {
            return null;
        }
    }

    //Delete Now same Code Post but add String paremeter
}
