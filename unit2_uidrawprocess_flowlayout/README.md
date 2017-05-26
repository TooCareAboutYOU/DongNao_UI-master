<h1 color="#ff0000">第二节课:UI绘制流式布局
<h5>一、UI绘制流程：</h5>
<h5/>1、Measure</h5>
    MeasureSpec：在Measure流程中，系统将View的LayoutParams根据父容器所施加的规则转换成对应的MeasureSpec,
    在onMeasure中根据这个MeasureSpec来确定View的测量宽高。<br/>
    <blockquote>
        <h6>1)、测量模式：</h6>
            <blockquote>
            (1)、EXACTLY：父容器已经测量出所需要的精确大小，这也是childView的最终大小-------match_parent、精确值。<br/>
            (2)、AT_MOST：childView最终的大小不能超过父容器给的值-------wrap_content<br/>
            (3)、UNSPECIFIED:不确定、源码内部使用------一般在ScrollView、ListView
            </blockquote>
        <h6>2)、测量大小：根据测量模式来确定测量大小</h6>
        <h6>3)、源码里面的位元算</h6>
            <blockquote>
            &：取出对应的Mask类型的属性值<br/>
            |：添加对应的属性值<br/>
            & =~与非 或者（^异或）：去掉Mask类型的属性值
            </blockquote>
    </blockquote>