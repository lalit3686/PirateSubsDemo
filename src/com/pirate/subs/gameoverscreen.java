package com.pirate.subs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class gameoverscreen extends Activity implements OnClickListener
{
	
	private TextView lblBigDigitScore;
	private TextView lblSmallDigitScore;
	private TextView lblSubsKilled;
	private TextView lblTimePlayed;
	
	private ImageView ibtnGoAch;
	private ImageView ibtnGoBomb;
	private ImageView ibtnGoContinue;
	private ImageView ibtnGoNoBear;
	private ImageView ibtnGoMissile;
	
	private int score=0;
	private int subkilled=0;
	private String timePlayed=null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		setContentView(R.layout.gameoverscreen);
		
//		i.putExtra("currentscore", 10);
//		i.putExtra("subkilled", 5);
//		i.putExtra("timePlayed",1.15);
		
		score = this.getIntent().getExtras().getInt("currentscore");
		subkilled =this.getIntent().getExtras().getInt("subkilled");
		timePlayed =this.getIntent().getExtras().getString("timePlayed");
		initComponent();
		addListener();
		//DisplayScore();
		
		
	}
	private void initComponent()
	{
		
		
	 lblBigDigitScore =(TextView)findViewById(R.id.txtBigDigitScore);
	 lblSmallDigitScore =(TextView)findViewById(R.id.txtscore);
	 lblSubsKilled=(TextView)findViewById(R.id.txtsubkilled);
	 lblTimePlayed=(TextView)findViewById(R.id.txttimeplayed);
	 
	 ibtnGoAch =(ImageView)findViewById(R.id.ibtngoach);
	 ibtnGoBomb =(ImageView)findViewById(R.id.ibtngobomb);
	 ibtnGoContinue=(ImageView)findViewById(R.id.ibtncontinue);
	 ibtnGoNoBear=(ImageView)findViewById(R.id.ibtngonobear);
	 ibtnGoMissile=(ImageView)findViewById(R.id.ibtngomissile);
	 
	 lblBigDigitScore.setText(Integer.toString(score));
	 lblSubsKilled.setText(Integer.toString(subkilled));
	 lblTimePlayed.setText(timePlayed);
	}
	private void addListener()
	{
		ibtnGoAch.setOnClickListener(this);
		ibtnGoBomb.setOnClickListener(this);
		ibtnGoContinue.setOnClickListener(this);
		ibtnGoMissile.setOnClickListener(this);
		ibtnGoNoBear.setOnClickListener(this);
	}
	private void DisplayScore()
	{
//		 final User u = OpenFeint.getCurrentUser();
//		 
//		 if (u != null) {
//	        	u.load(new User.LoadCB() {
//					@Override public void onSuccess() {
						
//						Toast.makeText(gameoverscreen.this, "Player : "+u.name +"Score: "+u.gamerScore, Toast.LENGTH_LONG).show();
//						lblBigDigitScore.setText(u.gamerScore);
//						lblSmallDigitScore.setText(u.gamerScore);
						//userName.setText("Player Name : "+u.name +" "+u.userID());
						//tvScore.setText("Score is :"+u.gamerScore);
//						LastGamePlayedID.setText("Last  Played Game ID:"+u.lastPlayedGameId );
//						LastGamePlayedName.setText("Last  Played Game Name:"+u.lastPlayedGameId );
//						if(u.online==true)
//						{
//							tvOnlineStatus.setText("Online");
//						}
//						else
//						{
//							tvOnlineStatus.setText("OFFline");
//						}
//						//add by javid
//						if(u.online ==true)
//						{
//							tvOnlineStatus.setText("ONLINE");
//						}
//						else
//						{
//							tvOnlineStatus.setText("OFFLINE");
//						}
//						tvScore.setText("Score is ="+u.gamerScore);
//						LastGamePlayedID.setText("Last Game Play ID is :"+u.lastPlayedGameId);
//						LastGamePlayedName.setText("Last Played Game Name is"+u.lastPlayedGameName);
						  //u.gamerScore-gamesScore
//						u.gamerScore;
//						u.lastPlayedGameId//
//						u.lastPlayedGameName//
//						u.online//
//						
						
//					}
//					@Override public void onFailure(String errorMessage) {
//						Toast.makeText(gameoverscreen.this, errorMessage, Toast.LENGTH_SHORT).show();
//					}
//	        	});
//	        }
//	        else
	        {
	        	Toast.makeText(getBaseContext(), "No user currently logged in.",Toast.LENGTH_LONG).show();
	        }
	}
	public void onClick(View v) {
		if(v==ibtnGoAch)
		{
//			Toast.makeText(getBaseContext(), "Go Achievement Button Pressed", Toast.LENGTH_LONG).show();
		}
		if(v==ibtnGoBomb)
		{
			Toast.makeText(getBaseContext(), "Go Bomb Button Pressed", Toast.LENGTH_LONG).show();	
		}
		if(v==ibtnGoContinue)
		{
			Intent intent =new Intent(gameoverscreen.this,NewGameScreen.class);
			startActivity(intent);
			gameoverscreen.this.finish();
		}
		if(v==ibtnGoMissile)
		{
			Toast.makeText(getBaseContext(), "Go Missle Button Pressed", Toast.LENGTH_LONG).show();
		}
		if(v==ibtnGoNoBear)
		{
			Toast.makeText(getBaseContext(), "Go No Bear Button Pressed", Toast.LENGTH_LONG).show();	
		}
}
}
