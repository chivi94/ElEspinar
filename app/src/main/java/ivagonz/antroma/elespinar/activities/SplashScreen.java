package ivagonz.antroma.elespinar.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;
import ivagonz.antroma.elespinar.pdf_controller.PDFMainController;
import ivagonz.antroma.elespinar.pdf_asynctask.PDFSplashAsyncTask;

/**
 * Created by Ivan on 05/04/2017.
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_splash_screen);
        getWindow().setStatusBarColor(R.color.ig_color);
        PDFSplashAsyncTask pdfAsyncTask = new PDFSplashAsyncTask(this);
        PDFMainController pdfMainController = new PDFMainController(pdfAsyncTask, this, new String[]{Constants.getAtpSingleUrl(), Constants.getAtpDoubleUrl(), Constants.getAtpPreviousUrl(), Constants.getAtpGameOrderUrl(),
                Constants.getItfSingleUrl(), Constants.getItfDoubleUrl(), Constants.getItfPreviousUrl(), Constants.getItfGameOrderUrl()});
        pdfMainController.start();

    }

    @Override
    public void onBackPressed() {
        /*Do nothing*/
    }
}
