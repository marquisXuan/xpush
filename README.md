#push-message

### 使用Netty搭建WebSocket消息推送

这是一个基于netty的websocket服务器与客户端代码。

可基于客户端代码直接开发项目。

将原来的demo进行了一些业务整合



---

# 基于Netty的一个WeoSocket通信服务器与客户端代码(非JS代码)

咳咳，在这里呢，小轩就不多说什么是WebSocket的，还有呢，小轩为什么不给出JS-Client代码？网上太多代码可以用了。小轩这里的WebSocket客户端是非JS客户端，因为小轩的项目后台逻辑要与WebSocket服务器通信，所以才搞了个客户端出来。不多说，先上成果图。

1. 启动WebSocket服务器

   ![1](https://static.oschina.net/uploads/img/201805/21194813_5GmN.png)

2. 启动项目（客户端1）

   ![](https://static.oschina.net/uploads/img/201805/21195702_unup.png)

3. 使用ws-js客户端与服务器建立连接（客户端2）

   ![](https://static.oschina.net/uploads/img/201805/21200201_npv3.png)

4. 调用项目接口，由客户端1给客户端2发送消息。

   ![](https://static.oschina.net/uploads/img/201805/21200339_tdoq.png)

这里，小轩可以支持文本消息，也可以支持二进制数据。当然，逻辑写的很简单，就是简单的发送消息。但是可以在此基础上进行业务逻辑的修改，从而达到符合自己的业务需求。

好了，图呢，就先到这里，小轩这边已经在正常使用中。没有视频演示还真是硬伤。

之所以看到tdg_yyx跟连接里面的不一样，是因为在小轩的项目中集成的客户端中加了系统前缀。所以才会有这样的演示图。当然，如果想知道具体再怎么使用的话，依旧可以联系小轩我哦。



例子中的编码解码数据是使用的MsgPack对对象进行了序列化。但是从服务器发给浏览器客户端的时候使用的是文本消息哦。



项目源码已共享至github中，另起了一个新的repository哦。与之前的demo分开了。可以直接拿下来进行修改的哦。

[项目源码](https://github.com/marquisXuan/xpush)

[博文路径](https://my.oschina.net/yzwjyw/blog/1816270)

## 随意随意

![随意随意](http://app.hartedu.com/file/cant_delete.jpg)

#### 作者QQ: 562638362

#### 作者邮箱：tdg_yyx@foxmail.com



