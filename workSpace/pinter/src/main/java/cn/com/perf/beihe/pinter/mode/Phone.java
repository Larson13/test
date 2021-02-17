package cn.com.perf.beihe.pinter.mode;

public class Phone {
    private Integer id;

    private String systemVersion;

    private String brand;

    private static final String[] brands = new String[] { "Apple", "Huawei", "XiaoMi", "OPPO", "Vivo" };

    private String color;

    private static final String[] colors = new String[] { "black", "red", "pink", "blue", "yellow" };

    private String memorySize;

    private static final String[] memorySizes = new String[] { "32G", "64G", "128G", "512G" };

    private String cpuCore;

    private String price;

    private String desc;

    private String operator;

    private String screenSize;

    private String batterySize;

    private String phoneSize;

    private String camera;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMemorySize() {
        return this.memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getCpuCore() {
        return this.cpuCore;
    }

    public void setCpuCore(String cpuCore) {
        this.cpuCore = cpuCore;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getBatterySize() {
        return this.batterySize;
    }

    public void setBatterySize(String batterySize) {
        this.batterySize = batterySize;
    }

    public String getPhoneSize() {
        return this.phoneSize;
    }

    public void setPhoneSize(String phoneSize) {
        this.phoneSize = phoneSize;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setRandomBrand() {
        int index = (int)(Math.random() * brands.length);
        this.brand = brands[index];
    }

    public void setRandomColor() {
        int index = (int)(Math.random() * colors.length);
        this.color = colors[index];
    }

    public void setRandomMemorySize() {
        int index = (int)(Math.random() * memorySizes.length);
        this.memorySize = memorySizes[index];
    }
}
