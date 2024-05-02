package net.yassine.nurseryapp.services;


import net.yassine.nurseryapp.Dto.ChildDto;
import net.yassine.nurseryapp.entities.Child;
import net.yassine.nurseryapp.repositories.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {

    private final ChildRepository childRepository;
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }


    public static ChildDto ToDTO(Child child) {

        if (child==null){
            return null;
        }

        return new ChildDto(
                child.getId(),
                child.getFirstName(),
                child.getLastName(),
                child.getBirthdate(),
                child.getGender(),
                child.getStatus(),
                null
                ,
//                child.getParent(),
                child.getChildRegistration(),
//                child.getActivitiesRegistrations(),
//                child.getPreRegistration(),
//                child.getNewsRegistrationList(),
                child.getSection()
        );


    }
    public static Child ToEntity(ChildDto childDto) {

        Child child = new Child();
        child.setFirstName(childDto.getFirstName());
        child.setLastName(childDto.getLastName());
        child.setBirthdate(childDto.getBirthdate());
        child.setGender(childDto.getGender());
        child.setStatus(childDto.getStatus());
//        child.setParent(childDto.getParent());
        child.setChildRegistration(childDto.getChildRegistration());
//        child.setActivitiesRegistrations(childDto.getActivitiesRegistrations());
//        child.setPreRegistration(childDto.getPreRegistration());
//        child.setNewsRegistrationList(childDto.getNewsRegistrationList());
        child.setSection(childDto.getSection());

        return child;
    }





    public ChildDto GetChildById(Integer Id){
        ChildDto child= ToDTO(childRepository.findById(Id).orElse(null));
        return child;
    }
    public List<ChildDto> fintByParent(Integer Id){

        List<ChildDto> child= childRepository.findByParentId(Id).stream().map(c->ToDTO(c)).collect(Collectors.toList());
        return child;
    }

    public List<ChildDto> GetAllChildren(){
        List<ChildDto> childDtoList=childRepository.findAll().stream().map(
                ch->ToDTO(ch)).collect(Collectors.toList());
return childDtoList;

    }



    public void DeleteChild(Integer id){
     childRepository.deleteById(id);
    }



public void AddChild(ChildDto child){
        childRepository.save(ToEntity(child));

}







}
