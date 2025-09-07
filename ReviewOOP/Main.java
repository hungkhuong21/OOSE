import java.util.*;

// Lớp Sản phẩm
class Product {
    private String name;
    private double price;
    private String category;
    private int quantity;

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }

    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "San pham: " + name + " | Gia: " + price +
               " | Danh muc: " + category + " | SL: " + quantity;
    }
}

// Lớp quản lý cửa hàng
class Store {
    private List<Product> products = new ArrayList<>();

    public Store() {
        // Thêm sẵn vài sản phẩm mẫu
        products.add(new Product("Laptop", 1500, "Điện tử", 10));
        products.add(new Product("Tai nghe", 200, "Điện tử", 30));
        products.add(new Product("Áo thun", 100, "Thời trang", 50));
        products.add(new Product("Giày", 300, "Thời trang", 20));
        products.add(new Product("Bánh kẹo", 50, "Thực phẩm", 100));
    }

    // 1. Hiển thị tất cả sản phẩm
    public void displayProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    // 2. Cập nhật giá hoặc số lượng sản phẩm
    public void updateProduct(String name, double newPrice, int newQuantity) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setPrice(newPrice);
                p.setQuantity(newQuantity);
                System.out.println("Da cap nhat san pham!");
                return;
            }
        }
        System.out.println("Khong tim thay san pham!");
    }

    // 3. Tìm sản phẩm theo khoảng giá
    public void findProductByPrice(double min, double max) {
        boolean found = false;
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay san pham trong khoang gia nay!");
        }
    }

    // 4. Tìm sản phẩm theo danh mục (không phân biệt hoa/thường)
    public void findProductByCategory(String category) {
        boolean found = false;
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay san pham trong danh muc nay!");
        }
    }

    // 5. Tính tổng giá trị hàng tồn kho cho từng danh mục
    public void totalValueByCategory() {
        Map<String, Double> map = new HashMap<>();
        for (Product p : products) {
            double value = p.getPrice() * p.getQuantity();
            map.put(p.getCategory(), map.getOrDefault(p.getCategory(), 0.0) + value);
        }
        for (String cat : map.keySet()) {
            System.out.println("Danh muc: " + cat + " | Tong gia tri ton kho: " + map.get(cat));
        }
    }

    // 6. Giảm giá cho sản phẩm
    public void discountProduct(String name, double percent) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                double newPrice = p.getPrice() * (1 - percent / 100);
                p.setPrice(newPrice);
                System.out.println("Da giam gia san pham " + name + " con: " + newPrice);
                return;
            }
        }
        System.out.println("Khong tim thay san pham!");
    }

    // 7. Đặt hàng
    public void orderProduct(String name, int amount) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                if (p.getQuantity() >= amount) {
                    double total = p.getPrice() * amount;
                    p.setQuantity(p.getQuantity() - amount);
                    System.out.println("Dat hang thanh cong! Tong tien: " + total);
                } else {
                    System.out.println("Khong du hang trong kho!");
                }
                return;
            }
        }
        System.out.println("Khong tim thay san pham!");
    }
}

// Lớp Main (chạy chương trình)
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();

        while (true) {
            System.out.println("\n===== QUAN LY SAN PHAM =====");
            System.out.println("1. Hien thi tat ca san pham");
            System.out.println("2. Cap nhat san pham");
            System.out.println("3. Tim san pham theo khoang gia");
            System.out.println("4. Tim san pham theo danh muc");
            System.out.println("5. Tinh tong gia tri ton kho theo danh muc");
            System.out.println("6. Giam gia san pham");
            System.out.println("7. Dat hang");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            int choice = sc.nextInt();
            sc.nextLine(); // bỏ Enter

            switch (choice) {
                case 1:
                    store.displayProducts();
                    break;
                case 2:
                    System.out.print("Nhap ten san pham can cap nhat: ");
                    String nameUpdate = sc.nextLine();
                    System.out.print("Nhap gia moi: ");
                    double price = sc.nextDouble();
                    System.out.print("Nhap so luong moi: ");
                    int qty = sc.nextInt();
                    store.updateProduct(nameUpdate, price, qty);
                    break;
                case 3:
                    System.out.print("Nhap gia thap nhat: ");
                    double min = sc.nextDouble();
                    System.out.print("Nhap gia cao nhat: ");
                    double max = sc.nextDouble();
                    store.findProductByPrice(min, max);
                    break;
                case 4:
                    System.out.print("Nhap ten danh muc: ");
                    String category = sc.nextLine();
                    store.findProductByCategory(category);
                    break;
                case 5:
                    store.totalValueByCategory();
                    break;
                case 6:
                    System.out.print("Nhap ten san pham can giam gia: ");
                    String nameDiscount = sc.nextLine();
                    System.out.print("Nhap phan tram giam gia: ");
                    double percent = sc.nextDouble();
                    store.discountProduct(nameDiscount, percent);
                    break;
                case 7:
                    System.out.print("Nhap ten san pham can dat: ");
                    String nameOrder = sc.nextLine();
                    System.out.print("Nhap so luong: ");
                    int amount = sc.nextInt();
                    store.orderProduct(nameOrder, amount);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
