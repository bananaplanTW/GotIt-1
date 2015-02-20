package com.bananaplan.gotit.friends;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bananaplan.gotit.R;

/**
 * Created by logicmelody on 15/2/20.
 */
public class FriendsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends_fragment, container, false);
        return v;
    }

}
