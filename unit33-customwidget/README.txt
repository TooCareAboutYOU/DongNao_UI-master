两种情况：
    1、继承字View的子类。只需要重写onMeasure测量好自己的宽高就可以了，最终调用setMeasureDimension()尺寸好自己的测量宽高。
            View提供的API resolvesize(int sieze, int widthMeasureSpec, int heightMeasureSpec);
            {
                int mode=MeasureSpec.getMode(widthMeasureSpec);
                int size=MeasureSpec.getSize(widthMeasureSpec);
                int viewSize=0;
                switch (mode) {
                    case MeasureSpec.EXACTLY:
                        viewSize = size; // 当前view的尺寸父容器的尺寸
                        break;
                    case MeasureSpec.AT_MOST:
                        viewSize = Math.min(size, getContentSize());// 当前view的尺寸就是内容尺寸和父容器尺寸
                        break;
                    case MeasureSpec.UNSPECIFIED:
                        viewSize = getContentSize();// 内容有多大，就设置多大尺寸
                        break;
                    default:
                        break;
                }
            }

    2、继承自ViewGroup的子类。不但需要重写onMeasure测量自己，还要测量子控件的规格大小，
       可以直接使用ViewGroup的工具方法测量里面的子控件，也可以自己来实现这一套子控件的测量(比如：)
       (1)、测量自己的尺寸 ViewGroup.onMeasure();
            <1.1、为每个child计算测量规格信息(MeasureSpec)
            <1.2、将上面测量后的结果，传给每一个子view，子view测量自己的尺寸child=measure();
            <1.3、子view测量完，ViewGroup就可以拿到这个子view的测量后的尺寸 child.getChildMeasuredSize() : child.getMeasureWidth() child.getMeasureHeight()
            <1.4、ViewGroup自己就可以根据自身的情况(Padding)等等，来计算自己的尺寸 ViewGroup.calculateSelfSize();
       (2)、保存自己的尺寸 ViewGroup.setMeasureDimension(size);
            ViewGroup中提供的Measure相关的api：measureChild、measureChilden(所有)、measureChildWithMargins、getMeasureSpec

自定义分类：
   一、继承控件
      指继承系统的原生控件，修改或者装饰系统控件默写方法。
      1、看系统控件里面是否有控件，能够支持（50%-80%）产品功能
      2、在原有基础上添加特定场景的产品功能
  二、自绘控件
     在系统的控件库中找不到相似逻辑的控件，完成产品逻辑不规则，较为特殊的产品需求，需要自己进行绘制
     1、封装性
     2、适配性

  三、组合控件
