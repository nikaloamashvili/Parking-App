package com.example.mylogin;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsRegister extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapsRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mapsregister.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsRegister newInstance(String param1, String param2) {
        MapsRegister fragment = new MapsRegister();
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

        View view = inflater.inflate(R.layout.fragment_mapsregister, container, false);
        Button buttonadd = view.findViewById(R.id.buttongetlatlonf);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addreso = ((EditText)view.findViewById(R.id.address)).getText().toString();
                Log.d("result" , addreso);
                Log.d("result" , "dsds");
                Geocoder locationAddress = new Geocoder(v.getContext());
                try {
                    List<Address> addresses= locationAddress.getFromLocationName(addreso,1);
                    if(addresses.size() > 0) {
                        Address addressl =addresses.get(0);
                        Parking parking= new Parking();
                        parking.setIsTaken(0);
                        parking.setLatitude(addressl.getLatitude());
                        parking.setLongitude(addressl.getLongitude());
                        parking.setName(addreso);
                        Log.d("result" , "4444444");
                        Log.d("result" , getArguments().getString("add"));
                        parking.setOwner(getArguments().getString("add"));
                        ((MainActivity)getActivity()).Addlocation(parking);
                        ((MainActivity)getActivity()).GetDataof(new MainActivity.MyCallback4() {
                            @Override
                            public void onCallback(User user) {
                                //((MainActivity)getActivity()).editDatauser(getArguments().getString("add"),user.getAdd_park()+1);
                                Integer z=user.getAdd_park();
                                Log.d("result" , z.toString());


                                ((MainActivity)getActivity()).editDatauser(getArguments().getString("add"),z+1);


                            }
                        });
                        String nameo=getArguments().getString("add");
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("add",nameo);

                        Navigation.findNavController(v).navigate(R.id.action_mapsregister_to_mapsFragment,bundle2);
                        Log.d("result" ,addressl.toString());
                    }else{
                        Toast.makeText(getView().getContext(),"not a vaild addres",Toast.LENGTH_LONG).show();
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });
        return view;
    }

}