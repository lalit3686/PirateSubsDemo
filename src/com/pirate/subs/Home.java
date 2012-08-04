package com.pirate.subs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Home extends Activity implements OnClickListener{

	public static MediaPlayer mediaPlayer;
	ImageView mImageView_New_Game,ibtnLeaderboard,ibtnAchievement,imgOptions,imgOpenFient;
	ImageView spin_img;
	AnimationDrawable mAnimationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mediaPlayer = MediaPlayer.create(this, R.raw.impact1);
		try {
			mediaPlayer.start();
			mediaPlayer.setLooping(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mImageView_New_Game = (ImageView) findViewById(R.id.Img_new_game);
		mImageView_New_Game.setOnClickListener(this);
		mAnimationDrawable = (AnimationDrawable) mImageView_New_Game.getBackground();
		
		new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(1000);
					mAnimationDrawable.start();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		spin_img = (ImageView) findViewById(R.id.spin);
		RotateAnimation mRotateAnimation = new RotateAnimation(0, 360,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setInterpolator(new LinearInterpolator());
		mRotateAnimation.setRepeatCount(Animation.INFINITE);
		mRotateAnimation.setDuration(20000);
		spin_img.setBackgroundColor(Color.TRANSPARENT);
		spin_img.setAnimation(mRotateAnimation);
		
		imgOptions = (ImageView) findViewById(R.id.imgOptions);
		
		ibtnLeaderboard =(ImageView)findViewById(R.id.imgleader);
		ibtnAchievement=(ImageView)findViewById(R.id.imgAward);
		imgOpenFient = (ImageView) findViewById(R.id.ibtnOpenFeintScreen1);

		imgOptions.setOnClickListener(this);
		
		ibtnLeaderboard.setOnClickListener(this);
		ibtnAchievement.setOnClickListener(this);
		imgOpenFient.setOnClickListener(this);
	}
	
//	@Override
//	public void onBackPressed() {
//		super.onBackPressed();
//		if(mediaPlayer.isPlaying()){
//			mediaPlayer.stop();			
//		}
//	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();			
		}
	}

	public void onClick(View v) {
	       
			if(v == mImageView_New_Game){
				
				if(mediaPlayer.isPlaying()){
					mediaPlayer.stop();
					Log.i("Media Player", "stopped#####################################");
				}
				System.gc();
				Intent intent = new Intent(Home.this,GameSelectionGallary.class);
				startActivity(intent);
				this.finish();
			}
		}
}


