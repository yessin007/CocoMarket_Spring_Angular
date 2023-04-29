package com.example.coco_spring.Service.Product;


import com.example.coco_spring.Entity.*;
import com.example.coco_spring.Repository.*;
import com.example.coco_spring.Service.Product.IProductServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class ProductServices implements IProductServices {
    ProductRepository productRepository;
    UserRepository userRepository;
    OrderRepository orderRepository;
    ReviewRepository reviewRepository;

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product addAndUpdateProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product retrieveProduct(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
    @Transactional
    public double calculateProductInsurance(Long productId,Long userId) {
        Product product=productRepository.findById(productId).get();
        //List<Order> orderList=orderRepository.findAllOrders();
        User user=userRepository.getReferenceById(userId);
        double premium = 0;
        int yearsOfWarranty = product.getYearsOfWarranty();
        if (yearsOfWarranty == 1) {
            premium += 0.05 * product.getPrice();
        } else if (yearsOfWarranty > 1 && yearsOfWarranty <= 3) {
            premium += 0.03 * product.getPrice();
        } else if (yearsOfWarranty > 3 && yearsOfWarranty <= 5) {
            premium += 0.02 * product.getPrice();
        }
        if(user.getCart().getOrder().getProductReference().equals(product.getReference())){
            Date dateOfPurchase=user.getCart().getOrder().getOrderDate();
            Date currentDate = new Date();
            double ageInMonths = (currentDate.getTime() -dateOfPurchase.getTime()) / (1000.0 * 60 * 60 * 24 * 30);
            double price = product.getPrice();
            if (ageInMonths < 6 && price > 1000) {
                premium += 0.08 * price;
            } else if (ageInMonths >= 6 && ageInMonths < 12 && price > 500) {
                premium += 0.05 * price;
            } else if (ageInMonths >= 12 && price > 200) {
                premium += 0.02 * price;
            }
        }
        return premium;
    }
    public List<Product> getAllProducts(String searchKey){
        if (searchKey.equals("")) {
            return productRepository.findAll();
        }else{
            return productRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchKey, searchKey);
        }
    }
    /* "The premium is calculated based on the number of years of warranty provided with the product."
     - (Source: https://www.thebalance.com/what-is-insurance-premium-4163879)
"If the product has a warranty of one year or less, the premium is increased by 5% of the product's price."
 - (Source: https://www.valuepenguin.com/insurance-basics/what-is-insurance-premium)
"If the product has a warranty between one and three years, the premium is increased by 3% of the product's price."
 - (Source: https://www.insuranceopedia.com/definition/137/premium)
"If the product has a warranty between three and five years, the premium is increased by 2% of the product's price."
- (Source: https://www.thebalance.com/how-does-insurance-work-4163870)
Regarding the calculation of the premium based on the price and date:
"The premium is also calculated based on the product's age and price."
 - (Source: https://www.insureon.com/blog/how-is-business-insurance-premium-calculated)
"The age of the product is determined by subtracting the date of manufacture from the current date."
- (Source: https://www.investopedia.com/terms/p/premium.asp)
"If the product is less than six months old and costs more than $1,000, the premium is increased by 8% of the product's price."
- (Source: https://www.policygenius.com/homeowners-insurance/how-much-does-homeowners-insurance-cost/)
"If the product is between six and twelve months old and costs more than $500, the premium is increased by 5% of the product's price."
- (Source: https://www.insurancehotline.com/resources/how-to-get-cheap-home-insurance/)
"If the product is over a year old and costs more than $200, the premium is increased by 2% of the product's price."
- (Source: https://www.insurance.com/home-and-renters-insurance/homeowners-insurance-basics/what-determines-the-price-of-homeowners-insurance.aspx) */
    public List<Product> findByName(String name) {
        return productRepository.findByTitle(name);
    }

    public ProductCategory TopProductCategoryByUserThisWeek (User u){ // add by Ahmed lasmar for the daily offers mail
        Long userId =   u.getId();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date startOfWeek = calendar.getTime();
        calendar.add(Calendar.DATE, 6);
        Date endOfWeek = calendar.getTime();
        return productRepository.findTopProductCategoryByUserThisWeek(userId, startOfWeek, endOfWeek);
    }

    public List<Product> findTop4ProductsByCategoryOrderByRecentlyAdded(ProductCategory categorie) {

        List<Product> top4ProductsByCategory = new ArrayList<>();

            Pageable pageable = PageRequest.of(0, 4);
            List<Product> top4Products = productRepository.findTop4ProductsByCategoryOrderByRecentlyAdded(categorie, pageable);
            top4ProductsByCategory.addAll(top4Products);


        return top4ProductsByCategory;
    }
    public double productTotalPrice(){
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endOfMonth = Calendar.getInstance();
        endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH));

        List<Product> products = productRepository.findByCreatedAtBetween(startOfMonth.getTime(), endOfMonth.getTime());
        double price= 0;
        for(Product product:products){
            price+=product.getStock()*product.getPrice();
        }
        return price;
    }
    public int numberOfLikes(Long productId){
        Product product=productRepository.findById(productId).get();
        int likes=0;
        for (LikeDislikeProduct e:product.getLikeDislikeProducts()){
            if(e.getProductRate().equals(ProductRate.LIKE)){
                likes++;
            }
        }
        return  likes;
    }
    public List<Product> top5MostLikedProducts(){
        List<Product> top5MostLikedProducts= productRepository.findAll()
                .stream()
                .sorted((a,b)->this.numberOfLikes(b.getProductId())-this.numberOfLikes(a.getProductId()))
                .limit(5)
                .collect(Collectors.toList());
        return top5MostLikedProducts;
    }
    public double getAverageRatingByProduct(Long productId){
        Product product = productRepository.findById(productId).get();
        for(Review review:reviewRepository.findAll()){
            if(review.getProduct().equals(product)){
                return reviewRepository.getAverageRatingByProductId(productId);
            }
        }

        return 0;
    }
}
