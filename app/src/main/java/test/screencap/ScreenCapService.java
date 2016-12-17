package test.screencap;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by free_ on 12/17/2016.
 */

public class ScreenCapService extends IntentService {

    public ScreenCapService(){
        this(ScreenCapService.class.getName());
    }

    public ScreenCapService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("Startig ScreenCap service...");



        showToast("Shutting down ScreenCap service...");
    }

    private void showToast(final String text){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
