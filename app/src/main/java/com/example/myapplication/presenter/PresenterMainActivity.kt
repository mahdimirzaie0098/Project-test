package com.example.myapplication.presenter

import com.example.myapplication.model.ModelMainActivity
import com.example.myapplication.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) {

    fun onCreatePresenter(){
        view.onClickHandler(model.getId())
    }
}