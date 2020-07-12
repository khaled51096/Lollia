package com.example.lollia.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lollia.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.Fragment;

public class Home extends Fragment{
   /* private Button logout;
    private FirebaseAuth mauth;*/

    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, container, false);
      /*  logout = (Button)view.findViewById(R.id.logout);
        mauth = FirebaseAuth.getInstance();
        final Login log1= new Login();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                mauth.signOut();
                Intent intent = new Intent(getActivity().getApplication(),Login.class);
                startActivity(intent);
            }
        });*/
        return view;

    }
}
