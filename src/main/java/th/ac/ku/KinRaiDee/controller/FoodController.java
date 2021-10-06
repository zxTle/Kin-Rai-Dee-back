package th.ac.ku.KinRaiDee.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.KinRaiDee.model.Account;
import th.ac.ku.KinRaiDee.model.Foods;
import th.ac.ku.KinRaiDee.service.FoodService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping("/foods")
    public String addFoods(@RequestBody Foods foods) throws ExecutionException, InterruptedException {
        return foodService.create_editFoods(foods);
    }

    @GetMapping("/foods")
    public List<Foods> getAllFoods() throws ExecutionException, InterruptedException {
        return foodService.getFoodAll();
    }
    @GetMapping("/foods/id/{foodId}")
    public Foods getFood(@PathVariable String foodId) throws ExecutionException, InterruptedException {
        return foodService.getFood(foodId);
    }

    @GetMapping("/foods/{type}")
    public List<Foods> getFoodByType(@PathVariable String type) throws ExecutionException, InterruptedException {
        return foodService.getFoodByType(type);
    }

    @GetMapping("/foods/category/{category}")
    public List<Foods> getFoodByCate(@PathVariable String category) throws ExecutionException, InterruptedException {
        return  foodService.getFoodByCate(category);
    }

    @GetMapping("/foods/คาว/{category}")
    public List<Foods> getFoodByT1andC(@PathVariable String category) throws ExecutionException, InterruptedException {
        return foodService.getFoodTypeAndCate("คาว",category);
    }

    @GetMapping("/foods/หวาน/{category}")
    public List<Foods> getFoodByT2andC(@PathVariable String category) throws ExecutionException, InterruptedException {
        return foodService.getFoodTypeAndCate("หวาน",category);
    }

    @GetMapping("/foods/rank")
    public List<Foods> getRank() throws ExecutionException, InterruptedException {
        return foodService.getFoodRank();
    }
    @PutMapping("/foods")
    public String updateFoods(@RequestBody Foods foods) throws ExecutionException, InterruptedException {
        return foodService.create_editFoods(foods);
    }
}
