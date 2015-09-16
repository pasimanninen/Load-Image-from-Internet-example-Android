package fi.ptm.loadimagefrominternet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author PTM
 */
public class MainActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // draw layout
        setContentView(R.layout.activity_main);
        // get image view
        imageView = (ImageView) findViewById(R.id.imageView);
        // create a new AsyncTask object
        DownloadImageTask task = new DownloadImageTask();
        // ulr to load data
        String url = "http://student.labranet.jamk.fi/~mapas/android/android.png";
        // start asynctask
        task.execute(url);
    }

    // asynctask class
    private class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {

        // this is done in UI thread
        @Override
        protected void onPreExecute() { }

        // this is background thread
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL imageUrl;
            Bitmap bitmap = null;
            try {
                imageUrl = new URL(urls[0]);
                InputStream in = imageUrl.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("<<LOADIMAGE>>", e.getMessage());
            }
            return bitmap;
        }

        // this is done in UI thread
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // draw image
            imageView.setImageBitmap(bitmap);
        }

        // no onProgressUpdate(...) method implemented -> no need now
    }

}
