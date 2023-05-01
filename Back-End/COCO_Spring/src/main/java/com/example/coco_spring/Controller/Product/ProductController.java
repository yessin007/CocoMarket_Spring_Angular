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
import org.springframework.data.repository.query.Param;
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
@CrossOrigin("*")
public class ProductController {

    ProductServices productServices;
    ProductRepository productRepository;
    public List<String> collection = new ArrayList<>();
    StoreService storeService;
    @Autowired
    private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;

    @GetMapping("/findmatchingaiproducts/{input}")
    public ResponseEntity<List<Product>> chatWithGpt3(@PathVariable("input") String message) throws Exception {
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
                if(l.contains(product.getTitle())){
                    resultRes.add(product);
                }
            }
        }
        return ResponseEntity.ok(resultRes);
    }
    @GetMapping("/gbt3/{input}")
    public ResponseEntity<List<String>> chatWithGpt3NoProduct(@PathVariable("input") String message) throws Exception {
        var completion = CompletionRequest.defaultWith("give me a list products which description is"+message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiApiClient.OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);

        var result = completionResponse.firstAnswer().trim();
        List<String> resultList = Arrays.asList(result.split("\n"));
        resultList.replaceAll(s -> s.replaceAll("^\\d+\\.\\s*", ""));
        return ResponseEntity.ok(resultList);
    }

    @PostMapping(value = "/addproduct",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Product addProduct(@RequestPart("product") Product product, @RequestPart("imageFile") MultipartFile [] files){
        //return productRepository.save(product);
        try {
            Set<ProductImages> productImagesSet=uploadImages(files);
            product.setImage(productImagesSet);
            product.setQuantity(product.getStock());
            return productRepository.save(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Set<ProductImages> uploadImages(MultipartFile[] files) throws IOException {
        Set<ProductImages> productImagesSet=new HashSet<>();
        for (MultipartFile file:files){
            ProductImages productImage = new ProductImages(file.getOriginalFilename(), file.getContentType(),file.getBytes());
            productImagesSet.add(productImage);
        }
        return productImagesSet;
    }
    @GetMapping("/getallproducts")
    List<Product> getAllProducts(@RequestParam(defaultValue = "") String searchKey) {
        return productServices.getAllProducts(searchKey);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public  void deleteProduct(@PathVariable("productId") Long prodId){
        Product product=productRepository.findById(prodId).get();
        productRepository.delete(product);
    }
    @GetMapping("/getproductdetails/{productId}")
    public Product getProductDetailsById(@PathVariable("productId")Long productId){
        return productRepository.findById(productId).get();
    }
     @GetMapping("/retriveproduct/{id}")
    public Product retrieveProduct (@PathVariable("id") Long productId){
        return productServices.retrieveProduct(productId);
    }

    @GetMapping("/compare/{product}")
    public Map<String,Product> comparePrices(@PathVariable("product") String productName) {
        List<Product> productsByName = productRepository.findByTitle(productName);
        productsByName.sort(Comparator.comparing(Product::getPrice));
        Map<String,Product> productByStore=new HashMap<>();



        for(Product product:productsByName){
            productByStore.put(storeService.getStoreByProductId(product.getProductId()).getStoreName(), product);

        }
        return productByStore;
    }
    @GetMapping("/getstore/{pid}")
    public Store getStoreByProductId(@PathVariable("pid") Long productId){
        return storeService.getStoreByProductId(productId);
    }

    @GetMapping("/insuranceprice/{idprod}/{iduser}")
    public double calculateProductInsurance(@PathVariable("idprod") Long productId,@PathVariable("iduser") Long idUser) {
        return productServices.calculateProductInsurance(productId,idUser);
    }
    @GetMapping("/gettotalpriceproducts")
    public  double getTotAflPriceProducts(){
        return productServices.productTotalPrice();
    }
    @GetMapping ("/topfivemostlikedproducts")
    public List<Product> top5MostLikedProducts(){
        return productServices.top5MostLikedProducts();
    }
    @GetMapping ("/getnumberoflikes/{productId}")
    public int getNumberOflikes(@PathVariable("productId") Long productId){
        return productServices.numberOfLikes(productId);
    }
    @GetMapping("/getaveragelikesofproduct/{productId}")
    public double getAverageLikesOfProduct(@PathVariable("productId") Long productId){
        return productServices.getAverageRatingByProduct(productId);
    }
    @GetMapping("/verifyifliked/{userId}/{productId}")
    public boolean verifyIfLiked(@PathVariable("productId") Long productId,@PathVariable("userId") Long userId){
        return productServices.verifyIfLiked(userId,productId);
    }
    @GetMapping("/verifyifdisliked/{userId}/{productId}")
    public boolean verifyIfDisiked(@PathVariable("productId") Long productId,@PathVariable("userId") Long userId){
        return productServices.verifyIfDisliked(userId,productId);
    }
}
