![PayU Logo](http://static.payu.com/images/mobile/payu_logo.png)

# PayTouch SDK for Android

SDK for Android Developers easing PayU payments platform integration. The SDK provides UI, network communication and security mechanisms to make payment easiest possible. The developer is required to implement only a few simple interfaces to accommodate payment order details.  

**Beware that this SDK works only in Poland**


# Table of Contents

* [Requiremnets](#requirements)
* [Repository](#repository)
* [FAQ](#faq)
* [Changes](#changelog)
* [Features](#quick-tour-over-features)
* [Backend](#backend)
* [Android Pay](#android-pay)

## Requirements

* Android 2.3 or later
* Server-side OAuth2 token retrieval - see full documentation

## Repository

**Maven**
```xml
<repositories>
  <repository>
    <id>payu-mvn-repo</id>
    <url>https://raw.github.com/PayU/paytouch-android/mvn-repo/</url>
  </repository>
</repositories>
```

**Gradle (build.gradle file)**
```javascript
repositories {
    maven { url "https://raw.github.com/PayU/paytouch-android/mvn-repo/" } 
    mavenCentral() 
}
```

```xml
<dependencies> 
  <!-- Other dependencies --> 
  <dependency> 
    <groupId>com.payu.android.sdk</groupId> 
    <artifactId>payment-library-widget</artifactId> 
    <version>1.5.0</version> 
  </dependency> 
  <dependency> 
    <groupId>com.payu.android.sdk</groupId> 
    <artifactId>payment-library-full</artifactId> 
    <version>1.5.0</version> 
  </dependency> 
</dependencies>
``` 

```javascript
compile('com.payu.android.sdk:payment-library-full:1.5.0') {
    exclude group: 'com.android.support', module: 'support-v4'
	exclude group: 'org.jetbrains', module: 'annotations'
}

compile('com.payu.android.sdk:payment-library-widget:1.5.0') {
    exclude group: 'com.android.support', module: 'support-v4'
	exclude group: 'org.jetbrains', module: 'annotations'
}
```


## Android Pay
For integration with Android Pay check docs\Android-Pay manual

## Additional Information
In SDK when using local environment if you set payment to be less than 100 PLN there will be thrown GENERIC_ERROR.
This will lets you test 'error' path.

## Backend
Configuration for backend: http://developers.payu.com/en/mobile_sdk.html#mobile_sdk_description 

## Changelog
Releases changelog can be found [here](CHANGELOG.md).

## Quick tour over features
<img src="/gfx/widget_new_1.5.0.png?raw=true" alt="Selected payment method widget" style="width:500px">
<img src="/gfx/payment_methods_empty.png?raw=true" alt="Empty payment method list" style="width:500px">
<img src="/gfx/payment_methods.png?raw=true" alt="Payment method list with instruments" style="width:500px">
<img src="/gfx/pay_by_links.png?raw=true" alt="Pay-By-Link payment methods" style="width:500px">
<img src="/gfx/login_screen.png?raw=true" alt="Login screen" style="width:500px">
<img src="/gfx/add_card.png?raw=true" alt="Adding credit card" style="width:500px">

## FAQ

**1. I implemented TokenProviderService class and I am using Local property in xml as environment, why there is a connection error?**

Local environment on Android is an environment that works without property: ``` "payu_token_service_class_full_qualified_name" ```. 
When this property is set - there will be _"connection error"_ information when moving to select payment view.
All connection/interaction with servers in case of _Local_ environment are mocked.

**2. What kind of servers can I access with this SDK ?**

* _Local_ - all request are mocked, 
* _Sandbox_ - request are connected to test instance of PayU Servers, more information - http://developers.payu.com/pl/overview.html#sandbox 
* _Production_ - there will be interaction with Banks so end client will be charged- http://developers.payu.com/pl/overview.html#endpoint_reference

**3. What means connection error when opening one of payment view ?**
* There is no connection with internet on mobile device ( example: wifi is off, or there where a problem with connecting to merchant backend )

* There is an issue while obtaining an access token
As described on http://developers.payu.com/en/mobile_sdk.html#mobile_sdk_description obtaining token is a process that require MerchantBackend that ask PayU Backend for a token and later pass it to mobile app.
PayTouch library will work only with ```mobile.sdk``` scope.
In this case we suggest to check POS if it is configured for mobile payments and check backend - backend communication with requesting for authorization token.

* e-mail collision
When creating a shop on environment the e-mail will be reserved and you cannot use it to purchase.

**4. Why there is no PaymentService class in library?**

PaymentService class is part of PayTouch library ( payment-library-widget and playent-library-full )  so when you are integrating with AndroidPay this class will not be find.

**5. I cannot see a payment type "xyz" on smartphone**

To configure payment type you need to create a shop on _Sandbox_ or _Production_ environment(sandbox: http://developers.payu.com/en/overview.html#sandbox) and ask for confgutation at: _itsupport@payu.pl_;
Currently using PayTouch you may pay via bank transfer, credit card and installment on the other hand while using _AndroidPay_ integration library with rest API you may in addition use AndroidPay.

**6. Can I change default flow or propose a new ?** 

In this case please contact with your consultant at PayU side.


**7. Will token from Sandbox environment work on Production environment ?**

No, currently you need to create on _Production_ new store and configure it like you configure your Sandbox store.


**8. Why client see information about successful transaction when I recived cancel transaction notification?**

PayU SDK "know nothing ;)" about processing transaction. Application with payment will receive "success" information when user will press 'pay' - so he will accept payment transaction.
Merchant should verify if payment transaction will end successful (check transaction status code ).

**9. What means "tick" in AppBar when user for example is paying using bank transfer?**

There can be aproblem with internet connection on smartphone so after the payment transfer was complieted user can press the tick to notify an app that he thinks the payment was successful and can go back to previous view.
In this case like, in other cases merchant needs to verify if payment was successful.

**10. Do I need to have an agreement with PayU to use Sandbox environment ?**

No, Sandbox is open for "testing and checking" PayU solutions to verify if it can meets your criteria.
When migrating to Production evironment you need to contact PayU to sign an agreement.

**11. How to integrate with PhoneGap?**

In this scenario we  suggest to use RestAPI - http://developers.payu.com/en/restapi.html .
