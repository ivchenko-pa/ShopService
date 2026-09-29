import java.util.*;

public class ProductRepo {
    private Set<Product> allProducts = new HashSet<>();

    public Set<Product> getAllProducts() {
        return allProducts;
    }

    public int addProduct(Product product){
        allProducts.add(product);
        return allProducts.size();
    }

    public Product retrieveProduct(String id){
        if (Objects.isNull(id) || id.isEmpty()) return null;
        for(Product product: allProducts){
            if (product.id().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Set<Product> retrieveAllProducts(){
        return allProducts;
    }

    public int removeProduct(String id){
        allProducts.removeIf((product) -> product.id().equals(id));
        return allProducts.size();
    }
}
