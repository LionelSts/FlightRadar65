package com.example.flightradar65.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightradar65.R;

import java.util.Random;

public class FlightsInfoRetriever extends RecyclerView.Adapter<FlightsInfoRetriever.ViewHolder> {
    private Random random;
    public FlightsInfoRetriever(int seed, Context context) {
        this.random = new Random(seed);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.flights_infos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.flightNoString.setText(R.string.flight_no_string);
        holder.flightDepString.setText(R.string.departure_icao_string);
        holder.flightAirlineString.setText(R.string.airline_icao_string);
        holder.flightArrString.setText(R.string.arrival_icao_string);
        holder.flightAirframeString.setText(R.string.airplane_icao_string);
        holder.flightAltitudeString.setText(R.string.altitude_string);
        /*
        holder.flightDep.setText(String.valueOf(mDataset.getResponse().get(position).getDepIcao()));
        holder.flightNo.setText(String.valueOf(mDataset.getResponse().get(position).getFlightIcao()));
        holder.flightArr.setText(String.valueOf(mDataset.getResponse().get(position).getArrIcao()));
        holder.flightAirline.setText(String.valueOf(mDataset.getResponse().get(position).getAirlineIcao()));
        holder.flightAirframe.setText(String.valueOf(mDataset.getResponse().get(position).getAircraftIcao()));
        holder.flightAltitude.setText(String.valueOf(mDataset.getResponse().get(position).getAlt()));*/
        holder.flightDep.setText(String.valueOf(random.nextInt()));
        holder.flightNo.setText(String.valueOf(random.nextInt()));
        holder.flightArr.setText(String.valueOf(random.nextInt()));
        holder.flightAirline.setText(String.valueOf(random.nextInt()));
        holder.flightAirframe.setText(String.valueOf(random.nextInt()));
        holder.flightAltitude.setText(String.valueOf(random.nextInt()));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }

        @Override
        public String toString() {
            return super.toString() + " '" + flightNo.getText() + "'";
        }
    }
}
