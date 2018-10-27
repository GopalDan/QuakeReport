package com.example.gopal.quakerepo;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gopal on 10/24/2018.
 */

public class CustomArrayAdapter extends ArrayAdapter<Earthquake> {
    public CustomArrayAdapter(Activity context, List<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentEarthQuake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitudeView.setText( ""+ currentEarthQuake.getMagnitude());

        // Breaking Location into two part & showing it separately
        String location = currentEarthQuake.getLocation();
        String[] locationArray = location.split(",");
        TextView offsetText = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetText.setText(locationArray[0]);
        TextView primaryText = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryText.setText(locationArray[1]);


        Date dateObject = new Date(currentEarthQuake.getTime());
        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


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
    /*
     Returns the background color for magnitude of earthquake
     */
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int intMagnitude = (int) magnitude;
        switch(intMagnitude){
            case 0:
            case 1: magnitudeColorResourceId = R.color.magnitude1;break;
            case 2: magnitudeColorResourceId = R.color.magnitude2;break;

            case 3: magnitudeColorResourceId = R.color.magnitude3;break;
            case 4: magnitudeColorResourceId = R.color.magnitude4;break;
            case 5: magnitudeColorResourceId = R.color.magnitude5;break;
            case 6: magnitudeColorResourceId = R.color.magnitude6;break;
            case 7: magnitudeColorResourceId = R.color.magnitude7;break;
            case 8: magnitudeColorResourceId = R.color.magnitude8;break;
            case 9: magnitudeColorResourceId = R.color.magnitude9;break;
            default:
              magnitudeColorResourceId = R.color.magnitude10plus;break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);

    }
}
