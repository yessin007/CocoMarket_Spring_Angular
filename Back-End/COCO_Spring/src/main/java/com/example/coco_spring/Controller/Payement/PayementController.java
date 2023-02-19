package com.example.coco_spring.Controller.Payement;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Service.Payement.PayementService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.*;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/payement/")
public class PayementController {

    PayementService payementService;
    @GetMapping("/retrive_all_payements")
    public List<Payement> retrievePayementList(){
        return payementService.findAll();
    }

    @GetMapping("/retrive_payement/{payementId}")
    public Payement retrievePayement(@PathVariable("payementId") Long payementId){
        return payementService.retrieveItem(payementId);
    }

    @PostMapping("/add_payement")
    public Payement addPayement(@RequestBody Payement payement){
        return payementService.add(payement);
    }

    @PutMapping("/update_payement")
    public Payement updatePayement(@RequestBody Payement payement){
        return payementService.update(payement);
    }

    @DeleteMapping("/delete_payement/{payementId}")
    public void deletePayement(@PathVariable("payementId") Long payementId){
        payementService.delete(payementId);
    }
}
