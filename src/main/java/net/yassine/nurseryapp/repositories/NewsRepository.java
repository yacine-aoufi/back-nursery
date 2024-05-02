package net.yassine.nurseryapp.repositories;


import net.yassine.nurseryapp.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface NewsRepository extends JpaRepository<News,Integer> {







}
