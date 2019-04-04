package com.example.bkgut;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Home extends Fragment {

    String OOF = "https://www.bkgut.de";
    TextView tv0, tv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.content_home, container,false);

        ViewFlipper vf = (ViewFlipper) vw.findViewById(R.id.slideShow);

        final Animation fade_in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),android.R.anim.fade_in);
        final Animation fade_out = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),android.R.anim.fade_out);

        final Animation inLeft = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),android.R.anim.slide_in_left);
        final Animation outRight = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),android.R.anim.slide_out_right);

        vf.setInAnimation(fade_in);
        vf.setOutAnimation(fade_out);

        vf.setAutoStart(true);
        vf.setFlipInterval(5000);
        vf.startFlipping();

        tv0 = vw.findViewById(R.id.txtView0);
        tv1 = vw.findViewById(R.id.txtView1);


        CardView cv1 = vw.findViewById(R.id.cv1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv0.startAnimation(inLeft);
                tv0.setText("Schüler Online");
                tv1.startAnimation(inLeft);
                tv1.setText(R.string.home_describ);


            }
        });

        CardView cv2 = vw.findViewById(R.id.cv2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv0.startAnimation(inLeft);
                tv0.setText("Karriere");
                tv1.startAnimation(inLeft);
                tv1.setText(R.string.home_describ);


            }
        });

        CardView cv3 = vw.findViewById(R.id.cv3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv0.startAnimation(inLeft);
                tv0.setText("Termine");
                tv1.startAnimation(inLeft);
                tv1.setText(R.string.home_describ);


            }
        });

        CardView cv4 = vw.findViewById(R.id.cv4);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv0.startAnimation(inLeft);
                tv0.setText("Kontakte");
                tv1.startAnimation(inLeft);
                tv1.setText(R.string.home_describ);


            }
        });



        /*WebView weeb = (WebView) vw.findViewById(R.id.webkek);

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
        weeb.loadUrl(OOF);*/

        return vw;
    }
}
