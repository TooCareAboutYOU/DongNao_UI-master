<h4>1、兼容5.0以下版本，要求使用AndroidStudio2.2以上版本，gradle2.0版本</h4>

    1.1、添加： defaultConfig { vectorDrawables.useSupportLibrary=true; }
    1.2、添加： compile 'com.android.support:appcompat-v7:25.3.1'
    1.3、Activity必须继承AppCompatActivity
    1.4、文件布局添加：xmlns:app="http://schemas.android.com/apk/res-auto"
<h4>2、使用Activity时，前面添加一个flag设置</h4>

    static { AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);  }
    2.1、ImageView/ImageButton：Xml中 app:srcCompt代码里面使用无区别
    2.2、Button不支持app:srcCompt，Xml使用在Button的selector中
    2.3、RadioButton直接使用
    2.4、TextView的drawble直接使用
    2.5、使用冬天的VectorDrawble，不能直接修改pathData
