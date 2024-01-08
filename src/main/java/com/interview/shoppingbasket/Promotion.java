package com.interview.shoppingbasket;

public class Promotion {
    //not yet implemented
    public enum PromotionType{
        p_50_pct,
        p_10_pct,
        p_2_for_1
    }
    String productCode;
    PromotionType promotionType;

    public Promotion(String productCode,PromotionType promotionType){
        this.promotionType = promotionType;
        this.productCode=productCode;

    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }
}
