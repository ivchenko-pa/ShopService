import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void addProduct_productListSizeShouldBe1_whenAdded1() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product("1", "Milk");
        //When
        int actual = productRepo.addProduct(product);
        //Then
        assertEquals(1, actual);
    }

    @Test
    void addProduct_productLSizeShouldBe2_whenAdded2() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "A");
        Product productB = new Product("2", "B");
        //When
        productRepo.addProduct(productA);
        int actual = productRepo.addProduct(productB);

        //Then
        assertEquals(2, actual);
    }

    @Test
    void addProduct_productLSizeShouldBe1_whenAddedDuplicate() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "A");
        Product productB = new Product("1", "A");
        //When
        productRepo.addProduct(productA);
        int actual = productRepo.addProduct(productB);

        //Then
        assertEquals(1, actual);
    }

    @Test
    void removeProduct_listSizeShouldBe0_whenRemoved1From1() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product("1", "Milk");
        productRepo.addProduct(product);
        //When
        int actual = productRepo.removeProduct(product.id());
        //Then
        assertEquals(0, actual);
    }

    @Test
    void removeProduct_listSizeShouldBe1_whenRemoved1From2() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "A");
        Product productB = new Product("2", "B");
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        //When
        int actual = productRepo.removeProduct(productB.id());
        //Then
        assertEquals(1, actual);
    }

    @Test
    void removeProduct_listSizeShouldBe1_whenRemoved1From2SecondTime() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "A");
        Product productB = new Product("2", "B");
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        //When
        productRepo.removeProduct(productB.id());
        int actual = productRepo.removeProduct(productB.id());
        //Then
        assertEquals(1, actual);
    }

    @Test
    void retrieveProduct_shouldBeMilk_whenAskedMilkAndOnlyMilkIsInList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "Milk");
        productRepo.addProduct(productA);
        //When
        Product actualProduct = productRepo.retrieveProduct("1");
        //Then
        assertEquals("Milk", actualProduct.name());
    }

    @Test
    void retrieveProduct_shouldBeMilk_whenAskedMilkAndManyIncludingMilkAreInList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "Apple");
        Product productB = new Product("2", "Milk");
        Product productC = new Product("3", "Banana");
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        productRepo.addProduct(productC);
        //When
        Product actualProduct = productRepo.retrieveProduct("2");
        //Then
        assertEquals("Milk", actualProduct.name());
    }

    @Test
    void retrieveProduct_shouldBeNull_whenAskedMilkAndNoMilkInList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "Apple");
        Product productC = new Product("3", "Banana");
        productRepo.addProduct(productA);
        productRepo.addProduct(productC);
        //When
        Product actualProduct = productRepo.retrieveProduct("2");
        //Then
        assertNull(actualProduct);
    }

    @Test
    void retrieveProduct_shouldBeNull_whenAskedMilkAndListIsEmpty() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        //When
        Product actualProduct = productRepo.retrieveProduct("2");
        //Then
        assertNull(actualProduct);
    }

    @Test
    void retrieveAllProducts_shouldBe1_when1inList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product product = new Product("1", "Milk");
        productRepo.addProduct(product);
        //When
        Set<Product> products = productRepo.retrieveAllProducts();
        //Then
        assertEquals(1, products.size());
    }

    @Test
    void retrieveAllProducts_shouldBe2_when2inList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        Product productA = new Product("1", "Apple");
        Product productB = new Product("2", "Milk");
        productRepo.addProduct(productA);
        productRepo.addProduct(productB);
        //When
        Set<Product> products = productRepo.retrieveAllProducts();
        //Then
        assertEquals(2, products.size());
    }

    @Test
    void retrieveAllProducts_shouldBe0_when0inList() {
        //Given
        ProductRepo productRepo = new ProductRepo();
        //When
        Set<Product> products = productRepo.retrieveAllProducts();
        //Then
        assertEquals(0, products.size());
    }
}