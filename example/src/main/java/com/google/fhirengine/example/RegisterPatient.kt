package com.google.fhirengine.example

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.fhirengine.FhirEngine


class RegisterPatient : AppCompatActivity() {

  private lateinit var fhirEngine: FhirEngine

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    Log.d("RegisterPatientActivity", "onCreate() called")
    setContentView(R.layout.activity_register_patient)
    // Note that the Toolbar defined in the layout has the id "my_toolbar"
    //setSupportActionBar(findViewById(R.id.submit_toolbar))

    /* getting the toolbar */
    //val toolbar: Toolbar = findViewById<View>(R.id.submit_toolbar) as Toolbar

    //setting the title
    //toolbar.setTitle("Register Patient")
  }
}