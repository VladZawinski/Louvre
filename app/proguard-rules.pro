# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Models
-keep class non.shahad.stayhomegallery.data.** {*;}
#-keep class non.shahad.stayhomegallery.data.db.local.** {*;}
#-keep class com.codex.luckyyadanar.core.** {*;}

#Native Methods
-keepclasseswithmembernames class * { native <methods>; }

# Okhttp
-keep class okhttp3.* {*;}
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Retrofit
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Dagger 2
-dontwarn com.google.errorprone.annotations.**

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Preserve all Dexter classes and method names

-keepattributes InnerClasses, Signature, *Annotation*


# View Model
-keep class * extends androidx.lifecycle.ViewModel {
    <init>();
}