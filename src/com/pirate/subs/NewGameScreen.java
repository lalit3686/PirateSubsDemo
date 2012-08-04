package com.pirate.subs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class NewGameScreen extends Activity implements OnClickListener{

	private ListView lstFriends;
	private ImageView btnMenu;
	private ImageView btnNewGame;
	private TextView txtDisplayerror;
	private static String adaptedStr[]=null;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.newgame);
    
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	lstFriends =(ListView)findViewById(R.id.lstfriendList);
    	btnMenu =(ImageView)findViewById(R.id.ibtnMenuButton);
    	btnNewGame =(ImageView)findViewById(R.id.ibtnNewGameButton);
    	txtDisplayerror =(TextView)findViewById(R.id.tvError);	
    	
    	btnMenu.setOnClickListener(this);
    	btnNewGame.setOnClickListener(this);
	}


	public void onClick(View v) {

		if(v == btnNewGame){
			
			Intent intent = new Intent(NewGameScreen.this,GameSelectionGallary.class);
			startActivity(intent);
			this.finish();
		}
		else if(v == btnMenu){
			
			Intent intent = new Intent(NewGameScreen.this,Home.class);
			startActivity(intent);
			this.finish();
		}
	}
}
