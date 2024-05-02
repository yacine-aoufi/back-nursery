package net.yassine.nurseryapp.services;


import net.yassine.nurseryapp.Dto.ChildDto;
import net.yassine.nurseryapp.Dto.ParentDto;
import net.yassine.nurseryapp.entities.Parent;
import net.yassine.nurseryapp.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentService {
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ChildService childService;

    // Method to convert Parent entity to ParentDto
    private static ParentDto fromEntity(Parent parent) {

        if (parent==null){
            return null;
        }

        return new ParentDto(
                parent.getId(),
                parent.getFirstName(),
                parent.getLastName(),
                parent.getBirthdate(),
                parent.getGender(),
                parent.getPhoneNumber(),
                parent.getAddress(),
                parent.getEmail(),
                parent.getPassword(),
                parent.getUsername()
                ,
                parent.getRoles(),
                parent.getStatus(),
                parent.getChildren(),
                parent.getChildRegistration(),
                parent.getPreRegistrations()
        );
    }
    private static Parent toEntity(ParentDto parentDto) {
        Parent parent = new Parent();
        parent.setId(parentDto.getId());
        parent.setFirstName(parentDto.getFirstName());
        parent.setLastName(parentDto.getLastName());
        parent.setBirthdate(parentDto.getBirthdate());
        parent.setGender(parentDto.getGender());
        parent.setPhoneNumber(parentDto.getPhoneNumber());
        parent.setAddress(parentDto.getAddress());
        parent.setEmail(parentDto.getEmail());
        parent.setPassword(parentDto.getPassword());
        parent.setUsername(parentDto.getUsername());
        parent.setRoles(parentDto.getRoles());
        parent.setStatus(parentDto.getStatus());
        parent.setChildren(parentDto.getChildren());
        parent.setChildRegistration(parentDto.getChildRegistrations());
        parent.setPreRegistrations(parentDto.getPreRegistrations());
        return parent;
    }





public ParentDto GetParent(Integer id){
        ParentDto parentDto=fromEntity(parentRepository.findById(id).orElse(null));
        return parentDto;
}


public List<ParentDto>  GetAllparents(){
        List<ParentDto> parentList=parentRepository.findAll().stream().map(
                ParentService::fromEntity).collect(Collectors.toList());

return parentList;
    }


    public void DeleteParent(Integer id){
parentRepository.deleteById(id);

    }

    //save and update
public void AddParent(ParentDto parent){
        parentRepository.save(toEntity(parent));

}

public void AddChildToParent(Integer idParent,ChildDto child){
        List<ChildDto> childDtoList=new ArrayList<>();
        childDtoList.add(child);
     Parent parent= parentRepository.findById(idParent).orElse(null);
        parent.setChildren(childDtoList.stream().map(ChildService::ToEntity).collect(Collectors.toList()));
        parentRepository.save(parent);
}


























}
