package com.hsi.hearthstoneinfo;

/**
 * Created by Cristo on 03/02/2018.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmailTab extends  Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.email_tab, container, false);
        return rootView;
    }
}
