# MulStateLayout

[![](https://jitpack.io/v/changliugang/MulStateLayout.svg)](https://jitpack.io/#changliugang/MulStateLayout)
[![API](https://img.shields.io/badge/API-18%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=18)

多状态布局切换封装，简化项目加载中，无数据，正常内容，请求失败，网络异常等状态的切换

本lib是在[伪文艺大叔][1]的[StatusLayout][2]基础上根据自己的需求简化修改的，并丰富了注释以方便阅读，特此感谢。奉上前辈项目的[本体博文][3]

![演示](https://github.com/changliugang/MulStateLayout/raw/master/art/show.gif)  
 
## 引入依赖
1. 在项目跟目录下build.gradle中如下添加。
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. 在app下的build.gradle如下添加依赖。
```
dependencies {
	        compile 'com.github.changliugang:MulStateLayout:v1.0.0'
}
```
## 核心类

类名 | 描述
----- | -----
CoreFrameLayout | 核心布局，多种状态布局包含于其中
MulStateLayoutHelper | 帮助类，控制状态切换，直接使用的类
OnStateViewShowListener | 状态布局显示隐藏的监听器
OnRetryListener | 点击重试按钮的监听器

## MulStateLayoutHelper常用配置

函数名 | 描述
----- | -----
loadingLayoutResId | 设置加载中布局
contentLayoutResId | 设置正常内容布局
emptyLayoutResId | 设置无数据布局
errorLayoutResId | 设置请求出错布局
netWorkErrorViewResId | 设置网络异常布局
onStateViewShowListener | 设置状态布局显示隐藏的监听器
emptyDataRetryViewId | 设置无数据布局点击重试控件
errorRetryViewId | 设置请求错误布局点击重试控件
netWorkErrorRetryViewId | 设置网络异常布局点击重试控件
onRetryListener | 设置点击重试按钮的监听器
emptyDataIconImageId | 设置无数据布局图标
emptyDataTextTipId | 设置无数据布局提示文字
errorIconImageId | 设置请求出错布局图标
errorTextTipId | 设置请求出错布局提示文字

## 常用函数
函数名 | 描述
----- | -----
showContent | 显示正常内容布局
showEmpty | 显示无数据布局
showLoading | 显示加载中布局
showError | 显示错误布局
showNetWorkError | 显示网络异常布局

## 这里有几点需要注意：
* 在onCreate设置setContentView(R.layout.activity_normal)会覆盖我们的多状态布局，所以必须要在MulStateLayoutHelper的contentLayoutResId函数中设  置正常内容布局。
* 添加点击重试功能，需要指定RetryView的Id，并实现OnRetryListener监听。RetryView的Id需要是对应布局里的控件，否则，你懂的。
* 修改图标和提示文字需要在MulStateLayoutHelper设置对应的资源id，之后代码中调用showXXX才行。


[1]:http://www.jianshu.com/u/030d732a71d2 "伪文艺大叔"
[2]:https://github.com/chenpengfei88/StatusLayout "StatusLayout"
[3]:http://www.jianshu.com/p/9d53893b3eda "本体博文"
