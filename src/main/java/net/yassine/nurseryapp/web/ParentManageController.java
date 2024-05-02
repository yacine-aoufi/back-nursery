package net.yassine.nurseryapp.web;


import net.yassine.nurseryapp.Dto.ParentDto;
import net.yassine.nurseryapp.repositories.ParentRepository;
import net.yassine.nurseryapp.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parents")
public class ParentManageController {

    private final ParentService parentService;
    public ParentManageController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/allParents")
    public List<ParentDto> getallparents(){
        List<ParentDto> parentDtoList=parentService.GetAllparents();
        return parentDtoList;
    }

@GetMapping("/parent/{id}")
    public ParentDto getparent(@PathVariable Integer id){
   ParentDto parentDto= parentService.GetParent(id);
    return parentDto;
}

    @PostMapping("/parent/Add")
    public ResponseEntity<String> Addparent(@RequestBody ParentDto parent){
parentService.AddParent(parent);
        return ResponseEntity.ok("Added New Parent successfully");
    }


    @PutMapping("/parent/update")
    public ResponseEntity<String> Updateparent(@RequestBody ParentDto parent){
        parentService.AddParent(parent);
        return ResponseEntity.ok("Update successfully");
    }


@DeleteMapping("/parent/delete/{id}")
    public ResponseEntity<String> DeleteParent(@PathVariable Integer id){
        parentService.DeleteParent(id);
        return ResponseEntity.ok("Deleted parentDtoList");

}

























}
