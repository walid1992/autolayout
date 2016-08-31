##AutoLayoutSample ##

#### 介绍 ##

android 屏幕适配最佳实践，极大的减少开发成本，直接拿着设计师给量身定做的px，就可以搞定UI适配。

优点：

```
    1、编写代码简单；
    2、代码侵入较少；
    3、适配效果更佳；
```

一、效果展示：

<img src="http://img.blog.csdn.net/20160830193628000" width = "320" height = "567" alt="UI标注" align=center />

二、xml代码样式

<img src="http://img.blog.csdn.net/20160830195850819" width = "590" height = "570" alt="UI标注" align=center />

三、基本使用

1、引用方式：

```
compile 'com.walid:autolayout:1.0.6'
```

2、application初始化

```
AutoLayoutConifg.getInstance().initConfig(this, 640, 1136);
```

3、activity使用

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

三、扩展

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
public class CommonCell extends AutoRelativeLayout {

    @Bind(R.id.tv_left_item)
    TextView tvLeftItem;
    @Bind(R.id.tv_right_item)
    TextView tvRightItem;
    @Bind(R.id.iv_right_item)
    ImageView ivRightItem;
    @Bind(R.id.rl_right_item)
    RelativeLayout rlRightItem;
    @Bind(R.id.rl_cell)
    RelativeLayout rlCell;

    public CommonCell(Context context) {
        super(context);
    }

    public CommonCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_common_cell, this);
        ButterKnife.bind(this, view);
        updateStyle(context, attrs);
    }

    private void updateStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonCell);
        String leftItem = a.getString(R.styleable.CommonCell_CommonCell_tv_left_item);
        String rightItem = a.getString(R.styleable.CommonCell_CommonCell_tv_right_item);
        a.recycle();
        setTvLeftItem(leftItem);
        setTvRightItem(rightItem);
    }

    public void setTvLeftItem(String content) {
        tvLeftItem.setText(content);
    }

    public void setTvRightItem(String content) {
        tvRightItem.setText(content);
        tvRightItem.setVisibility(TextUtils.isEmpty(content) ? GONE : VISIBLE);
    }

}
```

#### 其他信息 ##

作者信息 ：

[walid](https://github.com/walid1992)

非常感谢 ：

[张鸿洋老师 AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)



