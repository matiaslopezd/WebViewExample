# Webview on Android example

This is an example of how embed Phone SDK on native Android app with WebView.

We recommend provide compatibility from Android 5.0 SDK or higher.

## What do you need

- Token integration (Public Token).
- A simple HTML with custom UI example and Phone SDK (Set by default).

## Permissions

The permissions is the most important part when try open a WebView in Android. You can check [here](https://github.com/videsk/WebViewAndroidExample/blob/master/app/src/main/java/com/example/webviewexample/MainActivity.java) where we need `camera` and `microphone` access in a single request.

Also you can customize behavior of grant or deny permissions of camera and microphone in the function named `onRequestPermissionsResult`. There you can handle behavior based on `requestCode`.

Any question please contact us to support@videsk.io.

## Thanks to

James McCracken - https://stackoverflow.com/a/34343101
