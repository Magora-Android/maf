# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#---------------------------------------------------------------------------------------------------

#Unit/Mock tests

# Proguard rules that are applied to your test apk/code.
-ignorewarnings

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn com.squareup.javawriter.JavaWriter
-dontwarn org.hamcrest.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.mockito.**
-dontwarn okhttp3.mockwebserver.**

-keep class org.hamcrest.** {
   *;
}

-keep class  org.mockito.**{
  *;
}

-keep class  okhttp3.mockwebserver.** {
    *;
}

-keep class org.junit.** { *; }
-dontwarn org.junit.**

-keep class junit.** { *; }
-dontwarn junit.**

-keep class sun.misc.** { *; }
-dontwarn sun.misc.**

-keep class rx.** { *; }


-keep public class * extends rx.observers.TestSubscriber{
            *;
}

#---------------------------------------------------------------------------------------------------