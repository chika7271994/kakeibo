package com.example.kakeibo.fragments;

import android.support.v4.app.Fragment;

import com.example.kakeibo.database.Database;
import com.example.kakeibo.activities.KakeiboListActivity;

public class BaseFragment extends Fragment {

    public KakeiboListActivity obtainKakeiboListActivity(){ //baseActivity
        return (KakeiboListActivity) getActivity();
    }

    public void navigateToFragment(BaseFragment fragment) {
        if (fragment == null) {
            return;
        }

        final KakeiboListActivity activity = obtainKakeiboListActivity();
        if (activity == null) {
            return;
        }

        activity.replaceFragment(fragment);
    }
}
