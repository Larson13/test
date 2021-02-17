package cn.com.perf.beihe.pinter.mode;

public class Sku {
    private Integer skuId;

    private String skuName;

    private String price;

    private Integer stock;

    private String brand;

    public Integer getSkuId() {
        return this.skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSkuName() {
        return this.skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String toString() {
        return "Sku{skuId=" + this.skuId + ", price='" + this.price + '\'' + ", stock=" + this.stock + ", brand='" + this.brand + '\'' + '}';
    }
}
