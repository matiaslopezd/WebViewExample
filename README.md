# CustomTabs example

`CustomTabs` allows to open video calls over native apps without compatibility issues of `WebView`.

# How to use

To open a custom tabs is ready easy, add this lines:

```java
CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
CustomTabsIntent customTabsIntent = builder.build();
customTabsIntent.launchUrl(this, Uri.parse("https://website.com"));
```

The example website is this codesandbox: https://codesandbox.io/s/customtabs-xfkumz

## Hints

To close programatically the `CustomTabs` we recommend you to execute on the website `window.close()`, that is the best way to do it.

# Documentation

Please follow this link to more information: https://developer.android.com/reference/androidx/browser/customtabs/package-summary
