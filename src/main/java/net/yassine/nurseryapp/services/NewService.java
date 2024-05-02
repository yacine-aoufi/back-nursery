package net.yassine.nurseryapp.services;


import net.yassine.nurseryapp.entities.Child;
import net.yassine.nurseryapp.entities.News;
import net.yassine.nurseryapp.entities.NewsRegistration;
import net.yassine.nurseryapp.repositories.ChildRepository;
import net.yassine.nurseryapp.repositories.NewsRegistrationRepository;
import net.yassine.nurseryapp.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class NewService {


private final NewsRepository newsRepository;
    public NewService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    @Autowired
    private ChildRepository childRepository;
@Autowired
private NewsRegistrationRepository newsRegistrationRepository;



    public void AddNews(News news){
newsRepository.save(news);
}

public List<News> GetNews(){
        return newsRepository.findAll();
}



public void joinNews(Integer idChild,Integer NewsId){

  News news=  newsRepository.findById(NewsId).orElse(null);
    Child child=childRepository.findById(idChild).orElse(null);

   NewsRegistration newsRegistration= NewsRegistration.builder()
           .news(news)
           .child(child)
           .isPayed(false)
           .registrationDate(Date.from(Instant.now()))
           .build();

   newsRegistrationRepository.save(newsRegistration);

}













}
