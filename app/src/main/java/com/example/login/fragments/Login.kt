package com.example.login.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.login.R
import com.example.login.entities.User
import com.example.login.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var v : View

    private lateinit var emailLogin : EditText
    private lateinit var passwordLogin : EditText
    private lateinit var buttonLogin : Button

    lateinit var nameText : String
    lateinit var emailText : String
    lateinit var passwordText : String

    private var users = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.login_fragment, container, false)

        // BINDINGS
        emailLogin = v.findViewById(R.id.emailLogin)
        passwordLogin = v.findViewById(R.id.passwordLogin)
        buttonLogin = v.findViewById(R.id.buttonLogin)

        // CREAMOS USUARIOS PARA LA LISTA
        users.add(User("brian", "brian@mail", "abc123"))
        users.add(User("luci", "luci@mail", "lilita"))
        users.add(User("pepe", "pepe@mail", "123abc"))

        return v

    }

    override fun onStart(){
        super.onStart()

        buttonLogin.setOnClickListener(){
            nameText = ""
            emailText = emailLogin.text.toString()
            passwordText = passwordLogin.text.toString()

            // RECORRER LA LISTA PARA MATCHEAR USUARIO Y CONTRASEÑA

            var result = findUser(emailText, passwordText, users)

            for (user in users) {
                if(user.email == emailText && user.password == passwordText){
                    nameText = user.name
                    break
                }
            }

            if(emailText.isEmpty() || passwordText.isEmpty()){
                Snackbar.make(v, "Debe completar los campos", Snackbar.LENGTH_SHORT).show()
            } else if(!result) {
                Snackbar.make(v, "Usuario no encontrado", Snackbar.LENGTH_SHORT).show()
            } else {
                val action = LoginDirections.actionLoginToWelcome(nameText)
                v.findNavController().navigate(action)
            }
        }
    }

    private fun findUser(email:String, password:String, users:MutableList<User>) : Boolean {
        var findUser = false

        for (user in users){
            if(user.email == email && user.password == password){
                findUser = true
                break
            }
        }
        return findUser
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}