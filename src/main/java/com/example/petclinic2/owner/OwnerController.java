package com.example.petclinic2.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.Map;

@Controller
public class OwnerController {

//  @GetMapping("/owners/find")은  @RequestMapping(value="/owners/find", method=RequestMethod.GET) 의 축약형 표현이다.
//    @GetMapping("/owners/find")
//    public String initFindForm(){
//        return "owners/findOwners";
//    }

//    public OwnerController() {
//        System.out.println("[OwnerController created]");
//    }

    private Connection conn;
    private DriverManager driverManager;
    private Statement stmt;



    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", new Owner());
//        System.out.println("[TEST @GetMapping]");
        return "owners/findOwners";
    }


    @GetMapping("/owners")
    public String processFindForm(Owner owner) {

        System.out.println("[TEST] owner.getFirstName() = " + owner.getFirstName());
        System.out.println("[TEST] owner.getId() = " + owner.getId());



        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pet_clinic2",
                    "root",
                    "root"
            );

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from owners"
            );

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e){
            System.out.println("[Exception] = " + e);
        }




        return "owners/findOwners";
    }
}
