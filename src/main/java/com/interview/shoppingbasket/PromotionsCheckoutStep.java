package com.interview.shoppingbasket;
import java.util.List;
public class PromotionsCheckoutStep implements CheckoutStep{
    private PromotionsService promotionsService;
    private List<Promotion> promotions;

    public PromotionsCheckoutStep() {}
    public PromotionsCheckoutStep(PromotionsService promoService){
        this.promotionsService = promoService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext){
        Basket basket = checkoutContext.getBasket();
        promotions = promotionsService.getPromotions(basket);
        checkoutContext.setPromotions(promotions);

    }
}
