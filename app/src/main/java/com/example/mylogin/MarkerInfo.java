package com.example.mylogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarkerInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarkerInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MarkerInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment markerinfo.
     */
    // TODO: Rename and change types and number of parameters
    public static MarkerInfo newInstance(String param1, String param2) {
        MarkerInfo fragment = new MarkerInfo();
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
        View view = inflater.inflate(R.layout.fragment_markerinfo, container, false);
        Button inuseb = view.findViewById(R.id.buttonuse);
        Button ilike =view.findViewById(R.id.buttonlike);
        inuseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                if(buttonText.equals("in use!")){
                    inuseb.setText("i am going!");
                    ((MainActivity)getActivity()).editData(getArguments().getString("add"),1);
                    //Navigation.findNavController(view).navigate(R.id.action_markerinfo_to_mapsFragment);
                    Log.d("result" , "1");
                }else {
                    Log.d("result" , "2");
                    inuseb.setText("in use!");
                    ((MainActivity)getActivity()).editData(getArguments().getString("add"),0);
                    String nameo=((MainActivity)getActivity()).me;
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("add",nameo);
                    Navigation.findNavController(view).navigate(R.id.action_markerinfo_to_mapsFragment,bundle2);
                }
                //((MainActivity)getActivity()).editData(getArguments().getString("add"));

                //Navigation.findNavController(view).navigate(R.id.action_markerinfo_to_mapsFragment);
            }
        });

        ilike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).GetLocations(new MainActivity.MyCallback() {
                    @Override
                    public void onCallback(Parking parkings) {
                        if(parkings.getName().equals(getArguments().getString("add"))){
                            ((MainActivity)getActivity()).GetDataof(new MainActivity.MyCallback4() {
                                @Override
                                public void onCallback(User user) {
                                    ((MainActivity)getActivity()).editDatauser2(parkings.getOwner(),user.getLikes()+1);
                                }
                            });

                        }
                    }
                });
            }
        });

        // Inflate the layout for this fragment
        TextView tv = view.findViewById(R.id.textViewinfo);
        Log.d("result" , getArguments().getString("add"));
        tv.setText(getArguments().getString("add"));

        return view;
    }
}