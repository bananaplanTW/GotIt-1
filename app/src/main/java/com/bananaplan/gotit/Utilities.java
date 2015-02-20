package com.bananaplan.gotit;

/**
 * Created by logicmelody on 15/2/20.
 */
public class Utilities {

    public static final int TAB_SIZE = 3;

    public static class MainTabTag {
        public static final String GOT_IT_TAB_TAG = "got_it_tab_tag";
        public static final String EVENTS_TAB_TAG = "events_tab_tag";
        public static final String FRIENDS_TAB_TAG = "friends_tab_tag";
    }

    public static class MainTabState {
        public static final int GOT_IT = 0;
        public static final int EVENTS = 1;
        public static final int FRIENDS = 2;
    }

    public static class MainFragmentTag {
        public static final String GOT_IT_FRAGMENT_TAG = "got_it_fragment_tag";
        public static final String EVENTS_FRAGMENT_TAG = "events_fragment_tag";
        public static final String FRIENDS_FRAGMENT_TAG = "friends_fragment_tag";
    }
}
