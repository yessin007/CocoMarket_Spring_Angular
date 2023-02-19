package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Service.AfterSaleServices.ASSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CocoAFSController {

ASSService assService ;
    @PostMapping("/addASS")
    public AfterSaleServices add(@RequestBody  AfterSaleServices ass1)  {
        AfterSaleServices afterSaleServices1 =assService.add(ass1);
        return afterSaleServices1;
    }
    @GetMapping("/getASS")
    public List<AfterSaleServices> findAll() {
        return assService.findAll();
    }
    @PutMapping("/tt")
    public AfterSaleServices update (@RequestBody AfterSaleServices afterSaleServices)  {
        return assService.update(afterSaleServices);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long serviceId){
        assService.delete(serviceId);
    }
}
