package net.yassine.nurseryapp.services;


import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import kong.unirest.JsonNode;
import net.yassine.nurseryapp.Dto.PreRegisterDto;
import net.yassine.nurseryapp.entities.*;
import net.yassine.nurseryapp.entities.enums.Status;
import net.yassine.nurseryapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Array;
import java.time.Instant;
import java.util.*;


@Service
public class PreRegisterService {

    @Value("${myapp.payment.secret}")
    private String SecrtKey;

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private PreRegRepository preRegRepository;
    @Autowired
    private SectionRepository sectionRepository;
@Autowired
private RegistrationRepository registrationRepository;

    public String PreRegister(PreRegisterDto preRegisterDto){

        Map<String, Object> requestBody = new HashMap<>();
        // Construct items array
        Map<String, Object> item = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();
        meta.put("name","pre");
        item.put("price", "01hw3yn4r7hape4qv745m1ghvg");
        item.put("quantity", 1);
        requestBody.put("items", new Object[] { item });
        requestBody.put("success_url", "http://localhost:3000/");
        requestBody.put("metadata",meta);

//        // Convert the request body map to JSON string
//        String jsonBody = new Gson().toJson(requestBody);
//        System.out.println(requestBody.toString());
        // Make the API request
        HttpResponse<String> response = Unirest.post("https://pay.chargily.net/test/api/v2/checkouts")
                .header("Authorization", "Bearer "+ SecrtKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .asString();
//        System.out.println(response.getBody());
        Gson gson = new Gson();
        Map<String, Object> jsonResponse = gson.fromJson(response.getBody(), Map.class);

        // Retrieve the checkout_url from the response
        String checkoutUrl = (String) jsonResponse.get("checkout_url");
        String Idcheck = (String) jsonResponse.get("id");

        // Print or use the checkout_url
//        System.out.println("Checkout URL: " + checkoutUrl);
//        System.out.println("id" + jsonResponse.get("id").toString());
        List<Child> children=new ArrayList<>();
Parent parent=new Parent(null,preRegisterDto.getFirstName(), preRegisterDto.getLastName(),null,null, preRegisterDto.getPhoneNumber(),null
        , preRegisterDto.getEmail(),
        null,
        null,null,null,
        Status.NOT_VALID,children,
        null,null);

        Child child= Child.builder().firstName(preRegisterDto.getFirstNameChild())
                .lastName(preRegisterDto.getLastNameChild())
                .gender(preRegisterDto.getGender())
                .Birthdate(preRegisterDto.getBirthdate())
                .parent(parent).status(Status.NOT_VALID).chargilyId(Idcheck)
                .build();
        children.add(child);


        PreRegistration preRegistration= PreRegistration.builder()
                .isPayed(false)
                .child(child)
                .parent(parent)
                .preRegistraionDate(Date.from(Instant.now()))
                .preAmount(2000.00)
                .build();

        Section section=sectionRepository.findById(preRegisterDto.getIdsec()).orElse(null);
//        System.out.println("************************************************"+preRegisterDto.getIdSection());
        assert section != null;
        section.setSectionPlaces(section.getSectionPlaces()-1);

        parentRepository.save(parent);
        childRepository.save(child);
        preRegRepository.save(preRegistration);
return checkoutUrl;
    }




    public void ValidPreRegister(String payload){

        JSONObject responseBodyJson = new JSONObject(payload);

       String Data= responseBodyJson.get("data").toString();
        JSONObject CheckId = new JSONObject(Data);

        Child chil1= childRepository.findChildByChargilyId(CheckId.get("id").toString());
        if (chil1!=null) {
            chil1.getPreRegistration().setIsPayed(true);
      chil1.setStatus(Status.VALID);
      childRepository.save(chil1);
        }else {

            System.out.println("not payed");
        }
//        Map<String, Object> responseMap = new HashMap<>();
//        responseBodyJson.toMap().forEach((key, value) -> responseMap.put(key, value));
//        responseMap.forEach( (key,value)->System.out.println(key +" "+ value));


//
//
//
//
//
//        JSONObject Data= (JSONObject) responseBodyJson.get("data");
//        System.out.println(payload);
//


    }


public String PayRegistration(Integer id){
   ChildRegistration childRegistration= registrationRepository.findById(id).orElse(null);

Map<String,String> post1=new HashMap<>();
post1.put("name","PayChild");
    HttpResponse<JsonNode> response1 = Unirest.post("https://pay.chargily.net/test/api/v2/products")
            .header("Authorization", "Bearer "+SecrtKey)
            .header("Content-Type", "application/json")
            .body(post1).asJson();
  String pid=response1.getBody().getObject().get("id").toString();

    Map<String,String> post2=new HashMap<>();
    post2.put("product_id",pid);
    post2.put("currency","dzd");
    post2.put("amount","11000");

    HttpResponse<JsonNode> response2 = Unirest.post("https://pay.chargily.net/test/api/v2/prices")
            .header("Authorization", "Bearer "+SecrtKey)
            .header("Content-Type", "application/json")
            .body(post2)
            .asJson();

    String ppid=response2.getBody().getObject().get("id").toString();

    Map<String, Object> post3 = new HashMap<>();
    Map<String, Object> items = new HashMap<>();
    Map<String, Object> metadata = new HashMap<>();
    items.put("price", ppid);
    items.put("quantity", 1);
    post3.put("items", new Object[] { items });
    metadata.put("RegisterId",childRegistration.getId());
    post3.put("metadata",metadata);
    post3.put("success_url", "http://localhost:3000/");
    HttpResponse<JsonNode> response3 = Unirest.post("https://pay.chargily.net/test/api/v2/checkouts")
            .header("Authorization", "Bearer "+SecrtKey)
            .header("Content-Type", "application/json")
            .body(post3)
            .asJson();

return response3.getBody().getObject().get("checkout_url").toString();

}

    public void validatPay( Integer IdRegister){


  ChildRegistration childRegistration= registrationRepository.findById(IdRegister).orElse(null);
childRegistration.setPayed(true);
childRegistration.setLastPaymentDate(Date.from(Instant.now()));
        registrationRepository.save(childRegistration);

    }




}
