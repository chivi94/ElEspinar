package ivagonz.antroma.elespinar.pdf_controller;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public abstract class PDFAsyncTaskController extends Thread implements Runnable {

    private AsyncTask<String, Void, ArrayList<File>> task;
    private Context context;
    private String[] files;

    public PDFAsyncTaskController(AsyncTask<String, Void, ArrayList<File>> task, Context context, String[] files) {
        this.task = task;
        this.context = context;
        this.files = files;
    }

    @Override
    public void run() {
        try {
            task.execute(files);
            task.get(6, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            this.taskTakeTooLong(true);
        } catch (ExecutionException e) {
            this.taskTakeTooLong(true);
        } catch (TimeoutException e) {
            this.taskTakeTooLong(true);
        } catch (CancellationException e) {
            taskCancelled();
        }
    }

    public abstract void taskCancelled();

    public abstract void taskTakeTooLong(boolean changeMessage);

    public abstract void cancelCheckTimeThread(boolean showMessageError);

    public AsyncTask<String, Void, ArrayList<File>> getTask() {
        return task;
    }

    public Context getContext() {
        return context;
    }

    public String[] getFiles() {
        return files;
    }
}
