package th.ac.ku.KinRaiDee.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.KinRaiDee.model.History;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class HistoryService {

    public String create_history(History history)throws ExecutionException, InterruptedException {
//        UUID uuid = UUID.randomUUID();
//        history.setHistoryId(uuid);
        String key = history.getUserId()+history.getFoodId();
        history.setHistoryId(key);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionHistory = dbFirestore.collection("history").document(history.getHistoryId()).set(history);
        return collectionHistory.get().getUpdateTime().toString();
    }

    public List<History> getHistoryByUserID(String userId)throws ExecutionException, InterruptedException {
        List<History> historiesByUserId = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("history").whereEqualTo("userId",userId).get();
        List<QueryDocumentSnapshot> histories = allDoc.get().getDocuments();
        for (DocumentSnapshot docs : histories){
            historiesByUserId.add(docs.toObject(History.class));
        }
        return historiesByUserId;
    }
}
