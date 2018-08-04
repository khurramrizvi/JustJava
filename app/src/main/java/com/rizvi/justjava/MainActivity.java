package com.rizvi.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       int price = calculatePrice();
       String summary = createOrderSummary(price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,"khurramrizi72@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for"+name);
        intent.putExtra(Intent.EXTRA_TEXT,summary);
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
    }

    /**
     * This is the method for calculating price
     * @return It returns the total price of the coffee
     */

    private int calculatePrice() {
        return  numberOfCoffees *5;

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numberOfCoffees) {
        TextView quantityNo = (TextView) findViewById(R.id.quantity_no);
        quantityNo.setText("" + numberOfCoffees);
    }

        /**
         *
         * @param number It takes no. of coffees as input
         */


        private String createOrderSummary ( int number) {
            String priceMsg;

            EditText nameField = findViewById(R.id.name_text_view);
            name = nameField.getText().toString();

            CheckBox checkBox = findViewById(R.id.check_box1);
            Boolean isCheck=checkBox.isChecked();
            CheckBox checkBox1 = findViewById(R.id.check_box2);
            Boolean isCheck1 = checkBox1.isChecked();

            if (isCheck==true && isCheck1==true) {
                priceMsg = "Name: " +name+  "\nQuantity: " + numberOfCoffees + "\nWhipped Cream Added!\n"+"Chocolate Added! " + "\nTotal: " + NumberFormat.getCurrencyInstance().format(number+5) + "\nThank you!";
                TextView priceTextView = findViewById(R.id.price_text_view);
                priceTextView.setText(priceMsg);

            }
            else if(isCheck==false && isCheck1==true)
            {
                priceMsg = "Name: " +name+  "\nQuantity: " + numberOfCoffees +"\nChocolate Added! " + "\nTotal: " + NumberFormat.getCurrencyInstance().format(number+2) + "\nThank you!";
                TextView priceTextView = findViewById(R.id.price_text_view);
                priceTextView.setText(priceMsg);

            }

            else if(isCheck ==true && isCheck1==false)
            {
                priceMsg = "Name: " +name+  "\nQuantity: " + numberOfCoffees +"\nWhipped Cream Added! " + "\nTotal: " + NumberFormat.getCurrencyInstance().format(number+3) + "\nThank you!";
                TextView priceTextView = findViewById(R.id.price_text_view);
                priceTextView.setText(priceMsg);
            }

            else
            {
                priceMsg = "Name: " +name+  "\nQuantity: " + numberOfCoffees + "\nTotal: " + NumberFormat.getCurrencyInstance().format(number) + "\nThank you!";
                TextView priceTextView = findViewById(R.id.price_text_view);
                priceTextView.setText(priceMsg);
            }

            if (number==-1)
            {
                priceMsg = "Name:" +"\nQuantity: "+ "\nTotal: ";
                TextView priceTextView = findViewById(R.id.price_text_view);
                priceTextView.setText(priceMsg);
            }

                return priceMsg;
        }




    /**
     * It is called when minus btn is clicked
     */
    public void decrement(View view) {
        TextView quantityNo = findViewById(R.id.quantity_no);
            if (numberOfCoffees == 0) {
                Toast.makeText(getApplicationContext(), "Cannot go beyond 0", Toast.LENGTH_SHORT).show();
            } else {
                numberOfCoffees = numberOfCoffees - 1;
               quantityNo.setText("" + numberOfCoffees);
                createOrderSummary(calculatePrice());
            }
    }

    /**
     * It is called when plus btn is clicked
     * * @param view
     */
    public void increment(View view) {
        TextView quantityNo = findViewById(R.id.quantity_no);
        if(numberOfCoffees==100)
        {
            Toast.makeText(getApplicationContext(),"Maximum limit reached! :)",Toast.LENGTH_LONG).show();
        }
        else {
            numberOfCoffees++;
            quantityNo.setText("" + numberOfCoffees);
            createOrderSummary(calculatePrice());
        }
    }

    public void clear_all(View view){
        int number = -1;
       createOrderSummary(number);
    }



}
