/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PtIngHome
 */
public class purchases {
    //ariables
    private int id;
    private int code;
    private String productName;
    private double purchaseAmount;
    private double purchasePrice;
    private double purchaseSubtotal;
    private double total;
    private String created;
    private String supplierNameProduct;
    private String purcharser;

    
    public purchases() {
    }

    public purchases(int id, int code, String productName, double purchaseAmount, double purchasePrice, double purchaseSubtotal, double total, String created, String supplierNameProduct, String purcharser) {
        this.id = id;
        this.code = code;
        this.productName = productName;
        this.purchaseAmount = purchaseAmount;
        this.purchasePrice = purchasePrice;
        this.purchaseSubtotal = purchaseSubtotal;
        this.total = total;
        this.created = created;
        this.supplierNameProduct = supplierNameProduct;
        this.purcharser = purcharser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getPurchaseSubtotal() {
        return purchaseSubtotal;
    }

    public void setPurchaseSubtotal(double purchaseSubtotal) {
        this.purchaseSubtotal = purchaseSubtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSupplierNameProduct() {
        return supplierNameProduct;
    }

    public void setSupplierNameProduct(String supplierNameProduct) {
        this.supplierNameProduct = supplierNameProduct;
    }

    public String getPurcharser() {
        return purcharser;
    }

    public void setPurcharser(String purcharser) {
        this.purcharser = purcharser;
    }
    
    
}
