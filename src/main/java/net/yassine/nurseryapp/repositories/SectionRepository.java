package net.yassine.nurseryapp.repositories;

import net.yassine.nurseryapp.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Integer> {

Section findSectionBySectionName(String sectionName);



}
