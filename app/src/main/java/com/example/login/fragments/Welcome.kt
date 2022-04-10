package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.login.R
import com.example.login.viewmodels.LoginViewModel
import com.example.login.viewmodels.WelcomeViewModel

class Welcome : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var v: View

    private lateinit var welcomeMessage : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.welcome_fragment, container, false)
        return v
    }

    override fun onStart(){
        super.onStart()

        val userName = WelcomeArgs.fromBundle(requireArguments()).valueName
        welcomeMessage = v.findViewById(R.id.welcomeMessage)
        val concatMessage = "Bienvenidx $userName!"
        welcomeMessage.text = concatMessage

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}