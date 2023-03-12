package com.example.coco_spring.Controller.Product;



import com.example.coco_spring.Controller.Chatgbt.CompletionRequest;
import com.example.coco_spring.Controller.Chatgbt.CompletionResponse;
import com.example.coco_spring.Controller.Chatgbt.OpenAiApiClient;
import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;

import com.example.coco_spring.Service.Product.ProductServices;
import com.example.coco_spring.Service.Store.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    ProductServices productServices;
    ProductRepository productRepository;
    StoreService storeService;
    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;

    @GetMapping("/findmatchingaiproducts/{req}")
    public ResponseEntity<List<Product>> chatWithGpt3(@PathVariable("req") String message) throws Exception {
        var completion = CompletionRequest.defaultWith("give me a list products which description is"+message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiApiClient.OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        List<Product> products=productRepository.findAll();
        var result = completionResponse.firstAnswer().trim();
        List<String> resultList = Arrays.asList(result.split("\n"));
        resultList.replaceAll(s -> s.replaceAll("^\\d+\\.\\s*", ""));
        List<Product> resultRes =new ArrayList<>();
        for (String l:resultList){
            for (Product product:products){
                if(l.contains(product.getProductName())){
                    resultRes.add(product);
                }
            }
        }
        return ResponseEntity.ok(resultRes);
    }
    @PostMapping(value = "/addandupdateproduct" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestParam("image") MultipartFile image, @RequestParam("productName") String poductName,
                              @RequestParam("reference") String reference,@RequestParam("description") String description,
                              @RequestParam("quantity") Long quantity,@RequestParam("model") MultipartFile model,
                              @RequestParam("video") MultipartFile video,@RequestParam("price") float price,
                              @RequestParam("date")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfProduct,
                                @RequestParam("discount") float discount,@RequestParam("brand") String brand,
                              @RequestParam("yearsOfWarranty") int yearsOfWarranty,@RequestParam("productCategory") ProductCategory productCategory) throws IOException {

        Product product = new Product();
        product.setProductName(poductName);
        product.setBrand(brand);
        product.setImages(image.getBytes());
        product.setReference(reference);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setModel(model.getBytes());
        product.setVideo(video.getBytes());
        product.setPrice(price);
        product.setDateOfProduct(dateOfProduct);
        product.setDiscount(discount);
        product.setYearsOfWarranty(yearsOfWarranty);
        product.setProductCategory(productCategory);
        productRepository.save(product);
        return product;
    }
    @GetMapping("/retriveproduct/{id}")
    public Product retrieveProduct (@PathVariable("id") Long productId){
        return productServices.retrieveProduct(productId);
    }
    @GetMapping("/retrieveallproducts")
    public List<Product> retrieveAllProducts(){
        return  productServices.retrieveAllProducts();
    }
    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productServices.deleteProduct(id);
    }



    @GetMapping(value = "/compare",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String,String> comparePrices(@RequestParam("product") String productName) {
        List<Product> productsByPrice = new ArrayList<>();
        List<Product> productsByName = productRepository.findByProductName(productName);
        Map<String,String> productByStore=new HashMap<>();

        Collections.sort(productsByName, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        for(Product product:productsByName){
            productByStore.put(storeService.getStoreByProductId(product.getProductId()).getStoreName(), product.getProductName());

        }
        return productByStore;
    }
    @GetMapping("/getstore/{pid}")
    public Store getStoreByProductId(@PathVariable("pid") Long productId){
        return storeService.getStoreByProductId(productId);
    }

    @GetMapping("/insuranceprice/{idprod}")
    public double calculateProductInsurance(@RequestParam("idprod") Long productId) {
        return productServices.calculateProductInsurance(productId);
    }
    
}
