package com.example.ecommerce.STRIPE;


import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Stripe")
public class stripeController {

    @Autowired
    stripeService stripeService;

    @PostMapping("/create/{email}")
    public String createconnect(@PathVariable String email) throws StripeException {

        return stripeService.CreateAccountStripe(email);
    }

    @PostMapping("/create-account-link/{stripeid}")
    public String createaccountlink(@PathVariable String stripeid) throws StripeException {
        return stripeService.CrateAccountLink(stripeid);
    }

    @PostMapping("/makepayment/{amount}/{vendorpaymentid}/{userstripeID}")
    public stripePaymentIntentModel makepayment(@PathVariable Integer amount, @PathVariable String vendorpaymentid, @PathVariable String userstripeID ) throws StripeException {
        return stripeService.MakePaymentIntent(amount,vendorpaymentid,userstripeID);
    }

    @PostMapping("/update-policy/{vendorid}")
    public void update(@PathVariable String vendorid ) throws StripeException {
        stripeService.serviceAgreement(vendorid);
    }
}
