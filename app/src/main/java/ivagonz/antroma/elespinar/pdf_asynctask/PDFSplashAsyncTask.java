package ivagonz.antroma.elespinar.pdf_asynctask;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

import ivagonz.antroma.elespinar.activities.NewsActivity;

/**
 * Created by ivgor on 21/03/2017.
 */

public class PDFSplashAsyncTask extends PDFAsyncTask {



    public PDFSplashAsyncTask(Context context) {
        super(context);
    }



    @Override
    public void setUpViewPager(ViewPager viewPager) {
        /*Do Nothing in this case*/
    }

    @Override
    public void setUpCancelViewPager(ViewPager viewPager) {

    }

    @Override
    protected void onPostExecute(ArrayList<File> files) {
        super.onPostExecute(files);
        startApp();

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        startApp();
    }

    private void startApp(){
        Intent intent = new Intent(getContext(), NewsActivity.class);
        getContext().startActivity(intent);
        ((AppCompatActivity)getContext()).finish();
    }
}
