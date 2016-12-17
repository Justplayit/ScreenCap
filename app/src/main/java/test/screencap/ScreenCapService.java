package test.screencap;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by free_ on 12/17/2016.
 */

public class ScreenCapService extends Service {
    private final IBinder binder =  new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder{
        ScreenCapService getService(){
            return ScreenCapService.this;
        }
    }

    protected void takeSnapshot(Activity activity) throws IOException {
        try {
            Date date = new Date();
            android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);

            String sdPath = Environment.getExternalStorageDirectory().toString();

            File sdDir = new File(sdPath + "/ScreenCap/");
            if (!sdDir.exists()) {
                sdDir.mkdir();
            }

            View view = activity.getWindow().getDecorView().getRootView();
            view.setDrawingCacheEnabled(true);

            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File image = new File(sdPath + "/ScreenCap/" + date + ".jpg");

            FileOutputStream outputStream = new FileOutputStream(image);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

            System.out.println("I took a snapshot... It felt great...");
        }
        catch (Exception exception){
            System.out.println("Exception occured in \"takeSnapshot\" > " +
                    exception);
        }
    }
}


