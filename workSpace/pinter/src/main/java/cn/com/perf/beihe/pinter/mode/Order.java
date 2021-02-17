package cn.com.perf.beihe.pinter.mode;

import java.util.Date;

public class Order {

    private Integer id;

    private Integer orderId;

    private Integer payUserId;

    private Float amount;

    private Byte orderType;

    private Byte payType;

    private Float discount;

    private Date createTime;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPayUserId() {
        return this.payUserId;
    }

    public void setPayUserId(Integer payUserId) {
        this.payUserId = payUserId;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Byte getOrderType() {
        return this.orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public Byte getPayType() {
        return this.payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Float getDiscount() {
        return this.discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "Order{id=" + this.id + ", orderId=" + this.orderId + ", payUserId=" + this.payUserId + ", amount=" + this.amount + ", orderType=" + this.orderType + ", payType=" + this.payType + ", discount=" + this.discount + ", createTime=" + this.createTime + '}';
    }
}
