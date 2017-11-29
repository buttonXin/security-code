package xingao.com.timebuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private VerificationCodeButton mVerificationCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVerificationCodeButton = (VerificationCodeButton) findViewById(R.id.bnt_time);


//        verificationCodeButton.setSumTime(60 , 5);

        mVerificationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "oooo", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerificationCodeButton.start();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVerificationCodeButton.stop();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mVerificationCodeButton.stop();
    }
}
