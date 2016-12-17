package test.screencap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent key){
        int action = key.getAction();
        int keyCode = key.getKeyCode();

        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action == KeyEvent.ACTION_UP){
                    System.out.println("Pressed VOLUME UP");
                    Intent service = new Intent(getApplicationContext(), ScreenCapService.class);
                    this.startService(service);

                }
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(action == KeyEvent.ACTION_DOWN) {
                    System.out.println("Pressed VOLUME DOWN");
                    Intent service = new Intent(getApplicationContext(), ScreenCapService.class);
                    this.startService(service);

                }
                break;
            default:
                return super.dispatchKeyEvent(key);
        }
        return super.dispatchKeyEvent(key);
    }


}
