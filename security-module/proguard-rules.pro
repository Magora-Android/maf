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

-keep class  com.magorasystems.** { *; }
-keep interface  com.magorasystems.**
-keep enum  com.magorasystems.**
-keepattributes *Annotation*

#---------------------------------------------------------------------------------------------------

#Gson

-keep class com.google.gson.Gson** {
     public ** toJson(...);
}

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


# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-dontwarn okhttp3.**
-keep class retrofit2.** { *; }
-keep class okhttp3.** {*;}
-keepattributes Signature
-keepattributes Exceptions

#---------------------------------------------------------------------------------------------------

# Logback-android
## https://github.com/tony19/logback-android/wiki#proguard


-keep class ch.qos.** { *; }
-keep class org.slf4j.** { *; }
-keep interface org.slf4j.**
-keep enum org.slf4j.**
-keepattributes *Annotation*

#---------------------------------------------------------------------------------------------------
# Configuration for Guava 18.0
-keep class com.google.common.io.Resources {
    public static <methods>;
}
-keep class com.google.common.collect.Lists {
    public static ** reverse(**);
}
-keep class com.google.common.base.Charsets {
    public static <fields>;
}

-keep class com.google.common.base.Joiner {
    public static com.google.common.base.Joiner on(java.lang.String);
    public ** join(...);
}

-keep class com.google.common.collect.MapMakerInternalMap$ReferenceEntry
-keep class com.google.common.cache.LocalCache$ReferenceEntry

# http://stackoverflow.com/questions/9120338/proguard-configuration-for-guava-with-obfuscation-and-optimization
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# Guava 19.0
-dontwarn java.lang.ClassValue
-dontwarn com.google.j2objc.annotations.Weak
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement


#---------------------------------------------------------------------------------------------------


#Keep the annotated things annotated
-keepattributes *Annotation*

#Keep the dagger annotation classes themselves
-keep @interface dagger.*,javax.inject.*

#Keep the Modules intact
-keep @dagger.Module class *

#-Keep the fields annotated with @Inject of any class that is not deleted.
-keepclassmembers class * {
  @javax.inject.* <fields>;
}

#-Keep the names of classes that have fields annotated with @Inject and the fields themselves.
-keepclasseswithmembernames class * {
  @javax.inject.* <fields>;
}

# Keep the generated classes by dagger-compile
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-keep class dagger.internal.**{*;}
-keep interface dagger.internal.**{*;}
-keep interface dagger.MembersInjector{
  *;
 }
 -keep interface javax.inject.**{*;}
 -keep class javax.inject.**{*;}
 -keep enum javax.inject.**{*;}
  -keep @interface javax.inject.**{
     *;
  }

#---------------------------------------------------------------------------------------------------



# Warnings
-dontwarn com.magorasystems.**
-dontwarn org.slf4j.**
-dontwarn ch.qos.logback.**
-dontwarn com.google.common.**
-dontwarn com.squareup.javawriter.**
-dontwarn okhttp.**
-dontwarn com.astuetz.**
-dontwarn android.support.v4.**
-dontwarn org.jivesoftware.**
-dontwarn org.xmlpull.v1.**
-dontwarn net.sf.cglib.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.apache.**
-dontwarn com.google.inject.**
-dontwarn com.google.gson.**
-dontwarn rx.internal.**
-dontwarn rx.**
-dontwarn bolts.**