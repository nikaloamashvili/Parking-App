package com.example.mylogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfile newInstance(String param1, String param2) {
        MyProfile fragment = new MyProfile();
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
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        TextView tex1 = view.findViewById(R.id.textViewhello);
        TextView tex2 = view.findViewById(R.id.textView2);
        TextView tex3 = view.findViewById(R.id.textView3);
        TextView tex4 = view.findViewById(R.id.textView4);
        TextView tex5 = view.findViewById(R.id.textView5);
        TextView tex6 = view.findViewById(R.id.textView6);
        TextView tex7 = view.findViewById(R.id.textView8);
        TextView tex8 = view.findViewById(R.id.textView9);
        tex1.setText("hello this is your info:");
        tex5.setText("my parkings:");
        tex6.setText("likes:");
        ((MainActivity)getActivity()).GetDataof(new MainActivity.MyCallback4() {
            @Override
            public void onCallback(User user) {
                tex2.setText(user.getEmail());
                tex3.setText(user.getPhone());
                tex4.setText(user.getID());
                tex7.setText("assaas");
                tex8.setText("sasas");
                tex7.setText(user.getAdd_park().toString());
                tex8.setText(user.getLikes().toString());
                }
        });





        return view;
    }
}