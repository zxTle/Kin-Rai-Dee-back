package th.ac.ku.KinRaiDee.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.KinRaiDee.model.Account;
import th.ac.ku.KinRaiDee.model.Foods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FoodService {
    public String create_editFoods(Foods foods) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        foods.setFoodId("foodId"+getFoodAll().size());
        ApiFuture<WriteResult> collectionFood = dbFirestore.collection("foods").document(foods.getFoodId()).set(foods);
        return  collectionFood.get().getUpdateTime().toString();
    }

    public List<Foods> getFoodAll() throws ExecutionException, InterruptedException {
        List <Foods> foods = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("foods").get();
        List<QueryDocumentSnapshot> food = allDoc.get().getDocuments();
        for (DocumentSnapshot f : food){
            foods.add(f.toObject(Foods.class));
        }
        return foods;
    }

    public List<Foods> getFoodByType(String type) throws ExecutionException, InterruptedException {
        List<Foods> foodsType = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("foods").whereEqualTo("type",type).get();
        List<QueryDocumentSnapshot> foodsByT = allDoc.get().getDocuments();
        for (DocumentSnapshot docs : foodsByT){
            foodsType.add(docs.toObject(Foods.class));
        }
        return foodsType;
    }

    public List<Foods> getFoodByCate(String category) throws ExecutionException, InterruptedException {
        List<Foods> foodsCate = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("foods").whereEqualTo("category",category).get();
        List<QueryDocumentSnapshot> foodsByT = allDoc.get().getDocuments();
        for (DocumentSnapshot docs : foodsByT){
            foodsCate.add(docs.toObject(Foods.class));
        }
        return foodsCate;
    }

    public List<Foods> getFoodTypeAndCate(String type,String category) throws ExecutionException, InterruptedException {
        List<Foods> foodsTandC = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> allDoc = dbFirestore.collection("foods").whereEqualTo("type",type).whereEqualTo("category",category).get();
        List<QueryDocumentSnapshot> foodsByTandC = allDoc.get().getDocuments();
        for (DocumentSnapshot docs : foodsByTandC){
            foodsTandC.add(docs.toObject(Foods.class));
        }
        return foodsTandC;
    }


    public Foods getFood(String foodId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("foods").document(foodId);

        ApiFuture<DocumentSnapshot> docs = docRef.get();

        DocumentSnapshot foods = docs.get();

        Foods food = null;
        if (foods.exists()){
            food = foods.toObject(Foods.class);
            return food;
        }
        else {
            return null;
        }
    }

    public List<Foods> getFoodRank() throws ExecutionException, InterruptedException {
        List <Foods> foods = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Query query = dbFirestore.collection("foods").orderBy("score", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> allDoc = query.get();
        List<QueryDocumentSnapshot> food = allDoc.get().getDocuments();
        for (DocumentSnapshot f : food){
            foods.add(f.toObject(Foods.class));
        }
        return foods;
    }
}
