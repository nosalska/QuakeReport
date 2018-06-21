package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentItem = getItem(position);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        magnitudeTextView.setText(formatMagnitude(currentItem.getMagnitude()));

        String location = currentItem.getLocation();
        String primaryLocation;
        String locationOffset;
        int indexOfTheOf = location.indexOf(" of ");

        if (indexOfTheOf == -1){
            primaryLocation = location;
            locationOffset = "Near the";
        }
        else {
            primaryLocation = location.substring(indexOfTheOf + 4);
            locationOffset = location.substring(0,indexOfTheOf+4);
        }
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextView.setText(primaryLocation);

        TextView locationOffsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetTextView.setText(locationOffset);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentItem.getTime());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return listItemView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeColor;
        int magnitudeInt = (int) Math.floor(magnitude);
        switch(magnitudeInt) {
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;

        } return magnitudeColor;
    }
}
