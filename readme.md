#Android中跨进程通信的AIDL(android接口定义语言)

定义一个客户端和服务端要实现的功能接口,如AInterface, 比如:登录login(username,password)

使用AIDL来编写功能接口,AS会帮我们实现一个公用的java类,实现android.os.IInterface接口,关键一点是,其内部创建了一
个Stub类,该类继承了Binder,并实现了AInterface
这里关键一个方法是asInterface(IBinder iBinder)方法,该方法的逻辑如下
1.查询本地是否有实现AInterface接口的对象 (在服务端返回true,)
2.在客户端没有找到,创建一个代理,根据iBinder找到服务器的服务实现对象的引用


客户端bindService成功后,会获取的一个ServiceConnection
在ServiceConnection中的成功连接回调方法中,IBinder作为一个服务给客户端,至此,客户端便拥有了远程服务