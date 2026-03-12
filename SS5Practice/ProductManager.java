package SS5Practice;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    public void addProduct(Product p) throws exception {
        boolean exists = products.stream().anyMatch(product -> product.getId() == p.getId());
        if(exists){
            throw new exception("ID đã tồn tại");
        }
        products.add(p);
    }
    public void displayProducts() {
        System.out.printf("%s %s %s %s %s\n","ID", "NAME", "PRICE", "QTY", "CATEGORY");

        products.forEach(p ->
                System.out.printf("%d %s %f %d %s\n", p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory()));
    }
    public void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
        System.out.println("Đã xóa các sản phẩm hết hàng");
    }
}
