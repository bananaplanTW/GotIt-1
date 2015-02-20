package com.bananaplan.gotit.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;

import com.bananaplan.gotit.AbstractTabViewPagerAdapter;
import com.bananaplan.gotit.R;
import com.bananaplan.gotit.Utilities;
import com.bananaplan.gotit.Utilities.MainTabTag;
import com.bananaplan.gotit.Utilities.MainTabState;
import com.bananaplan.gotit.Utilities.MainFragmentTag;
import com.bananaplan.gotit.events.EventsFragment;
import com.bananaplan.gotit.friends.FriendsFragment;
import com.bananaplan.gotit.gotit.GotItFragment;

/**
 * Created by logicmelody on 15/2/20.
 */
public class UIController implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private Activity mMainActivity;
    private FragmentManager mFragmentManager;

    private TabHost mTabHost;
    private SwipeControllableViewPager mViewPager;
    private TabViewPagerAdapter mTabViewPagerAdapter;

    private GotItFragment mGotItFragment;
    private EventsFragment mEventsFragment;
    private FriendsFragment mFriendsFragment;

    public UIController(Activity activity) {
        mMainActivity = activity;
        mFragmentManager = mMainActivity.getFragmentManager();
    }

    public void onActivityCreate(Bundle savedInstanceState) {
        initialize();
    }

    private void initialize() {
        initTabs();
        initFragments();
        initViewPager();
    }

    private void initFragments() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        // Init GotItFragment
        mGotItFragment = (GotItFragment) mFragmentManager
                .findFragmentByTag(MainFragmentTag.GOT_IT_FRAGMENT_TAG);
        if (mGotItFragment == null) {
            mGotItFragment = new GotItFragment();
            transaction.add(R.id.tab_pager, mGotItFragment, MainFragmentTag.GOT_IT_FRAGMENT_TAG);
        }

        // Init EventsFragment
        mEventsFragment = (EventsFragment) mFragmentManager
                .findFragmentByTag(MainFragmentTag.EVENTS_FRAGMENT_TAG);
        if (mEventsFragment == null) {
            mEventsFragment = new EventsFragment();
            transaction.add(R.id.tab_pager, mEventsFragment, MainFragmentTag.EVENTS_FRAGMENT_TAG);
        }

        // Init FriendsFragment
        mFriendsFragment = (FriendsFragment) mFragmentManager
                .findFragmentByTag(MainFragmentTag.FRIENDS_FRAGMENT_TAG);
        if (mFriendsFragment == null) {
            mFriendsFragment = new FriendsFragment();
            transaction.add(R.id.tab_pager, mFriendsFragment, MainFragmentTag.FRIENDS_FRAGMENT_TAG);
        }

        transaction.hide(mGotItFragment);
        transaction.hide(mEventsFragment);
        transaction.hide(mFriendsFragment);
        transaction.commit();
    }

    private void initTabs() {
        mTabHost = (TabHost)mMainActivity.findViewById(android.R.id.tabhost);
        mTabHost.setup();
        addTab(MainTabTag.GOT_IT_TAB_TAG);
        addTab(MainTabTag.EVENTS_TAB_TAG);
        addTab(MainTabTag.FRIENDS_TAB_TAG);
        mTabHost.setOnTabChangedListener(this);
    }

    private void addTab(String tag) {
        mTabHost.addTab(mTabHost.newTabSpec(tag).setIndicator(getTabTitle(tag))
                .setContent(new RunningTabContentFactory(mMainActivity)));
    }

    private String getTabTitle(String tag) {
        if(MainTabTag.GOT_IT_TAB_TAG.equals(tag)) {
            return mMainActivity.getString(R.string.got_it_tab_text);
        } else if(MainTabTag.EVENTS_TAB_TAG.equals(tag)) {
            return mMainActivity.getString(R.string.events_tab_text);
        } else if(MainTabTag.FRIENDS_TAB_TAG.equals(tag)) {
            return mMainActivity.getString(R.string.friends_tab_text);
        } else {
            return null;
        }
    }

    private void initViewPager() {
        mViewPager = (SwipeControllableViewPager)mMainActivity.findViewById(R.id.tab_pager);
        mTabViewPagerAdapter = new TabViewPagerAdapter(mFragmentManager, Utilities.TAB_SIZE);
        mViewPager.setAdapter(mTabViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        mViewPager.setCurrentItem(mTabHost.getCurrentTab());
    }

    /**
     * Control which fragment needed to be displayed according to the tab
     *
     * @author DannyLin
     */
    public class TabViewPagerAdapter extends AbstractTabViewPagerAdapter {

        private Fragment mFragmentAtMyTab;

        public TabViewPagerAdapter(FragmentManager fm, int size) {
            super(fm, size);
        }

        @Override
        protected Fragment getFragment(int position) {
            switch (position) {
                case MainTabState.GOT_IT:
                    return mGotItFragment;
                case MainTabState.EVENTS:
                    return mEventsFragment;
                case MainTabState.FRIENDS:
                    return mFriendsFragment;
            }
            return null;
        }

    }
}
