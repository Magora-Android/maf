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

#Keep the annotated things annotated
-keepattributes *Annotation*

#Keep the dagger annotation classes themselves
-keep @interface dagger.**,javax.inject.**

#Keep the Modules intact
-keep @dagger.Module class *

#-Keep the fields annotated with @Inject of any class that is not deleted.
-keepclassmembers class * {
  @javax.inject.* <fields>;
  @javax.inject.* <methods>;
}


#-Keep the names of classes that have fields annotated with @Inject and the fields themselves.
-keepclasseswithmembernames class * {
  @javax.inject.* <fields>;
  @javax.inject.* <methods>;
}

# Keep the generated classes by dagger-compile
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-keep interface javax.inject.**{*;}
-keep class javax.inject.**{*;}
-keep enum javax.inject.**{*;}

-keep class dagger.internal.**{*;}
-keep interface dagger.internal.**{*;}
-keep interface dagger.MembersInjector{
  *;
 }
 -keep @interface javax.inject.**{
    *;
 }

 #---------------------------------------------------------------------------------------------------
 # SnakeYAML
 -keep class org.yaml.snakeyaml.** { public protected private *; }
 -keep class org.yaml.snakeyaml.** { public protected private *; }
 -dontwarn org.yaml.snakeyaml.**

 # Joda Time
 -dontwarn org.joda.convert.**
 -dontwarn org.joda.time.**
 -keep class org.joda.time.** { *; }
 -keep interface org.joda.time.** { *; }
#---------------------------------------------------------------------------------------------------