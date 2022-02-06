package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
//
            ((MainActivity)getActivity()).GetLocations(new MainActivity.MyCallback() {
                @Override
                public void onCallback(Parking parkings) {
                    Log.d("result" , parkings.getLongitude().toString());
                    if(parkings.getIsTaken()==0){
                        LatLng parkingLocation = new LatLng(parkings.getLatitude(), parkings.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(parkingLocation).title(parkings.getName()));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(parkingLocation));

                    }
                }
            });
            EditText to_sherch = getView().findViewById(R.id.editTextTextPersonName2);
            Button sherchbb = getView().findViewById(R.id.radioAZ);

            sherchbb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Geocoder locationAddress = new Geocoder(view.getContext());
                    try {
                        List<Address> addresses= locationAddress.getFromLocationName(to_sherch.getText().toString(),1);
                        if(addresses.size() > 0){
                            Address addressl =addresses.get(0);
                            LatLng parkingLocation1 = new LatLng(addressl.getLatitude(), addressl.getLongitude());
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parkingLocation1,12));
                        }else{
                            Toast.makeText(getView().getContext(),"not a vaild addres",Toast.LENGTH_LONG).show();
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });



            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    Log.d("result" , marker.getTitle());
                    Bundle bundle = new Bundle();
                    bundle.putString("add",marker.getTitle());
                    Navigation.findNavController(getView()).navigate(R.id.action_mapsFragment_to_markerinfo,bundle);
                    return false;
                }
            });
        }
    };




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        Button baddp = view.findViewById(R.id.addparking);
        Button logoutb = view.findViewById(R.id.logoutb);
        Button my_prop = view.findViewById(R.id.radioAK);





        baddp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameo=getArguments().getString("add");
                Bundle bundle2 = new Bundle();
                bundle2.putString("add",nameo);

                Navigation.findNavController(v).navigate(R.id.action_mapsFragment_to_mapsregister,bundle2);
            }
        });

        my_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameo=getArguments().getString("add");
                Bundle bundle2 = new Bundle();
                bundle2.putString("add",nameo);
                Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_myProfile,bundle2);
            }
        });

        logoutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_loginPage);
            }
        });

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }




//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // TODO Add your menu entries here
//        super.onCreateOptionsMenu(menu, inflater);
//    }


}