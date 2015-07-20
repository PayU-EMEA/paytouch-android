![PayU Logo](http://static.payu.com/images/mobile/payu_logo.png)

# PayTouch SDK for Android

SDK for Android Developers easing PayU payments platform integration. The SDK provides UI, network communication and security mechanisms to make payment easiest possible. The developer is required to implement only a few simple interfaces to accommodate payment order details.  

**Beware that this SDK works only in Poland**

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
    <version>1.3.0</version> 
  </dependency> 
  <dependency> 
    <groupId>com.payu.android.sdk</groupId> 
    <artifactId>payment-library-full</artifactId> 
    <version>1.3.0</version> 
  </dependency> 
</dependencies>
``` 

```javascript
compile('com.payu.android.sdk:payment-library-full:1.3.0') {
    exclude group: 'com.android.support', module: 'support-v4'
}

compile('com.payu.android.sdk:payment-library-widget:1.3.0') {
    exclude group: 'com.android.support', module: 'support-v4'
}
```

## Quick tour over features
