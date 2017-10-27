### android使用ViewPage实现卡片式画廊效果（代码很简单，一看就懂）

![1](C:\Users\lenovo\Desktop\1.png)



![2](C:\Users\lenovo\Desktop\2.png)



### 再来看看代码的使用，一看就懂的代码

![img](file:///C:\Users\lenovo\AppData\Roaming\Tencent\Users\535905846\QQ\WinTemp\RichOle\@MOA3ZN9N5LS_KHO08P5XBM.png)



```
//这是主页面的代码
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewCard = (ViewPager) findViewById(R.id.vp_card);
        mViewCard.setAdapter(new ViewPagerCardAdapter());
        mViewCard.setOffscreenPageLimit(2);//预加载2个
        mViewCard.setPageMargin(30);//设置viewpage之间的间距
        mViewCard.setClipChildren(false);
        mViewCard.setPageTransformer(true, new CardTransformer());
    }

    private class ViewPagerCardAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_task_viewcard, null);

            TextView cardNumber = view.findViewById(R.id.tv_task_card_number);
            cardNumber.setText("任务" + (position + 1));

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }
    }
}
```

```
//这是缩放效果的代码器
public class CardTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;//0.85f

    @Override
    public void transformPage(View page, float position) {
        if (position <= 1) {
         //   1.2f + (1-1)*(1.2-1.0)
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);

            page.setScaleX(scaleFactor);  //缩放效果

            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);
            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else {

            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
```



很简单吧这代码，直接拷贝根据自己的情况再修改一下就可以用了。觉得好的可以赞一个