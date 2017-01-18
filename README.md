#OkHttp3Utils

OkHttp3.0封装框架，内部使用gson解析json数据

项目地址:[https://github.com/open-android/OkHttp3Utils](https://github.com/open-android/OkHttp3Utils "OKHttp3.0")

简书：[http://www.jianshu.com/p/e9258c1bc5ce](http://www.jianshu.com/p/e9258c1bc5ce "OKHttp3.0")

* 爱生活,爱学习,更爱做代码的搬运工,分类查找更方便请下载黑马助手app


![黑马助手.png](http://upload-images.jianshu.io/upload_images/4037105-f777f1214328dcc4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 使用步骤
### 1. 在project的build.gradle添加如下代码(如下图)

	allprojects {
	    repositories {
	        ...
	        maven { url "https://jitpack.io" }
	    }
	}

![](http://oi5nqn6ce.bkt.clouddn.com/itheima/booster/code/jitpack.png)

### 2. 在Module的build.gradle添加依赖

     compile 'com.github.open-android:OkHttp3Utils:0.0.4'



### 3. 需要的权限

	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />



### 4. GET请求（内部使用Gson解析json数据）

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

### POST请求（内部使用Gson解析json数据）

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

### 添加请求参数

	heiMaHttp.addParam("key","value")
	.addParam("key","value")
	.addParam("key","value");


### 添加请头

	heiMaHttp.addHead("key","value")
	.addHead("ke","value")
	.addHead("key","value");


* retrofit网络工具类推荐：[https://github.com/open-android/RetrofitUtils](https://github.com/open-android/RetrofitUtils "retrofit")

详细的使用方法在DEMO里面都演示啦,如果你觉得这个库还不错,请赏我一颗star吧~~~ 

欢迎关注微信公众号

![](http://oi5nqn6ce.bkt.clouddn.com/itheima/booster/code/qrcode.png)
