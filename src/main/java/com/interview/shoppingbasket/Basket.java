package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }


    /*
        Implement the consolidateItems method from the Basket, so that there is no more than
    one BasketItem with the same productCode within it, and the quantity of that item
    is set to the sum of the quantities for all items with that same productCode in the original basket
    */
    public void consolidateItems() {
        Map<String,BasketItem> consolidatedItems = new HashMap<>();
        String productName;
        int productQuantity;
        String productCode;
        for(BasketItem basketItem : items){
            productCode = basketItem.getProductCode();
            if (consolidatedItems.containsKey(productCode)) {
                BasketItem consolidatedItem = consolidatedItems.get(productCode);
                consolidatedItem.setQuantity(consolidatedItem.getQuantity()+ basketItem.getQuantity());
            }
            else {
                productName = basketItem.getProductName();
                productQuantity= basketItem.getQuantity();
                consolidatedItems.put(productCode,new BasketItem(productCode,productName,productQuantity));
            }


        }
        items = new ArrayList<>(consolidatedItems.values());
    }
}
