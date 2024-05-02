package net.yassine.nurseryapp.web;


import net.yassine.nurseryapp.entities.News;
import net.yassine.nurseryapp.services.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("News")
public class NewsManageController {

 private final NewService newService;
    public NewsManageController(NewService newService) {
        this.newService = newService;
    }


    @PostMapping("/addNews")
    public ResponseEntity<String> AddNews(@RequestBody News news){
        newService.AddNews(news);
        return ResponseEntity.ok("Added Successfully");
    }


    @GetMapping("/getNews")
    public ResponseEntity<List<News>> GetAllnews(){
        return ResponseEntity.ok(newService.GetNews());
    }





















}
