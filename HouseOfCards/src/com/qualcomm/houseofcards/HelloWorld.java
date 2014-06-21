package com.qualcomm.houseofcards;

public class HelloWorld {

    public static void SayHello() {
        Context context   = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration      = Toast.LENGTH_SHORT;

        Toast toast       = Toast.makeText(context, text, duration);
        toast.show();
    }

} /* HelloWorld */
