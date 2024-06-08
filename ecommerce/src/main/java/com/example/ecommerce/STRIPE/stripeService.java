package com.example.ecommerce.STRIPE;

import com.example.ecommerce.AWS.AWSIMAGEUPLOAD;
import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class stripeService {

//    private stripePaymentIntentModel stripePaymentIntentModel;


    String ID;
//    StripeClient client = new StripeClient("sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM");
    public String CreateAccountStripe(String Email) throws StripeException {
        Stripe.apiKey = "sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM";

        AccountCreateParams params =
                AccountCreateParams.builder()
                        .setType(AccountCreateParams.Type.CUSTOM)
                        .setCountry("CA")
                        .setEmail(Email)
                        .setCapabilities(
                                AccountCreateParams.Capabilities.builder()
                                        .setCardPayments(
                                                AccountCreateParams.Capabilities.CardPayments.builder()
                                                        .setRequested(true)
                                                        .build()
                                        )
                                        .setTransfers(
                                                AccountCreateParams.Capabilities.Transfers.builder()
                                                        .setRequested(true)
                                                        .build()
                                        )
                                        .build()
                        )

                        .build();
        Account account = Account.create(params);
        return account.getId();
    }

    public String CrateAccountLink(String ID) throws StripeException {
        Stripe.apiKey = "sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM";
        AccountLinkCreateParams params =
                AccountLinkCreateParams.builder()
                        .setAccount(ID)
                        .setRefreshUrl("https://google.com")
                        .setReturnUrl("https://youtube.com")
                        .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                        .build();
        AccountLink accountLink = AccountLink.create(params);

        return accountLink.getUrl();
    }


    public stripePaymentIntentModel MakePaymentIntent(Integer amount, String vendorpaymentid, String userstripeID) throws StripeException {
        stripePaymentIntentModel stripePaymentIntentModel = new stripePaymentIntentModel();
        Stripe.apiKey = "sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM";

        EphemeralKeyCreateParams ephemeralKeyParams =
                EphemeralKeyCreateParams.builder()
                        .setStripeVersion("2023-10-16")
                        .setCustomer(userstripeID)
                        .build();

        EphemeralKey ephemeralKey = EphemeralKey.create(ephemeralKeyParams);

        PaymentIntentCreateParams paymentIntentParams =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount.longValue()*100)
                        .setCurrency("CAD")
                        // In the latest version of the API, specifying the `automatic_payment_methods` parameter
                        // is optional because Stripe enables its functionality by default.
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .setApplicationFeeAmount(500L)
                        .setTransferData(
                                PaymentIntentCreateParams.TransferData.builder()
                                        .setDestination(vendorpaymentid)
                                        .build())
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentParams);
//        return  "Payment Intent - " + paymentIntent.getClientSecret() + " Emperical Key - " + ephemeralKey.getSecret();
        String paymentintentsecrect = paymentIntent.getClientSecret();
        String empericalkeysecrect = ephemeralKey.getSecret();
        stripePaymentIntentModel.setPaymentIntent(paymentintentsecrect);
        stripePaymentIntentModel.setEmpericalKey(empericalkeysecrect);
        return stripePaymentIntentModel;
    }


    public void serviceAgreement(String stripeid) throws StripeException {
//        Stripe.apiKey = "sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM";
        StripeClient client = new StripeClient("sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM");
        AccountUpdateParams params =
                AccountUpdateParams.builder()
                        .setTosAcceptance(
                                AccountUpdateParams.TosAcceptance.builder()
                                        .setDate(1609798905L)
                                        .setIp("8.8.8.8")
                                        .build()
                        )
                        .build();

        Account account = client.accounts().update(stripeid,params);
    }

    public String createCustomer(String customerEmail) throws StripeException {

        Stripe.apiKey = "sk_test_51P05vgP37mf0FjkV91QQs19MVDOgWgArDb2G8gKByBfeW5QrjD1kyh0l3Tkt5H7MxcMroYvUyLP6IoeQtCp9bsAL00aJz5rHqM";

        CustomerCreateParams params =
                CustomerCreateParams.builder()
                        .setName(customerEmail)
                        .setEmail(customerEmail)
                        .build();

        Customer customer = Customer.create(params);

        return customer.getId();
    }


}
