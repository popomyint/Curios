package com.example.curiosi.view;

import com.example.curiosi.activity.HomeActivity;
import com.example.curiosi.model.DataModel;

import java.util.List;

public interface HomeView extends BaseView {
    void getData(List<DataModel> dataList);
}
