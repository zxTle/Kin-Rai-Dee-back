package th.ac.ku.KinRaiDee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.KinRaiDee.model.History;
import th.ac.ku.KinRaiDee.service.HistoryService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/{userId}")
    public List<History> getHistory(@PathVariable String userId)throws ExecutionException,InterruptedException {
        return historyService.getHistoryByUserID(userId);
    }

    @PostMapping("/history")
    public String addHistory(@RequestBody History history)throws ExecutionException, InterruptedException {
        return historyService.create_history(history);
    }
}
