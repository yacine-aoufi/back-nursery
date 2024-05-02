
package net.yassine.nurseryapp.web;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.yassine.nurseryapp.entities.Child;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import net.yassine.nurseryapp.Dto.ChildDto;
import net.yassine.nurseryapp.services.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Children")
public class ChildManageController {
    private final ChildService childService;

    public ChildManageController(ChildService childService) {
        this.childService = childService;
    }


    @GetMapping("child/{id}")
    public ResponseEntity<ChildDto> GetChild(@PathVariable Integer id){
    ChildDto child=childService.GetChildById(id);
return ResponseEntity.ok(child) ;
}

    @GetMapping("all")
    public ResponseEntity<List<ChildDto>> GetChildren(){
        List<ChildDto> children=childService.GetAllChildren();
        return ResponseEntity.ok(children) ;
    }

    @DeleteMapping("child/delete/{id}")
    public ResponseEntity<String> DeleteChild(@PathVariable Integer id){
        childService.DeleteChild(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("child/Add")
    public ResponseEntity<String> AddChild(@RequestBody ChildDto child){
        childService.AddChild(child);
        return ResponseEntity.ok("Added new child successfully") ;
    }

    @PutMapping("child/update")
    public ResponseEntity<String> UpdateChild(@RequestBody ChildDto child){
        childService.AddChild(child);
        return ResponseEntity.ok("Updated the child successfully") ;
    }


    @GetMapping("qrcode/{id}")
    public ResponseEntity<ChildDto> generateQRCode(@PathVariable Integer id) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ChildDto child=childService.GetChildById(1);
        Map<String,String> childd=new HashMap<>();
        childd.put("id",child.getId().toString());
        childd.put("firstName",child.getFirstName());
        childd.put("LastName",child.getLastName());
        BitMatrix bitMatrix = qrCodeWriter.encode(childd.toString(), BarcodeFormat.QR_CODE, 350, 350);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setContentLength(byteArrayOutputStream.size());
        child.setQrcode(byteArrayOutputStream.toByteArray());
        return new ResponseEntity<>(child, HttpStatus.OK);
    }



    @GetMapping("myChild/{id}")
    public List<ChildDto> get(@PathVariable Integer id){
        return childService.fintByParent(id);
    }












}
