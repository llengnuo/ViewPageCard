package demo.card.com.viewpagecarddemo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
