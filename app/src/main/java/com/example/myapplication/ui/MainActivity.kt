package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.androidwrapper.ActUtils
import com.example.myapplication.model.ModelMainActivity
import com.example.myapplication.presenter.PresenterMainActivity
import com.example.myapplication.view.ViewMainActivity

class MainActivity : AppCompatActivity(),ActUtils {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this,this)
        setContentView(view.binding.root)

            val presenter = PresenterMainActivity(view, ModelMainActivity(this))
            presenter.onCreatePresenter()
    }

    override fun finished() {
        finish()
    }
}

