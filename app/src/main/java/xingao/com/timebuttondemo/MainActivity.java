package xingao.com.timebuttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SecurityButton securityButton = (SecurityButton) findViewById(R.id.bnt_time);

        //这是直接调用方法
        /*securityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                securityButton.start();
            }
        });*/

        //这是回调使用方法
        securityButton.setStartTime(new SecurityButton.StartTimeLIstener() {
            @Override
            public void onStart() {
                Toast.makeText(getApplicationContext() , "start" , Toast.LENGTH_LONG).show();
            }
        });
    }
}
