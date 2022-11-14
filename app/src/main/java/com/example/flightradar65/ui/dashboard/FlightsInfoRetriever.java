package com.example.flightradar65.ui.dashboard;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightradar65.R;
import com.example.flightradar65.data.Dataset;

public class FlightsInfoRetriever extends RecyclerView.Adapter<FlightsInfoRetriever.ViewHolder> {
    private Dataset mDataset;
    public FlightsInfoRetriever() {
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
        }

        holder.flightDep.setOnClickListener(view -> Toast.makeText(holder.flightDep.getContext(), "clicked on " +holder.flightDep.getText(), Toast.LENGTH_LONG).show());

        holder.flightNo.setOnClickListener(view -> Toast.makeText(holder.flightNo.getContext(), "clicked on " +holder.flightNo.getText(), Toast.LENGTH_LONG).show());

        holder.flightArr.setOnClickListener(view -> Toast.makeText(holder.flightArr.getContext(), "clicked on " +holder.flightArr.getText(), Toast.LENGTH_LONG).show());

        holder.flightAirline.setOnClickListener(view -> Toast.makeText(holder.flightAirline.getContext(), "clicked on " +mDataset.getResponse().get(position).getAirlineIcao() , Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        if(mDataset == null){
            return 10;
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

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + flightNo.getText() + "'";
        }
    }
}
