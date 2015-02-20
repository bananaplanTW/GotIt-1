package com.bananaplan.gotit.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import com.bananaplan.gotit.R;
import com.bananaplan.gotit.Utilities;
import com.bananaplan.gotit.Utilities.MainTabTag;

/**
 * Created by logicmelody on 15/2/20.
 */
public class UIController {

    private Activity mMainActivity;

    private TabHost mTabHost;

    public UIController(Activity activity) {
        mMainActivity = activity;
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

    }

    private void initTabs() {
        mTabHost = (TabHost)mMainActivity.findViewById(android.R.id.tabhost);
        mTabHost.setup();
        addTab(MainTabTag.GOT_IT_TAB_TAG);
        addTab(MainTabTag.EVENTS_TAB_TAG);
        addTab(MainTabTag.FRIENDS_TAB_TAG);
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

    }
}
