package com.example.android_reference.fragments;

import android.app.Activity;
import android.app.Fragment;

import com.example.android_reference.BaseActivity;
import com.example.android_reference.DataManager;
import com.example.libs.loader.ImageClient;

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        getDataManager().invalidateListener();
    }

    public ImageClient getImageClient() {
        return ((BaseActivity) getActivity()).getImageClient();
    }

    public DataManager getDataManager() {
        return ((BaseActivity) getActivity()).getDataManager();
    }
}
