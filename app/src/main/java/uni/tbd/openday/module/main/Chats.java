package uni.tbd.openday.module.main;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import uni.tbd.openday.R;
import uni.tbd.openday.databinding.ActivityChatsBinding;
import uni.tbd.openday.module.main.chats.view.ChatsFragment;
import uni.tbd.openday.module.main.profile.view.ProfileFragment;
import uni.tbd.openday.module.main.users.view.UsersFragment;
import uni.tbd.openday.module.postdetail.view.PostDetailActivity;

public class Chats extends AppCompatActivity {


    private FragmentPagerAdapter adapter;
    private ActivityChatsBinding bind;
    private int[] backgroundColors, textColors;
    private int state;
    private int currColorBack, currColorText;

    private AnimatorSet animatorSet;

    private Fragment[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_chats);

        list = new Fragment[]{
                new ProfileFragment(),
                new ChatsFragment(),
                new UsersFragment()
        };

        adapter = new FragmentPagerViewAdapter(getSupportFragmentManager(), list, new String[] {
                getResources().getString(R.string.home),
                getResources().getString(R.string.chats),
                getResources().getString(R.string.users)
        });

        state = 0;
        backgroundColors = new int[]{
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.colorPrimaryDarkBlue)
        };
        textColors = new int[]{
                getResources().getColor(R.color.colorPrimaryDarkBlue),
                getResources().getColor(R.color.colorPrimaryDarkBlue),
                getResources().getColor(R.color.white)
        };
        currColorBack = backgroundColors[0];
        currColorText = textColors[0];

        bind.viewPager.setAdapter(adapter);
        bind.viewPager.setOffscreenPageLimit(3);
        bind.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                state = position;
                updateTabs();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bind.tabLayout.setupWithViewPager(bind.viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            ((ProfileFragment)list[0]).updatePost(
                    data.getStringExtra(PostDetailActivity.EXTRA_ID),
                    data.getLongExtra(PostDetailActivity.EXTRA_LIKE, 0),
                    data.getLongExtra(PostDetailActivity.EXTRA_COMMENT, 0),
                    data.getLongExtra(PostDetailActivity.EXTRA_VIEW, 0)
            );
        }
    }

    private void updateTabs() {
        if (animatorSet != null) animatorSet.pause();
        ValueAnimator animatorBack = ValueAnimator.ofArgb(currColorBack, backgroundColors[state]);
        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bind.tabLayout.setBackgroundColor((int)animation.getAnimatedValue());
                currColorBack = (int)animation.getAnimatedValue();
            }
        });

        final int selected = getResources().getColor(R.color.colorAccentDark);
        ValueAnimator animatorText = ValueAnimator.ofArgb(currColorText, textColors[state]);
        animatorText.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bind.tabLayout.setTabTextColors((int)animation.getAnimatedValue(), selected);
                currColorText = (int)animation.getAnimatedValue();
            }
        });

        animatorSet = new AnimatorSet();
        animatorSet
                .play(animatorBack)
                .after(animatorText);
        animatorSet.start();
    }
}