package com.example.ecommerce.STRIPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class stripePaymentIntentModel {

    private String PaymentIntent;
    private String EmpericalKey;
}
