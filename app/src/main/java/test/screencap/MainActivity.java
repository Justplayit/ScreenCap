package test.screencap;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ScreenCapService screenCap;
    boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
        catch (Exception exception){
            System.out.println("Exception occurred in \"MainActivity - onCreate()\" > " +
                exception);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                ScreenCapService.LocalBinder binder = (ScreenCapService.LocalBinder) service;
                screenCap = binder.getService();
                bound = true;
            }
            catch (Exception exception){
                System.out.println("Error occurred in \"MainActivity - onServiceConnected()\" > " +
                    exception);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onStart(){
        try {
            super.onStart();

            Intent intent = new Intent(this, ScreenCapService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
        catch (Exception exception){
            System.out.println("Error occurred in \"MainActivity - onStart()\" > " +
                exception);
        }
    }

    @Override
    protected void onStop(){
        try {
            super.onStop();
            if (bound) {
                unbindService(connection);
                bound = false;
            }
        }
        catch(Exception exception){
            System.out.println("Error occurred in \"MainActivity - onStop()\" > " +
                exception);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent key){
        try {
            int action = key.getAction();
            int keyCode = key.getKeyCode();

            switch (keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    if (action == KeyEvent.ACTION_UP) {
                        System.out.println("Pressed VOLUME UP");
                        if (bound) {
                            try {
                                screenCap.takeSnapshot(this);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;

                default:
                    return false;
            }
        }
        catch (Exception exception){
            System.out.println("Error occurred in \"MainActivity - dispatchKeyEvent()\" > " +
                exception);
        }

        return false;
    }


}
