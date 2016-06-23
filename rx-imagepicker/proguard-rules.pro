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
-target 1.7
-dontobfuscate
-dontoptimize
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose

#---------------------------------------------------------------------------------------------------
#Project classes

-keep class com.magorasystems.** { *; }
-keep interface com.magorasystems.**
-keep enum com.magorasystems.**
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes InnerClasses,EnclosingMethod
#---------------------------------------------------------------------------------------------------
## Retrolambda specific rules ##
# as per official recommendation: https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*
-keep class sun.misc.Unsafe { *; }

#---------------------------------------------------------------------------------------------------

# RxJava 0.21
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}

-keep class rx.observers.TestSubscriber {
    public <methods>;
}

-keep class rx.schedulers.Schedulers {
    public static ** test();
}

-keep class rx.operators.OperationReplay* {
    *;
}

-keep class rx.Observable* {
     public ** delay(...);
}

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#---------------------------------------------------------------------------------------------------

## Retrolambda specific rules ##
# as per official recommendation: https://github.com/evant/gradle-retrolambda#proguard
-dontwarn java.lang.invoke.*
-keep class sun.misc.Unsafe { *; }

#---------------------------------------------------------------------------------------------------

# Warnings
-dontwarn java.lang.invoke.*
-dontwarn com.magorasystems.**
-dontwarn android.support.v4.**
-dontwarn javax.annotation.**
-dontwarn rx.internal.**
-dontwarn rx.**