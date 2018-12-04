package ivagonz.antroma.elespinar.pdf_controller;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ivagonz.antroma.elespinar.R;

/**
 * Created by Ivan on 18/04/2017.
 */

public class PDFMainController extends PDFAsyncTaskController {

    public PDFMainController(AsyncTask<String, Void, ArrayList<File>> task, Context context, String[] files) {
        super(task, context, files);
    }

    @Override
    public void taskCancelled() {
        getTask().cancel(true);
    }

    @Override
    public void taskTakeTooLong(boolean changeMessage) {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), R.string.dialog_long, Toast.LENGTH_SHORT).show();
            }
        });
        try {
            getTask().get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            this.cancelCheckTimeThread(true);
        } catch (ExecutionException e) {
            this.cancelCheckTimeThread(true);
        } catch (TimeoutException e) {
            this.cancelCheckTimeThread(true);
        } catch (CancellationException e) {
            taskCancelled();
        }
    }

    @Override
    public void cancelCheckTimeThread(boolean showMessageError) {
        getTask().cancel(true);

    }
}
