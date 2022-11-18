package com.example.flightradar65.ui.dashboard;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightradar65.R;
import com.example.flightradar65.RetrofitAPI;
import com.example.flightradar65.RetrofitClient;
import com.example.flightradar65.RetrofitClientLogo;
import com.example.flightradar65.data.ApiResponse;
import com.example.flightradar65.data.Dataset;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavInfoRetriever extends RecyclerView.Adapter<FavInfoRetriever.ViewHolder> {
    List<ApiResponse> cleanData = new ArrayList<>();
    private DataSnapshot userFav;
    DatabaseReference databaseReference;
    RetrofitAPI serviceLogo = RetrofitClientLogo.createService(RetrofitAPI.class);
    RetrofitAPI service = RetrofitClient.createService(RetrofitAPI.class);
    String username = "augustinmariage@studentjuniacom";

    public FavInfoRetriever() {
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.fav_infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getUid()!=null) username = mAuth.getUid();
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        databaseReference = FirebaseDatabase.getInstance("https://my-project-app-366214-default-rtdb.europe-west1.firebasedatabase.app/").getReference("/");
        databaseReference.child("Fav").child(username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userFav = task.getResult();
                loadAllDataset();
            }
        });
        return new ViewHolder(view);
    }

    public void loadAllDataset(){
        for(DataSnapshot children:userFav.getChildren()){
            switch (Objects.requireNonNull(children.getKey())){
                case "flightAirline":
                    for (DataSnapshot flightAirline:children.getChildren()) {
                        Call<Dataset> callAsync = service.getApiResponseAirIcao(flightAirline.getKey());
                        callAsync.enqueue(new Callback<Dataset>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                                assert response.body() != null;
                                cleanData.addAll(response.body().getResponse());
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                                System.out.println(throwable);

                            }
                        });
                    }

                case "flightArr":
                    for (DataSnapshot flightAirline:children.getChildren()) {
                        Call<Dataset> callAsync = service.getApiResponseArrIcao(flightAirline.getKey());
                        callAsync.enqueue(new Callback<Dataset>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                                assert response.body() != null;
                                cleanData.addAll(response.body().getResponse());
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                                System.out.println(throwable);

                            }
                        });
                    }

                case "flightDep":
                    for (DataSnapshot flightAirline:children.getChildren()) {
                        Call<Dataset> callAsync = service.getApiResponseDepIcao(flightAirline.getKey());
                        callAsync.enqueue(new Callback<Dataset>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                                assert response.body() != null;
                                cleanData.addAll(response.body().getResponse());
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                                System.out.println(throwable);

                            }
                        });
                    }

                case "flightNo":
                    for (DataSnapshot flightAirline:children.getChildren()) {
                        Call<Dataset> callAsync = service.getApiResponseFltNo(flightAirline.getKey());
                        callAsync.enqueue(new Callback<Dataset>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onResponse(@NonNull Call<Dataset> call, @NonNull Response<Dataset> response) {
                                assert response.body() != null;
                                cleanData.addAll(response.body().getResponse());
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(@NonNull Call<Dataset> call, @NonNull Throwable throwable) {
                                System.out.println(throwable);

                            }
                        });
                    }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadFav(){
        notifyDataSetChanged();
    }

    public String getFlagEmoji(String country){
        int flagOffset = 0x1F1E6;
        int asciiOffset = 0x41;
        int firstChar = Character.codePointAt(country, 0) - asciiOffset + flagOffset;
        int secondChar = Character.codePointAt(country, 1) - asciiOffset + flagOffset;
        return new String(Character.toChars(firstChar))
                + new String(Character.toChars(secondChar));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.flightNoString.setText(R.string.flight_no_string);
        holder.flightDepString.setText(R.string.departure_icao_string);
        holder.flightAirlineString.setText(R.string.airline_icao_string);
        holder.flightArrString.setText(R.string.arrival_icao_string);
        holder.flightAirframeString.setText(R.string.airplane_icao_string);
        holder.flightAltitudeString.setText(R.string.altitude_string);

        if(cleanData.isEmpty()){
            holder.flightDep.setText(R.string.alias_departure);
            holder.flightNo.setText(R.string.alias_flight);
            holder.flightArr.setText(R.string.alias_arrival);
            holder.flightAirline.setText(R.string.alias_airlines);
            holder.flightAirframe.setText(R.string.alias_plane);
            holder.flightAltitude.setText(R.string.alias_altitude);
        }else{
            holder.flightDep.setText(String.valueOf(cleanData.get(position).getDepIcao()));
            holder.flightNo.setText(String.valueOf(cleanData.get(position).getFlightIcao()));
            holder.flightArr.setText(String.valueOf(cleanData.get(position).getArrIcao()));
            holder.flightAirline.setText(cleanData.get(position).getAirlineIcao() + " " + getFlagEmoji(cleanData.get(position).getFlag()));
            holder.flightAirframe.setText(String.valueOf(cleanData.get(position).getAircraftIcao()));
            holder.flightAltitude.setText(String.valueOf(cleanData.get(position).getAlt()));
            String urlLogo = cleanData.get(position).getAirlineIata()+".png";
            Call<ResponseBody> callAsync = serviceLogo.getApiResponseLogo(urlLogo);
            callAsync.enqueue(new Callback<ResponseBody>() {
                                  @Override
                                  public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                      if (response.isSuccessful()) {
                                          if (response.body() != null) {
                                              // display the image data in a ImageView or save it
                                              Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                                              holder.airlineImg.setImageBitmap(bmp);
                                          }
                                      }
                                  }
                                  @Override
                                  public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                                  }
                              }
            );
        }

        holder.flightDep.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightDep").child((String) holder.flightDep.getText()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if(task.getResult().getValue() == null){
                        return;
                    }
                    HashMap<String,String> userDepFav;
                    userDepFav = (HashMap<String, String>) userFav.child("flightDep").getValue();
                    assert userDepFav != null;
                    userDepFav.remove((String) holder.flightDep.getText());
                    databaseReference.child("Fav").child(username).child("flightDep").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Departure removed " + holder.flightDep.getText(), Toast.LENGTH_LONG).show();
        });

        holder.flightNo.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightNo").child((String) holder.flightNo.getText()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if(task.getResult().getValue() == null){
                        return;
                    }
                    HashMap<String,String> userDepFav;
                    userDepFav = (HashMap<String, String>) userFav.child("flightNo").getValue();
                    assert userDepFav != null;
                    userDepFav.remove((String) holder.flightNo.getText());
                    databaseReference.child("Fav").child(username).child("flightNo").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Flight number removed " + holder.flightNo.getText(), Toast.LENGTH_LONG).show();

        });

        holder.flightArr.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightArr").child((String) holder.flightArr.getText()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if(task.getResult().getValue() == null){
                        return;
                    }
                    HashMap<String,String> userDepFav;
                    userDepFav = (HashMap<String, String>) userFav.child("flightArr").getValue();
                    assert userDepFav != null;
                    userDepFav.remove((String) holder.flightArr.getText());
                    databaseReference.child("Fav").child(username).child("flightArr").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Arrival removed " + holder.flightArr.getText(), Toast.LENGTH_LONG).show();
        });
        holder.flightAirline.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightAirline").child(cleanData.get(position).getAirlineIcao()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if(task.getResult().getValue() == null){
                        return;
                    }
                    HashMap<String,String> userDepFav;
                    userDepFav = (HashMap<String, String>) userFav.child("flightAirline").getValue();
                    assert userDepFav != null;
                    userDepFav.remove(cleanData.get(position).getAirlineIcao());
                    databaseReference.child("Fav").child(username).child("flightAirline").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite airline removed " + cleanData.get(position).getAirlineIcao(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        if(cleanData.isEmpty()){
            return 10;
        }else if(cleanData.size()>100){
            return 100;
        }
        return cleanData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView flightNo;
        public final TextView flightDep;
        public final TextView flightNoString;
        public final TextView flightDepString;
        public final TextView flightAirline;
        public final TextView flightAirlineString;
        public final TextView flightArr;
        public final TextView flightArrString;
        public final TextView flightAirframe;
        public final TextView flightAirframeString;
        public final TextView flightAltitude;
        public final TextView flightAltitudeString;
        public final ImageView airlineImg;

        public ViewHolder(View view) {
            super(view);
            flightNoString = view.findViewById(R.id.flightNo);
            flightDepString = view.findViewById(R.id.departureString);
            flightDep = view.findViewById(R.id.departureTime);
            flightNo = view.findViewById(R.id.flightNumber);
            flightArr = view.findViewById(R.id.arrivalTime);
            flightArrString = view.findViewById(R.id.arrivalTimeString);
            flightAirline = view.findViewById(R.id.airlineIcao);
            flightAirlineString = view.findViewById(R.id.airlineIcaoString);
            flightAirframe = view.findViewById(R.id.airframe);
            flightAirframeString = view.findViewById(R.id.airframeString);
            flightAltitude = view.findViewById(R.id.altitude);
            flightAltitudeString = view.findViewById(R.id.altitudeString);
            airlineImg = view.findViewById(R.id.imageViewAirline);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + flightNo.getText() + "'";
        }
    }
}
