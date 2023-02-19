package com.example.coco_spring.Controller.AfterSaleService;

import com.example.coco_spring.Entity.AfterSaleServices;
import com.example.coco_spring.Service.AfterSaleServices.ASSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/afs")
public class CocoAFSController {

    ASSService assService ;

    @PostMapping("/addASS")
    public AfterSaleServices add(@RequestBody  AfterSaleServices ass1)  {

        return assService.add(ass1);
    }
    @GetMapping("/get_all_ASS")
    public List<AfterSaleServices> findAll() {

        return assService.findAll();
    }

    @PutMapping("/updateASS")
    public AfterSaleServices update (@RequestBody AfterSaleServices afterSaleServices)  {
        return assService.update(afterSaleServices);
    }

    @DeleteMapping("/deleteASS/{id}")
    public void delete(@PathVariable("id") Long serviceId){

        assService.delete(serviceId);
    }

    @GetMapping("/get_ASS/{idAss}")
    public AfterSaleServices getASS(@PathVariable("idAss") Long idAss) {

        return assService.retrieveItem(idAss);
    }

}
