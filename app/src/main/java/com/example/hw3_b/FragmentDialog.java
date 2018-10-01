package com.example.hw3_b;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentDialog extends DialogFragment{
    EditText login;
    EditText email;
    Button btnOk;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.dialog_main, container, false);
        login = (EditText) rootView.findViewById(R.id.et_login);
        email = (EditText) rootView.findViewById(R.id.et_email);
        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button = getActivity().findViewById(R.id.btnOk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText login_et = getActivity().findViewById(R.id.et_login);
                EditText email_et = getActivity().findViewById(R.id.et_email);
                TextView login_tv = getActivity().findViewById(R.id.tv_login);
                TextView email_tv = getActivity().findViewById(R.id.tv_email);
                TextView email_header = getActivity().findViewById(R.id.tv_email_header);
                TextView login_header = getActivity().findViewById(R.id.tv_login_header);
                login_tv.setText(login_et.getText());
                email_tv.setText(email_et.getText());
                email_header.setText(email_et.getText());
                login_header.setText(login_et.getText());
            }
        });
    }
}
