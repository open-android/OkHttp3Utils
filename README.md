#OkHttp3Utils

OkHttp3.0封装框架，内部使用gson解析json数据

开始
===
在project的build.gradle添加如下代码(如下图)
```
allprojects {
    repositories {
        ...
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

```
![image](jitpack.png)


在build.gradle添加依赖
```
compile 'com.github.open-android:OkHttp3Utils:0.0.4'
```

####需要的权限
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```


####GET请求（内部使用Gson解析json数据）
```
        ItHeiMaHttp heiMaHttp = ItHeiMaHttp.getInstance().
           addHead("参数名称", "参数"). //加头
           addParam("参数名称", "参数"); //参数

         // WSCallBack<Bean> 中的数据类型必须给，如果只想要JSON,传入String即可
        heiMaHttp.get("BASE_URL", new WSCallBack<Bean>() {
            @Override
            public void onFailure(Call call, Exception e) {
               //失败
            }

            @Override
            public void onSuccess(Bean bean) {
                 //成功， 自己想要的Bean
            }
        });
```
####POST请求（内部使用Gson解析json数据）
```
        ItHeiMaHttp heiMaHttp = ItHeiMaHttp.getInstance().
            addHead("参数名称", "参数"). //加头
            addParam("参数名称", "参数"); //参数

         // WSCallBack<Bean> 中的数据类型必须给，如果只想要JSON,传入String即可
        heiMaHttp.post("BASE_URL", new WSCallBack<Bean>() {
            @Override
            public void onFailure(Call call, Exception e) {
               //失败
            }

            @Override
            public void onSuccess(Bean bean) {
                 //成功， 自己想要的Bean
            }
        });
```
####添加请求参数
```
heiMaHttp.addParam("key","value")
.addParam("key","value")
.addParam("key","value");

```
####添加请头
```
heiMaHttp.addHead("key","value")
.addHead("ke","value")
.addHead("key","value");

```


[回到顶部](#readme)
