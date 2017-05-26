<h1 color="#ff0000">第一节课:UI绘制流程流式布局
<h5>一、从setContentView开始了解View的加载过程：</h5>
    <h6/>1、setContentView到底做了些什么？为什么调用后就可以显示我们想要的布局页面？<br/>
    <h6/>2、PhoneWindow到底是什么？和window的关系<br/>
    <h6/>3、DecorView是干什么用的？和我们布局有什么关系？ <br/>
    <h6/>4、requestFeature为什么要在setContentView之前调用？
    <h6/>5、LayoutInflater到底怎么把xml添加到DecorView?<br/>
         <blockquote>include 为什么不能作为xml资源布局的根节点？
         <br/>merge为什么不能作为xml资源布局的根节点？
    <h6/>小结：<br/>
           <blockquote/>
              1、每一个Activity都有一个关联的Window对象，用来描述应用程序窗口；<br/>
              2、每一个窗口内部又不包含一个DecorView对象，DecorView对象用来描述窗口的师徒--xml布局
           </blockquote><br/>
    上述是创建DecorView的过程</blockquote>
<h5>二、DecorView如何添加到Window</h5>
    看图片，最终调用了ViewRootImpl.setView,在setView方法里调用了View.assignParent(this);<br/>
    将DecorView的mParent设置成ViewRootImpl，这也就是为什么View再用requestLayout方法的时候最终会走到ViewRootImpl的requestLayout<br/>

    找到UI绘制流程的起始点
    ViewRootImpl#performTraversals()
    测量：performMeasure()
    摆放布局：performLayout()
    绘制：performDraw()
