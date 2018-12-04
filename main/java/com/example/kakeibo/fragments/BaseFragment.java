package com.example.kakeibo.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.example.kakeibo.activities.BaseActivity;

import butterknife.OnItemClick;

public abstract class BaseFragment extends Fragment {

    public BaseActivity obtainBaseActivity(){
        return (BaseActivity) getActivity();
    }

    public void navigateToFragment(BaseFragment fragment) {
        if (fragment == null) {
            return;
        }

        final BaseActivity activity = obtainBaseActivity();
        if (activity == null) {
            return;
        }

        activity.replaceFragment(fragment);
    }

}
