package com.interview.shoppingbasket;
import java.util.List;
public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(){}
    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }




    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        //basket.consolidateItems();
        retailTotal = 0.0;
        List<Promotion> promotions = checkoutContext.getPromotions();

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode()); //preço por produto BasketItem
            price = price * basketItem.getQuantity();
            for(Promotion p: promotions){
                if(p.getProductCode().equals(basketItem.getProductCode())){

                    price = applyPromotion(p,basketItem,price);


                }


            }


            //basketItem.setProductRetailPrice(price);
            retailTotal += price ;



        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }



    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        double productRetailPrice;

        switch (promotion.getPromotionType()){
            case p_50_pct:
                productRetailPrice = price * 0.5;
                item.setProductRetailPrice(productRetailPrice);
                return item.getProductRetailPrice()  ;
            case p_10_pct:
                productRetailPrice = price * 0.9;
                item.setProductRetailPrice(productRetailPrice);
                return item.getProductRetailPrice()  ;
            case p_2_for_1:
                return apply2For1(item,price);
            default:
                return price ;
        }

    }

    private double apply2For1(BasketItem item,double price){

        int quantity = item.getQuantity();
        double precoT = item.getQuantity() / price;
        int quantidadeImpar;
        if(quantity%2 == 0) return 0.5 * price ;
        else{
            quantidadeImpar = quantity/2 + quantity%2;
            return quantidadeImpar * precoT;
        }

    }
}
