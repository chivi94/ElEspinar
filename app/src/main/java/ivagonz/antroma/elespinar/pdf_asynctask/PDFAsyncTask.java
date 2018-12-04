package ivagonz.antroma.elespinar.pdf_asynctask;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;

/**
 * Created by ivgor on 21/03/2017.
 */

public abstract class PDFAsyncTask extends AsyncTask<String, Void, ArrayList<File>> {




    private Context context;
    private ArrayList<File> files;

    public PDFAsyncTask(Context context) {
        this.context = context;

    }


    @Override
    protected ArrayList<File> doInBackground(String... strings) {
        // Inflate the layout for this fragment
        URL u = null;
        HttpURLConnection c;
        FileOutputStream f;
        File file = null;
        InputStream in;
        files = Constants.getFiles();
        try {
            for (int i = 0; i < strings.length; i++) {
                u = new URL(strings[i]);
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();
                file = new File(context.getCacheDir(), "archivo" + i + ".txt");
                f = new FileOutputStream(file);
                in = c.getInputStream();
                byte[] buffer = new byte[2048];
                int len1 = 0;
                while ((len1 = in.read(buffer)) > 0) {
                    f.write(buffer, 0, len1);
                }
                f.close();
                if (file != null) {
                    files.add(file);
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(context, context.getString(R.string.toast_download_cancel), Toast.LENGTH_SHORT).show();
    }

    public abstract void setUpViewPager(ViewPager viewPager);

    public abstract void setUpCancelViewPager(ViewPager viewPager);

    public Context getContext() {
        return context;
    }
}
