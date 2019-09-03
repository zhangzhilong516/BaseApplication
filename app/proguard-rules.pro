
#混淆字典
-obfuscationdictionary proguard-dic.txt
-classobfuscationdictionary proguard-dic.txt
-packageobfuscationdictionary proguard-dic.txt
# https://www.jianshu.com/p/f3455ecaa56e
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------



#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------

# retrofit
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**
-dontwarn javax.annotation.**

# okhttp3
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.{*;}

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# rxjava rxandroid
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
-dontnote rx.internal.util.PlatformDependent

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# agentweb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}
-keep interface com.google.gson.**{*;}

# appsflyer
-dontwarn com.android.installreferrer

# face++ - 活体识别
-keep public class com.megvii.**{*;}

# 同盾 - 活体识别
-ignorewarnings
-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-renamesourcefileattribute SourceFile
-keep class com.oliveapp.** {*;}
-dontwarn com.oliveapp.**
-keepattributes InnerClasses
-keep class **.R$* {*;}

# 聚信立
-keep public class com.juxinli.sdk.**{*;}
# 同盾 - 在线账户
-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**
#-overloadaggressively
#Addidional for x5.sdk classes for apps
-keep class com.tencent.smtt.export.external.**{ *; }
-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener { *; }
-keep class com.tencent.smtt.sdk.CacheManager { public *; }
-keep class com.tencent.smtt.sdk.CookieManager { public *; }
-keep class com.tencent.smtt.sdk.WebHistoryItem { public *; }
-keep class com.tencent.smtt.sdk.WebViewDatabase { public *; }
-keep class com.tencent.smtt.sdk.WebBackForwardList { public *; }
-keep public class com.tencent.smtt.sdk.WebView { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebView$HitTestResult { public static final <fields>; public java.lang.String getExtra(); public int getType(); }
-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport { public <methods>; }
-keep public class com.tencent.smtt.sdk.WebView$PictureListener { public <fields>; public <methods>; }
-keepattributes InnerClasses -keep public enum com.tencent.smtt.sdk.WebSettings$** { *; }
-keep public enum com.tencent.smtt.sdk.QbSdk$** { *; }
-keep public class com.tencent.smtt.sdk.WebSettings { public *; }
-keepattributes Signature -keep public class com.tencent.smtt.sdk.ValueCallback { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebViewClient { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.DownloadListener { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebChromeClient { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams { public <fields>; public <methods>; }
-keep class com.tencent.smtt.sdk.SystemWebChromeClient{ public *; }
#1、extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* { public protected *; }
#2、interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* { public protected *; }
-keep public class com.tencent.smtt.sdk.WebViewCallbackClient { public protected *; }
-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebIconDatabase { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.WebStorage { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.DownloadListener { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.QbSdk { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.CookieSyncManager { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.Tbs* { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.utils.LogFileUtils { public <fields>; public <methods>;
}
-keep public class com.tencent.smtt.utils.TbsLog { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.utils.TbsLogClient { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.CookieSyncManager { public <fields>; public <methods>; }
#Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* { public <fields>; public <methods>; }
-keep public class com.tencent.smtt.utils.Apn { public <fields>; public <methods>; }
-keep class com.tencent.smtt.** { *; }
 #end
-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension { public <fields>; public <methods>; }
-keep class MTT.ThirdAppInfoNew { *; }
-keep class com.tencent.mtt.MttTraceEvent { *; }
 #Game related
-keep public class com.tencent.smtt.gamesdk.* { public protected *; }
-keep public class com.tencent.smtt.sdk.TBSGameBooter {    public <fields>;    public <methods>; }
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity { public protected *; }
-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy { public protected *; }
-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient { public *; }

-keepclasseswithmembers class * { ... *JNI*(...); }
-keepclasseswithmembernames class * { ... *JRI*(...); }
-keep class **JNI* {*;}
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**
-keep class cn.fraudmetrix.octopus.aspirit.bean.** { *; }

#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------



#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
# 抑制警告
-ignorewarnings
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
#指定执行几次优化，默认情况下，只执行一次优化。执行多次优化可以提高优化的效果，但是，如果执行过一次优化之后没有效果，就会停止优
-optimizationpasses 5
#混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify

-printmapping proguardMapping.txt
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------