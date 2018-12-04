package ivagonz.antroma.elespinar.pdf_controller;

import android.app.Activity;
import android.app.ProgressDialog;
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

public class PDFDialogController extends PDFAsyncTaskController {


    private ProgressDialog progressDialog;

    public PDFDialogController(AsyncTask<String, Void, ArrayList<File>> task, Context context, String[] files, ProgressDialog progressDialog) {
        super(task, context, files);
        this.progressDialog = progressDialog;
    }

    public void taskCancelled() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
        getTask().cancel(true);
    }

    public void taskTakeTooLong(boolean changeMessage) {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    if (changeMessage) {
                        progressDialog.setMessage(getContext().getString(R.string.dialog_long));
                    }
                }
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

    public void cancelCheckTimeThread(boolean showMessageError) {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
        getTask().cancel(true);
    }
}
