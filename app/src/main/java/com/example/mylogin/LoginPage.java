package com.example.mylogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginPage extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginPage.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginPage newInstance(String param1, String param2) {
        LoginPage fragment = new LoginPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);

        Button buttonRegister = view.findViewById(R.id.registerButton);
        Button buttonLogin = view.findViewById(R.id.loginButton);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginPage_to_registerPage);
            }
        });

        /*buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity)getActivity()).LoginFunc();
                //((MainActivity)getActivity()).GetData();
                Navigation.findNavController(view).navigate(R.id.action_loginPage_to_mapsFragment);
            }
        });*/
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User myuser;
                String email = ((EditText)view.findViewById(R.id.username)).getText().toString();
                String password = ((EditText)view.findViewById(R.id.password)).getText().toString();
                if(!((email.equals("")) || (password.equals("")))){
                    ((MainActivity) getActivity()).LoginFunc(new MainActivity.MyCallback2() {
                        @Override
                        public void onCallback(Boolean logino) {
                            if (logino == Boolean.TRUE) {
                                Bundle bundle = new Bundle();
                                ((MainActivity)getActivity()).GetData(new MainActivity.MyCallback3() {
                                    @Override
                                    public void onCallback(User user) {
                                        bundle.putString("add",user.getEmail());
                                        Navigation.findNavController(v).navigate(R.id.action_loginPage_to_mapsFragment,bundle);
                                    }

                                });

                            }else {

                            }
                        }
                    });
                    }else{
                    Toast.makeText(getView().getContext(),"you did not enter you info",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }


}