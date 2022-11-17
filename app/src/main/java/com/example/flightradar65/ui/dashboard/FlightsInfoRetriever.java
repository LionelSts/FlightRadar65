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
import com.example.flightradar65.RetrofitClientLogo;
import com.example.flightradar65.data.Dataset;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsInfoRetriever extends RecyclerView.Adapter<FlightsInfoRetriever.ViewHolder> {
    private Dataset mDataset;
    private DataSnapshot airlinesLogos;
    DatabaseReference databaseReference;
    RetrofitAPI service = RetrofitClientLogo.createService(RetrofitAPI.class);
    String username = "augustinmariage@studentjuniacom";

    public FlightsInfoRetriever() {
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.flights_infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        databaseReference = FirebaseDatabase.getInstance("https://my-project-app-366214-default-rtdb.europe-west1.firebasedatabase.app/").getReference("/");
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        databaseReference.child("AirlinesLogos").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                airlinesLogos = task.getResult();
            }
        });
        return new ViewHolder(view);
    }

    public void loadDataset(Dataset dataset){
        this.mDataset = dataset;
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
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.flightNoString.setText(R.string.flight_no_string);
        holder.flightDepString.setText(R.string.departure_icao_string);
        holder.flightAirlineString.setText(R.string.airline_icao_string);
        holder.flightArrString.setText(R.string.arrival_icao_string);
        holder.flightAirframeString.setText(R.string.airplane_icao_string);
        holder.flightAltitudeString.setText(R.string.altitude_string);

        if(mDataset == null){
            holder.flightDep.setText(R.string.alias_departure);
            holder.flightNo.setText(R.string.alias_flight);
            holder.flightArr.setText(R.string.alias_arrival);
            holder.flightAirline.setText(R.string.alias_airlines);
            holder.flightAirframe.setText(R.string.alias_plane);
            holder.flightAltitude.setText(R.string.alias_altitude);
        }else{
            holder.flightDep.setText(String.valueOf(mDataset.getResponse().get(position).getDepIcao()));
            holder.flightNo.setText(String.valueOf(mDataset.getResponse().get(position).getFlightIcao()));
            holder.flightArr.setText(String.valueOf(mDataset.getResponse().get(position).getArrIcao()));
            holder.flightAirline.setText(mDataset.getResponse().get(position).getAirlineIcao() + " " + getFlagEmoji(mDataset.getResponse().get(position).getFlag()));
            holder.flightAirframe.setText(String.valueOf(mDataset.getResponse().get(position).getAircraftIcao()));
            holder.flightAltitude.setText(String.valueOf(mDataset.getResponse().get(position).getAlt()));
            String urlLogo = searchForLogo(mDataset.getResponse().get(position).getAirlineIata());
            Call<ResponseBody> callAsync = service.getApiResponseLogo(urlLogo);
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
            databaseReference.child("Fav").child(username).child("flightDep").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    HashMap<String,String> userDepFav = new HashMap<>();
                    if(task.getResult().getValue() != null){
                        userDepFav = (HashMap<String, String>) task.getResult().getValue();
                    }
                    userDepFav.put((String) holder.flightDep.getText(),(String) holder.flightDep.getText());
                    databaseReference.child("Fav").child(username).child("flightDep").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Departure added " + holder.flightDep.getText(), Toast.LENGTH_LONG).show();
        });

        holder.flightNo.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightNo").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    HashMap<String,String> userDepFav = new HashMap<>();
                    if(task.getResult().getValue() != null){
                        userDepFav = (HashMap<String, String>) task.getResult().getValue();
                    }
                    userDepFav.put((String) holder.flightNo.getText(),(String) holder.flightNo.getText());
                    databaseReference.child("Fav").child(username).child("flightNo").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Flight number added " + holder.flightNo.getText(), Toast.LENGTH_LONG).show();

        });

        holder.flightArr.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightArr").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    HashMap<String,String> userDepFav = new HashMap<>();
                    if(task.getResult().getValue() != null){
                        userDepFav = (HashMap<String, String>) task.getResult().getValue();
                    }
                    userDepFav.put((String) holder.flightArr.getText(),(String) holder.flightArr.getText());
                    databaseReference.child("Fav").child(username).child("flightArr").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite Arrival added " + holder.flightArr.getText(), Toast.LENGTH_LONG).show();
        });

        holder.flightAirline.setOnClickListener(view -> {
            databaseReference.child("Fav").child(username).child("flightAirline").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    HashMap<String,String> userDepFav = new HashMap<>();
                    if(task.getResult().getValue() != null){
                        userDepFav = (HashMap<String, String>) task.getResult().getValue();
                    }
                    userDepFav.put(mDataset.getResponse().get(position).getAirlineIcao(),mDataset.getResponse().get(position).getAirlineIcao());
                    databaseReference.child("Fav").child(username).child("flightAirline").setValue(userDepFav);
                }
            });
            Toast.makeText(holder.flightAirline.getContext(), "Favorite airline added " + mDataset.getResponse().get(position).getAirlineIcao(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        if(mDataset == null){
            return 10;
        }else if(mDataset.getResponse().size()>100){
            return 100;
        }
        return mDataset.getResponse().size();
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
    public String searchForLogo(String airline) {
        for (DataSnapshot airlinesLogo: airlinesLogos.getChildren()) {
            if(Objects.requireNonNull(airlinesLogo.child("id").getValue()).toString().equals(airline)){
                return Objects.requireNonNull(airlinesLogo.child("logo").getValue()).toString().replace("https://images.kiwi.com/", "");
            }
        }
        return "Couldn't find logo";
    }
}
