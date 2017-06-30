# MulStateLayout
多状态布局切换封装，简化项目加载中，无数据，正常内容，请求失败，网络异常等状态的切换

本lib是在[伪文艺大叔][1][StatusLayout][2]基础上根据自己的需求简化修改的，并丰富了注释以方便阅读，特此感谢。[本体博文][3]

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

[1]:http://www.jianshu.com/u/030d732a71d2 "伪文艺大叔"
[2]:https://github.com/chenpengfei88/StatusLayout "StatusLayout"
[3]:http://www.jianshu.com/p/9d53893b3eda "本体博文"
