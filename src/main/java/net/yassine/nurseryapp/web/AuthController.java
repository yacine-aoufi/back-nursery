package net.yassine.nurseryapp.web;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.yassine.nurseryapp.Dto.LoginDto;
import net.yassine.nurseryapp.Dto.UserDto;
import net.yassine.nurseryapp.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("hello")
public String hello(){
    return "hello world";
}


    @GetMapping("mine")
    public Authentication hello(Authentication authentication){
        return authentication ;
    }


    @PostMapping("login")
    public ResponseEntity<UserDto> Login(@RequestBody LoginDto loginDto){
    return  ResponseEntity.ok(authService.Login(loginDto));
    }





    @GetMapping("qrcode/{text}")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 90, 90);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(byteArrayOutputStream.size());

        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

}
