package ivagonz.antroma.elespinar.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import ivagonz.antroma.elespinar.R;


public class NewsFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private WebView webView;
    private String url;


    public NewsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_news_tournament_fragment, container, false);
        webView = (WebView) v.findViewById(R.id.webView);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_tournament);
        Bundle args = getArguments();
        url = args.getString("url");
        if (url != null) {
            setWebViewSettings();
            setSwipeRefreshLayoutSettings();
        }
        return v;
    }

    public void setWebViewSettings(){
        //Disable webview zoom
        getWebView().setVisibility(View.INVISIBLE);
        getWebView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return (motionEvent.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        getWebView().getSettings().setJavaScriptEnabled(true);
        getWebView().setInitialScale(125);
        getWebView().loadUrl(url);
        getWebView().setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                for (int a = 0; a < 14; a++) {
                    view.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('div')["+ a + "].style.display='none'; " +
                            "})()");
                }
                for (int a = 21; a < 68; a++) {
                    view.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('div')["+ a + "].style.display='none'; " +
                            "})()");
                }
                webView.setVisibility(View.VISIBLE);
                if (getSwipeRefreshLayout().isRefreshing()) {
                    getSwipeRefreshLayout().setRefreshing(false);
                }
            }

        });
    }

    public void setSwipeRefreshLayoutSettings(){
        getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                getWebView().reload();
            }
        });
        getSwipeRefreshLayout().setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }


    public WebView getWebView(){
        return webView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout(){
        return swipeRefreshLayout;
    }
}
