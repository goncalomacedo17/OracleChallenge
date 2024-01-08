package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;

    @Mock
    Basket basket;

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;

    //adicionado por mim

    @Mock
    CheckoutStep checkoutStep0;

    @Mock
    PricingService pricingService;

    @Mock
    PromotionsService promotionsService;

    @BeforeEach
    void setup() {
        pricingService = Mockito.mock(PricingService.class);
        promotionsService = Mockito.mock(PromotionsService.class);
        checkoutPipeline = new CheckoutPipeline(); // unica que nao adicionei;
        basket = new Basket();

        when(pricingService.getPrice("product1")).thenReturn(4.0);
        when(pricingService.getPrice("product2")).thenReturn(2.0);


    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);
        assertEquals(paymentSummary.getRetailTotal(), 0.0);
    }

    @Test
    void executeAllPassedCheckoutSteps() {

        PaymentSummary paymentSummary;

        // Exercise - implement testing passing through all checkout steps
        Promotion p1 = new Promotion("product1", Promotion.PromotionType.p_50_pct);
        //Promotion p2 = new Promotion("product2", Promotion.PromotionType.p_2_for_1);
        Promotion p2 = new Promotion("product2", Promotion.PromotionType.p_2_for_1);

        //System.out.println(p1.getProductCode() + " - " + p1.getPromotionType());

        List<Promotion> promotions = new ArrayList<>();
        promotions.add(p1);
        promotions.add(p2);

        //for(Promotion p : promotions) System.out.println(p.getProductCode() + " - " + p.getPromotionType());

        basket.add("product1", "myproduct1", 10);
        basket.add("product2", "myproduct2", 11);


        when(promotionsService.getPromotions(basket)).thenReturn(promotions);


        System.out.println("Product1 Price: " + pricingService.getPrice("product1"));
        System.out.println("Product2 Price: " + pricingService.getPrice("product2"));
        for(Promotion p : promotionsService.getPromotions(basket)){
            System.out.println("Promotions: " + p.getProductCode() + " - " + p.getPromotionType());

        }

        checkoutStep1 = new PromotionsCheckoutStep(promotionsService);
        checkoutStep2 = new RetailPriceCheckoutStep(pricingService);

        checkoutPipeline.addStep(checkoutStep1);
        checkoutPipeline.addStep(checkoutStep2);
        paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals(paymentSummary.getRetailTotal(),2.0 * 10 + 2.0 * 6);













        /*

        when(pricingService.getPrice("product1")).thenReturn(4.0);
        when(pricingService.getPrice("product2")).thenReturn(2.0);

        //when(promotionsService.getPromotions(basket)).thenReturn(promotions);


        checkoutStep1 = new PromotionsCheckoutStep(promotionsService);

        checkoutStep2 = new RetailPriceCheckoutStep(pricingService);



        //checkoutPipeline.addStep(checkoutStep0);
        checkoutPipeline.addStep(checkoutStep1);
        checkoutPipeline.addStep(checkoutStep2);



        //PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        //Mockito.verify(checkoutStep0).execute(Mockito.any(CheckoutContext.class));
        //Mockito.verify(checkoutStep1).execute(Mockito.any(CheckoutContext.class));
        //Mockito.verify(checkoutStep2).execute(Mockito.any(CheckoutContext.class));

        */

    }

}
