# 前言

android 碎片化非常严重，市面上的机型有几万中之多，导致于android程序员在做屏幕适配时，非常难受，而且把时间浪费在屏幕适配上面也得不偿失，况且还不一定能适配的好，而网络上好的适配方案并不多见，使用上也并不是最佳的，因此草民打算将个人的AutoLayout和大家分享~

# 框架介绍

1.编写代码简单

在代码中直接写px进行适配，相信没有比这个更爽的了吧~

<img src="https://github.com/walid1992/AutoLayout/blob/master/autolayout_task_xml.png" alt="xml代码样式"/>

2.代码侵入较少；

3.适配效果更佳；

<img src="https://github.com/walid1992/AutoLayout/blob/master/autolayout_baby.png" alt="宝贝界面" />

<img src="https://github.com/walid1992/AutoLayout/blob/master/autolayout_task.png" alt="任务界面" />

<img src="https://github.com/walid1992/AutoLayout/blob/master/autolayout_mine.png" alt="个人中心" />

  ...

看完上面的说明，想必大家会对autolayout会有所认可~


# 基本使用

## 引用方式：

```
compile 'com.walid:autolayout:1.0.7'
```

## application初始化

```
AutoLayoutConifg.getInstance().initConfig(this, 640, 1136);
```

## activity 注入Autolayout

方式一 ：继承

```
public class MainActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
    }

}
```

方式二 ：代码注入

```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
    }

    // 采用AutoLayoutUtils进行适配
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = AutoLayoutUtils.genAutoView(name, context, attrs);
        if (view != null) {
            return view;
        }
        return super.onCreateView(name, context, attrs);
    }
}
```

## 扩展

1、扩展控件：实现generateLayoutParams、onMeasure方法

```
public class AutoRadioGroup extends RadioGroup {

    public AutoRadioGroup(Context context) {
        super(context);
    }

    public AutoRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            AutoUtils.autoLayoutAdjustChildren(this);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    class LayoutParams extends RadioGroup.LayoutParams implements AutoUtils.AutoLayoutParams {

        private AutoLayoutInfo autoLayoutInfo;

        LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            autoLayoutInfo = AutoUtils.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return autoLayoutInfo;
        }

    }
}
```

2、自定义控件：继承Auto***Layout

```
public class MartianCell extends AutoRelativeLayout {

    private MartianViewHolder viewHolder;

    public MartianCell(Context context) {
        super(context);
    }

    public MartianCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new MartianViewHolder(inflater.inflate(R.layout.view_common_cell, this));
        initStyle(context, attrs);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MartianCell);
        String leftItem = a.getString(R.styleable.MartianCell_CommonCell_tv_left_item);
        String rightItem = a.getString(R.styleable.MartianCell_CommonCell_tv_right_item);
        a.recycle();
        setTvLeftItem(leftItem);
        setTvRightItem(rightItem);
    }

    public void setTvLeftItem(String content) {
        viewHolder.setText(R.id.tv_left_item, content);
    }

    public void setTvRightItem(String content) {
        viewHolder.setText(R.id.tv_right_item, content);
        viewHolder.setVisible(R.id.tv_right_item, !TextUtils.isEmpty(content));
    }

    public void setIvRightItem(String url) {
//        GlideUtils.load(getContext(), viewHolder.getView(R.id.iv_right_item), LoadParams.get(url)
//                .setStrokeColor(Color.parseColor("#dadada"))
//                .setError(R.mipmap.ic_place_holder)
//                .setStrokeWidth(4));
        viewHolder.setVisible(R.id.iv_right_item, true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        viewHolder.setVisible(R.id.iv_right_arrow, enabled);
    }
}
```

# 其他信息

作者信息 ：

[walid](https://github.com/walid1992)

CSDN 地址 ：

[逐日-walid](http://blog.csdn.net/walid1992/article)

简书 地址 ：

[walid](http://www.jianshu.com/users/a279a2f8ed63/latest_articles)

非常感谢 ：

[张鸿洋老师 AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)
