package net.yassine.nurseryapp.web;

import kong.unirest.json.JSONObject;
import net.yassine.nurseryapp.Dto.PreRegisterDto;
import net.yassine.nurseryapp.entities.Section;
import net.yassine.nurseryapp.repositories.SectionRepository;
import net.yassine.nurseryapp.services.PreRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Register")
@CrossOrigin("*")
public class RegistrationController {


private final PreRegisterService preRegisterService;
    public RegistrationController(PreRegisterService preRegisterService) {
        this.preRegisterService = preRegisterService;
    }
@Autowired
private SectionRepository sectionRepository;

    @PostMapping("PreRegisstration")
    public ResponseEntity<String> Preregister(@RequestBody PreRegisterDto preRegisterDto){
return ResponseEntity.ok(preRegisterService.PreRegister(preRegisterDto));

    }



    @PostMapping("validPreRegisetr")
    public ResponseEntity<String> ValidPreRegister(@RequestBody String payload) {

      JSONObject responseBodyJson = new JSONObject(payload);
        JSONObject Data= (JSONObject) responseBodyJson.get("data");
        JSONObject meta= (JSONObject) Data.get("metadata");
        if (meta.has("RegisterId")){
            Integer RegisterId= (Integer) meta.get("RegisterId");
preRegisterService.validatPay(RegisterId);
        }else {
            preRegisterService.ValidPreRegister(payload);
        }
        return ResponseEntity.ok("validateee");

    }

    @GetMapping("section")
    public List<Section> GetSections(){
        return sectionRepository.findAll();
    }



@PostMapping("Regisstration/{id}")
    public String payRegister(@PathVariable Integer id){


  return  preRegisterService.PayRegistration(id);


}
























//    @Value("${myapp.payment.secret}")
//    private String SecrtKey;
//
//    @Value("${myapp.payment.link}")
//    private String Urlpayment;
//    @Autowired
//    private PreRegisterService preRegisterService;

//    @PostMapping("/preRegister")
//    public ResponseEntity<String> preRegister(@RequestBody Family family) {
//
//        try {
//            preRegisterService.preRegister(family.getParent(), family.getChildlList());
//        } catch (Exception ex) {
//            return ResponseEntity.ok(ex.getMessage());
//        }


//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("name", "pre-pay");
//
//        List<Map<String, Object>> items = new ArrayList<>();
//        Map<String, Object> item = new HashMap<>();
//        item.put("price", "01htgtqaabhyd5v7c81y1tczmr");
//        item.put("quantity", 2);
//        item.put("adjustable_quantity", true);
//        items.add(item);
//        requestBody.put("items", items);
//
//        requestBody.put("after_completion_message", "u paid  secuus");
//        requestBody.put("locale", "ar");
//        requestBody.put("pass_fees_to_customer", true);
//        requestBody.put("collect_shipping_address", true);
//        requestBody.put("metadata", new HashMap<>());
//        Map<String, Object> metadata = new HashMap<>();
//        metadata.put("gdh nw7d kyn", "22");
//        requestBody.put("metadata", metadata);
//
//        // Make HTTP POST request
//        HttpResponse<JsonNode> response = Unirest.post("https://pay.chargily.net/test/api/v2/payment-links")
//                .header("Authorization", "Bearer "+ SecrtKey)
//                .header("Content-Type", "application/json")
//                .body(requestBody)
//                .asJson();
//
//        JSONObject responseBodyJson = new JSONObject(response.getBody());
//        Map<String, Object> responseMap = new HashMap<>();
//        responseBodyJson.toMap().forEach((key, value) -> responseMap.put(key, value));
//
//
//
//        return ResponseEntity.ok(family.toString());
//
//    }









}
