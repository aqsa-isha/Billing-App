package com.example.billingapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    EditText board_price, sensor_price, bluetooth_price, wifi_price;
    Spinner board_qty, sensor_qty, bluetooth_qty, wifi_qty, discount_rate;
    TextView total_bill, discounted, new_bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Arduino Project (20SW14)");
        // Initialling Spinners
        board_qty = findViewById(R.id.AB_Qty_input);
        sensor_qty = findViewById(R.id.LS_Qty_input);
        bluetooth_qty = findViewById(R.id.B_Qty_input);
        wifi_qty = findViewById(R.id.W_Qty_input);
        discount_rate = findViewById(R.id.Discount_input);
        // Initializing Edit Text Views to get input values
        board_price = findViewById(R.id.AB_Price_Input);
        sensor_price = findViewById(R.id.LS_Price_Input);
        bluetooth_price = findViewById(R.id.B_Price_Input);
        wifi_price = findViewById(R.id.W_Price_Input);
        // Initializing TextView for output
        total_bill = findViewById(R.id.total_bill_output);
        new_bill =  findViewById(R.id.new_bill_output);
        discounted = findViewById(R.id.total_discount_output);
        // Setting up Spinners Options for Quantity and Discount boxes
        String[] Qty_Options = {"1","2","3","4","5","6","7","8","9","10"};
        String[] Discount = {"0","5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100"};
        // For Quantities of Items
        ArrayAdapter<String> quantity_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Qty_Options);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        board_qty.setAdapter(quantity_adapter);
        sensor_qty.setAdapter(quantity_adapter);
        bluetooth_qty.setAdapter(quantity_adapter);
        wifi_qty.setAdapter(quantity_adapter);
        // For Discount Rates of the bill
        ArrayAdapter<String> discount_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Discount);
        discount_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discount_rate.setAdapter(discount_adapter);
    }
    public void Calculate_Bill(View v)
    {
        double cal_bill = 0, discount_bill = 0;
        try {
            if(board_price.getText().toString().equals(""))
                board_price.setText("0");
            if (sensor_price.getText().toString().equals(""))
                sensor_price.setText("0");
            if (bluetooth_price.getText().toString().equals(""))
                bluetooth_price.setText("0");
            if(wifi_price.getText().toString().equals(""))
                wifi_price.setText("0");
            cal_bill += Double.parseDouble(board_qty.getSelectedItem().toString()) * Double.parseDouble(board_price.getText().toString());
            cal_bill += Double.parseDouble(sensor_qty.getSelectedItem().toString()) * Double.parseDouble(sensor_price.getText().toString());
            cal_bill += Double.parseDouble(bluetooth_qty.getSelectedItem().toString()) * Double.parseDouble(bluetooth_price.getText().toString());
            cal_bill += Double.parseDouble(wifi_qty.getSelectedItem().toString()) * Double.parseDouble(wifi_price.getText().toString());
            if(discount_rate.getSelectedItem().toString().equals("0"))
                discount_bill = cal_bill;
            else
                discount_bill = cal_bill - (cal_bill * (Double.parseDouble(discount_rate.getSelectedItem().toString()) / 100));
        }
        catch (Exception ex)
        {
            Log.e("Calculation Error" , ex.getMessage());
        }
        total_bill.setText(String.valueOf("RS: "+cal_bill));
        discounted.setText(discount_rate.getSelectedItem().toString()+"%");
        new_bill.setText(String.valueOf("RS: "+discount_bill));
    }
}


