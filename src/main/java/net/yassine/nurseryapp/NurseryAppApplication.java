package net.yassine.nurseryapp;

import lombok.Builder;
import net.yassine.nurseryapp.entities.*;
import net.yassine.nurseryapp.entities.enums.Gender;
import net.yassine.nurseryapp.entities.enums.Status;
import net.yassine.nurseryapp.repositories.*;
import net.yassine.nurseryapp.services.ParentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NurseryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NurseryAppApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ChildRepository childRepository, ParentRepository parentRepository, RoleRepository roleRepository, PasswordEncoder encoder, SectionRepository sectionRepository, RegistrationRepository registrationRepository) {

        return Args -> {
            Section section= Section.builder()
                    .Id(null).sectionName("Infants")
                    .Ages("3 month- 1 Year")
                    .SectionPlaces(50)
                    .build();
            Section section1= Section.builder()
                    .Id(null).sectionName("mini-Toddlers")
                    .SectionPlaces(50)
                    .Ages("1 Year- 2 Years")
                    .build();
            Section section2= Section.builder()
                    .Id(null).sectionName("Toddlers")
                    .Ages("2 Year- 3 Years")
                    .SectionPlaces(50)
                    .build();
            Section section3= Section.builder()
                    .Id(null).sectionName("Preschoolers")
                    .Ages("4 Years- 5 Years")
                    .SectionPlaces(50)
                    .build();
            sectionRepository.save(section);
            sectionRepository.save(section1);
            sectionRepository.save(section2);
            sectionRepository.save(section3);





            List<Child> children = new ArrayList<>();
            Role role=roleRepository.save(new Role("PARENT"));
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            Parent parent = new Parent(
                    null, // Id
                    "Yacine", // firstName
                    "aoufi", // lastName
                    new SimpleDateFormat("yyyy-MM-dd")
                            .parse("1999-03-12"), // birthdate
                    Gender.MALE, // gender
                    "123456789", // phoneNumber
                    "123 Main St", // Address
                    "yacine2gmail.com", // email
                    "yacine", // username
                    encoder.encode("yacine12"), // Password
                    roles, // roles
                   null, // Checkout
                    Status.VALID, // status
                  children, // children
                   null, // childRegistration
                   null // preRegistrations
            );
            Child child = Child.builder()
                    .firstName("houyam")
                    .lastName("aoufi").section(section2)
                    .Birthdate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-20"))
                    .gender(Gender.FEMALE)
                    .status(Status.NOT_VALID).parent(parent)
                    .build();
            children.add(child);
            Child child1 = Child.builder()
                    .firstName("mouad")
                    .lastName("chain").section(section)
                    .Birthdate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-20"))
                    .gender(Gender.MALE)
                    .status(Status.VALID).parent(parent)
                    .build();
            children.add(child1);


            ChildRegistration childRegistration= ChildRegistration.builder()
                    .id(null)
                    .isPayed(false)
                    .lastPaymentDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-04-1"))
                    .child(child)
                    .parent(parent)
                    .build();

            ChildRegistration childRegistration1= ChildRegistration.builder()
                    .id(null)
                    .isPayed(true)
                    .lastPaymentDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-04-30"))
                    .child(child1)
                    .parent(parent)
                    .build();

            parentRepository.save(parent);
            childRepository.save(child);
            childRepository.save(child1);

registrationRepository.save(childRegistration);
            registrationRepository.save(childRegistration1);







        };
//    @Bean
//    CommandLineRunner commandLineRunner(ParentRepository parentRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
//
//        return Args -> {
//
//            Role role=roleRepository.save(new Role("ADMIN"));
//            List<Role> roles=new ArrayList<>();
//            roles.add(role);
//                        Parent parent = new Parent(null, "yacine", "aoufi",
//                    new SimpleDateFormat("yyyy-MM-dd")
//                            .parse("1999-03-12"),
//                    Gender.MALE, "0541373208", "7ey elfadj", null,"admin", encoder.encode("yacine12"),
//                                roles, null, Status.NOT_VALID, null, null, null);
//
//
//                        parentRepository.save(parent);
//        };
//
    }
}
