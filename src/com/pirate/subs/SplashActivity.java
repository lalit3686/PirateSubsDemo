package com.pirate.subs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreen);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		new Thread(new Runnable() {

			public void run() {
				
				try {
					Thread.sleep(5000);
					Intent intent = new Intent(SplashActivity.this,Home.class);
					startActivity(intent);
					SplashActivity.this.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				
			}
			
		}).start();
	}
}
