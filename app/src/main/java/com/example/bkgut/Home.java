package com.example.bkgut;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Home extends Fragment {

    String OOF = "https://www.bkgut.de";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.content_home, container,false);

        WebView weeb = (WebView) vw.findViewById(R.id.webkek);

        WebSettings webs = weeb.getSettings();
        webs.setJavaScriptEnabled(true);

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(true);

        weeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pd.setTitle("Loading...");
                pd.show();
                pd.setProgress(0);
                getActivity().setProgress(newProgress * 100);
                if (newProgress == 100 && pd.isShowing()){
                    pd.dismiss();
                }
            }
        });
        weeb.setWebViewClient(new WebViewClient());
        weeb.loadUrl(OOF);

        return vw;
    }
}
