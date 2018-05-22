#push-message

### 使用Netty搭建WebSocket消息推送

这是一个基于netty的websocket服务器与客户端代码。

可基于客户端代码直接开发项目。

将原来的demo进行了一些业务整合



### 目录结构

|- xpush

|——push-client

|————org.yyx.message.push.client

|——————client		// 客户端代码

|——————config		// 从yml文件中加载配置的Netty配置类包

|——————entity		// 用来在服务器与客户端之间进行传输的实体类包

|——————handler		// WebSocket客户端处理包

|——————listener		// 用于启动客户端的监听器

|——————util			// 此处应该写接口，主要用来做传输消息的逻辑

|————PushClientApplication  // SpringBoot 启动类

|——push-server

|————org.yyx.message.push.server

|——————config     // 从yml文件中加载配置的Netty配置类包

|——————entity     // 用来在服务器与客户端之间进行传输的实体类包

|——————handler  // WebSocket服务端处理包

|——————listener  // WebSocket服务端监听器

|——————main      // 服务端代码

|——————util		// 此处应该写接口，主要用来做传输消息的逻辑

|————ServerApplication.java	// SpringBoot 启动类



#### 服务端的处理

1. WebSocketUrl为ws://localhost:9999/oa/web_socket/{uri}  其中**{uri}**用来接收业务参数
2. 如果服务端接收的消息是文本消息，则返回给客户端的内容为：**你发的消息是：+ 原消息内容**
3. 如果是二进制数据，则会对其进行解码，编解码使用的是MessagePack工具。若想自己开发自己的业务逻辑，可以将**handlerWebSocketFrame**方法抽出到接口中，根据不同的业务走自己的业务逻辑。在源码中，服务端和客户端使用同一个实体类。进行简易的点对点通信。详见**WebSocketMessageEntity**实体类。

#### 客户端的处理

1. 在源码中，客户端连接的是本地服务器，连接成功后进行WebSocket通信。url为：ws://127.0.0.1:9999/web_socket/${userName}其中 userName为用户名。即我自己的业务参数。
2. 连接成功后，将userName的Channel保留。当需要给服务端发送消息时，根据用户名获取自己与服务器已连接的通道，进行消息传输。
3. 现在客户端的处理逻辑是发送二进制数据，指定服务端将消息发送给哪个客户端。如果有自己的业务逻辑，可根据服务端业务进行相应的修改。

#### 测试用例

1. 启动服务端。
2. 将客户端代码集成在已有项目中（此处我集成在了一个基于tomcat应用服务器的web项目）
3. 启动客户端（此处，我将项目以一个用户的身份与服务端进行通信system_manage)
4. 通过web项目的登陆入口，将一个个用户与服务端进行连接（项目中的前端页面，使用js直接与服务端通信，但触发点是登陆系统之后）
5. 用户A发送消息给用户B

----

到目前为止，仍然没有看到客户端的作用对吧。不急，向下看。

当Web服务器处理了一些业务，需要给当前用户A发送消息通推送，可以在后台直接调用客户端发送消息给用户的方法，此时，就用到客户端了。

WEB后台调用client发送消息的方法，以上面提到的system_manage身份发送消息给指定用户。消息以二进制数据到达服务端，服务端有写好的业务逻辑，将消息解码后，回写数据给用户A（没错，用户A就是通过js与服务端建立连接的前台用户）当然，这里也可以做到给所以在线用户发送消息。这里就不多说了。原理是一样的。



[博文路径](https://my.oschina.net/yzwjyw/blog/1816270)

## 随意随意

![随意随意](http://app.hartedu.com/file/cant_delete.jpg)

#### 作者QQ: 562638362

#### 作者邮箱：tdg_yyx@foxmail.com



