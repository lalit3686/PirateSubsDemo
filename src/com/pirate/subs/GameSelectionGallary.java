package com.pirate.subs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class GameSelectionGallary extends Activity implements OnClickListener,OnItemSelectedListener, mygallery.myCallBack//MyGestureListener.onFlingInterface
{
    /** Called when the activity is first created. */
	List<PhotoGridGetterSetter> list = new ArrayList<PhotoGridGetterSetter>();
	//Gallery mGallery;
	public mygallery mGallery;
	private ImageView btnPlaynow;
	private ImageView ibtnMenu;
	private ImageView ibtnTitle;
	 int galleryPossition;

	private ImageView imgbtnprev,imgbtnnext;

	Handler myHandler = new Handler();
    int i=0;
    Bitmap bitmapOrg=null;
    
    //code for animation
	private ImageView imagebarrel,imagebird,imagebird1,imagedolphin,imagemine,imagelifeboat,imagetreasurechute;

//	protected MyGestureListener myGestureListener;

	
    Thread animThread1,animThread2;
  
    private  FrameLayout fLayout1,fLayout2,fLayout3,fLayout4,fLayout5,fLayout6,fLayout7;
    int animFlag=0;
	@Override
   public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
       
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.gameselectiongallery);
    
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
    	ibtnTitle =(ImageView)findViewById(R.id.ibtntitle);
		btnPlaynow =(ImageView)findViewById(R.id.btnPlayNow);
        btnPlaynow.setOnClickListener(this);
       
        // mGallery =(Gallery)findViewById(R.id.examplegallery);
        mGallery =(mygallery)findViewById(R.id.examplegallery);
        mGallery.setCallbackDuringFling(false); 
        
     	Photo_Gallery_Adapter adapter = new Photo_Gallery_Adapter(this, getMyList());
		mGallery.setAdapter(adapter);
		
		mGallery.setOnItemSelectedListener(this);
     
		bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.stagetitle_amazon);
		
		ibtnMenu =(ImageView)findViewById(R.id.ibtnmenu);
		ibtnMenu.setOnClickListener(this);
	
		ibtnTitle.setOnClickListener(this);
	
//		myGestureListener = new MyGestureListener(this);
//		mGallery.setOnTouchListener(myGestureListener);
		
		imgbtnprev = (ImageView)findViewById(R.id.btnPrevious);
		imgbtnnext = (ImageView)findViewById(R.id.btnNext);
		imgbtnnext.setOnClickListener(this);
		imgbtnprev.setOnClickListener(this);
		initComponent();
		
	//	startAnimationLayout1();
		
		myHandler.post(runs);
	    animFlag=1;
		
	}
	protected void initComponent()
	{
		fLayout1 =(FrameLayout)findViewById(R.id.animLayout1);
		fLayout2 =(FrameLayout)findViewById(R.id.animLayout2);
		fLayout3 =(FrameLayout)findViewById(R.id.animLayout3);
		fLayout4 =(FrameLayout)findViewById(R.id.animLayout4);
		fLayout5 =(FrameLayout)findViewById(R.id.animLayout5);
		fLayout6 =(FrameLayout)findViewById(R.id.animLayout6);
		fLayout7 =(FrameLayout)findViewById(R.id.animLayout7);
		
		fLayout1.setVisibility(View.VISIBLE);
		fLayout2.setVisibility(View.VISIBLE);
		fLayout3.setVisibility(View.GONE);
		fLayout4.setVisibility(View.GONE);
		fLayout5.setVisibility(View.GONE);
		fLayout6.setVisibility(View.GONE);
		fLayout7.setVisibility(View.GONE);
	}
	 
	private List<PhotoGridGetterSetter> getMyList()
	{
		list.add(new PhotoGridGetterSetter());
		list.get(0).setmBitmap(R.drawable.stageselect_amazon);
	
		list.add(new PhotoGridGetterSetter());
		list.get(1).setmBitmap(R.drawable.stageselect_arctic);

//		list.add(new PhotoGridGetterSetter());
//		list.get(2).setmBitmap(com.sample.Gallry.R.drawable.stageselect_cave);
	

		list.add(new PhotoGridGetterSetter());
		list.get(2).setmBitmap(R.drawable.stageselect_industrial);
//		list.add(new PhotoGridGetterSetter());
//		
////		list.get(4).setmBitmap(com.sample.Gallry.R.drawable.stageselect_random);

		list.add(new PhotoGridGetterSetter());
		list.get(3).setmBitmap(R.drawable.stageselect_tropical);
//		list.add(new PhotoGridGetterSetter());
//		list.get(6).setmBitmap(com.sample.Gallry.R.drawable.stageselect_volcano);
		return list;
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	public void make(float x ){
	
		int w = bitmapOrg.getWidth();
		int h = bitmapOrg.getHeight();
			// Setting post rotate to 90
		Matrix mtx = new Matrix();
		mtx.postRotate(x);
			// Rotating Bitmap
		Bitmap rotatedBMP = Bitmap.createBitmap(bitmapOrg, 0, 0, w, h, mtx, true);
		BitmapDrawable bmd1 = new BitmapDrawable(rotatedBMP);

		ibtnTitle.setImageDrawable(bmd1);	

	    }

	 Runnable runs = new Runnable() {
			public void run() {
			
				System.out.println("i="+i);
				if(i==0)
				{
					make(0);
					i++;
				}
				else if( i==1)
				{
					
					make(15);
					i++;
				}
				else if(i==2)
				{
					
					make(0);
					i++;
				}
				else if(i==3)
				{
					
					make(-15);
					i=0;
				}
				myHandler.removeCallbacks(runs);
				myHandler.postDelayed(runs,500);
			}
		};
//  
	
	@Override
    public void onDestroy()
    {
		myHandler.removeCallbacks(runs);
        super.onDestroy();
//        if(animThread1.isAlive()==true)
//        {
//        	//animThread1.destroy();
//        }
//        if(animThread2.isAlive()==true)
//        {
//        //	animThread2.destroy();
//        }
//        
    }

	protected void visibility(int index)
	{
		switch(index)
		{
		
		case 1:
					fLayout1.setVisibility(View.GONE);
					fLayout2.setVisibility(View.GONE);
					fLayout3.setVisibility(View.VISIBLE);
					fLayout4.setVisibility(View.GONE);
					fLayout5.setVisibility(View.GONE);
					fLayout6.setVisibility(View.GONE);
					fLayout7.setVisibility(View.GONE);
//					stopAnimationLayout1();
					startAnimationLayout2();
					break;
		case 2:
					
			fLayout1.setVisibility(View.GONE);
			fLayout2.setVisibility(View.GONE);
			fLayout3.setVisibility(View.GONE);
			fLayout4.setVisibility(View.VISIBLE);
			fLayout5.setVisibility(View.VISIBLE);
			fLayout6.setVisibility(View.GONE);
			fLayout7.setVisibility(View.GONE);
				
				startAnimationLayout3();
				break;
		case 3:
					fLayout1.setVisibility(View.GONE);
					fLayout2.setVisibility(View.GONE);
					fLayout3.setVisibility(View.GONE);
					fLayout4.setVisibility(View.GONE);
					fLayout5.setVisibility(View.GONE);
					fLayout6.setVisibility(View.VISIBLE);
					fLayout7.setVisibility(View.VISIBLE);
					
					startAnimationLayout4();
					break;
		default: 
				
				fLayout1.setVisibility(View.VISIBLE);
				fLayout2.setVisibility(View.VISIBLE);
				fLayout3.setVisibility(View.GONE);
				fLayout4.setVisibility(View.GONE);
				fLayout5.setVisibility(View.GONE);
				fLayout6.setVisibility(View.GONE);
				fLayout7.setVisibility(View.GONE);
				startAnimationLayout1();
					break;
			
		}
		
	}
	public void hideAll()
	{
		System.out.println("//////////////Gallery Possition"+galleryPossition); 
		switch (galleryPossition) {
		case 1:
		
			if(fLayout3.getVisibility() ==View.VISIBLE && fLayout3 !=null)
			{	
				fLayout3.setVisibility(View.GONE);	
			}
			break;
		case 2:
			
			if(fLayout4.getVisibility() ==View.VISIBLE && fLayout4 !=null )
			{	fLayout4.setVisibility(View.GONE);	}
			if(fLayout5.getVisibility() ==View.VISIBLE && fLayout5 !=null)
			{	fLayout5.setVisibility(View.GONE);	}
		case 3:
			
			if(fLayout6.getVisibility() ==View.VISIBLE && fLayout6 !=null)
			{	fLayout6.setVisibility(View.GONE);	}
			if(fLayout7.getVisibility() ==View.VISIBLE && fLayout7 !=null)
			{	fLayout7.setVisibility(View.GONE);	}
		default:
			
			if(fLayout1.getVisibility() ==View.VISIBLE && fLayout1 !=null)
			{	fLayout1.setVisibility(View.GONE);	}
			if(fLayout2.getVisibility() ==View.VISIBLE && fLayout2 !=null)
			{	fLayout2.setVisibility(View.GONE);	}
	
			break;
		}
	}
	
	public void startAnimationLayout1()
	{
		System.out.println("Animation Start1");
		
		imagebird =(ImageView)findViewById(R.id.ibtnbird);
		imagebird.setBackgroundResource(R.drawable.animbird);
		final AnimationDrawable  mAnimationDrawable1 =(AnimationDrawable) imagebird.getBackground();
		
	     animThread1= new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable1.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
				});
	     animThread1.start();
	     
	    	
	    System.out.println("Animation Start2");	
	    imagelifeboat =(ImageView)findViewById(R.id.ibtnlifeboat);
		imagelifeboat.setBackgroundResource(R.drawable.animlifeboat);		
		final AnimationDrawable  mAnimationDrawable2 =(AnimationDrawable) imagelifeboat.getBackground();
		animThread2=	new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable2.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
					});
		
		animThread2.start();
						
	}

	public void startAnimationLayout2()
	{

			 imagebird1 =(ImageView)findViewById(R.id.ibtnbird1);
			 imagebird1.setBackgroundResource(R.drawable.animbird);
			 final AnimationDrawable    mAnimationDrawable3 =(AnimationDrawable) imagebird1.getBackground();
			 animThread1= new Thread(new Runnable() {

				public void run() {
					try {
					Thread.sleep(1000);
					mAnimationDrawable3.start();
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
					}
					});
			animThread1.start();	
			
	}

	
	public void startAnimationLayout3()
	{
		imagebarrel =(ImageView)findViewById(R.id.ibtnbarrel);
		imagebarrel.setBackgroundResource(R.drawable.animbarrel);
		final AnimationDrawable  mAnimationDrawable4 =(AnimationDrawable) imagebarrel.getBackground();
		 animThread1= new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable4.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
				});
		animThread1.start();	
				
		imagemine =(ImageView)findViewById(R.id.ibtnmine);
		imagemine.setBackgroundResource(R.drawable.animmine);
		final AnimationDrawable mAnimationDrawable5 =(AnimationDrawable) imagemine.getBackground();
		animThread2=	new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable5.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
					});
		animThread2.start();
						
	}
	
	public void startAnimationLayout4()
	{
		 imagedolphin =(ImageView)findViewById(R.id.ibtndolphin); 
		 imagedolphin.setBackgroundResource(R.drawable.animdolphin);
		 final AnimationDrawable  mAnimationDrawable6 =(AnimationDrawable) imagedolphin.getBackground();
		 animThread1= new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable6.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
				});
		 animThread1.start();	

	    imagetreasurechute =(ImageView)findViewById(R.id.ibtntreasurechute);
	    imagetreasurechute.setBackgroundResource(R.drawable.animtreasurechute);
	    final AnimationDrawable  mAnimationDrawable7 =(AnimationDrawable) imagetreasurechute.getBackground();
		animThread2=	new Thread(new Runnable() {

			public void run() {
				try {
				Thread.sleep(1000);
				mAnimationDrawable7.start();
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				}
					});
		animThread2.start();
						
	}
	public void onClick(View v) {
		if(v==btnPlaynow)
		{
			System.gc();
			Intent intent =new Intent(this,Game.class);
			startActivity(intent);
			GameSelectionGallary.this.finish();
		}
		else if(ibtnMenu==v)
		{
			Intent i =new Intent(this,Home.class);
			startActivity(i);
		}
		else if(imgbtnnext==v)
		{
			
			imgbtnprev.setEnabled(true);
			galleryPossition = mGallery.getPositionForView(mGallery.getSelectedView());
			galleryPossition++;

			if(galleryPossition > mGallery.getAdapter().getCount() - 1)
			{
				galleryPossition = mGallery.getAdapter().getCount() - 1;
			imgbtnnext.setEnabled(false);
			}
			mGallery.setSelection(galleryPossition);


		}
		else if(imgbtnprev==v)
		{
	

			imgbtnnext.setEnabled(true);
			galleryPossition = mGallery.getPositionForView(mGallery.getSelectedView());
			galleryPossition --;

			if(galleryPossition < 0)
			{
				galleryPossition = 0;
			imgbtnprev.setEnabled(false);
			}
			mGallery.setSelection(galleryPossition);
		}
		visibility(galleryPossition);
	}
	public void HideLayout() {
		System.out.println("CallBack is perform");
		hideAll();
	}
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
	
		galleryPossition =position;
		Bitmap bitmap;
		int id = parent.getId();
		
		switch (id) {
		case R.id.examplegallery:
			
			if(position >= mGallery.getAdapter().getCount() - 1)
			{
				imgbtnnext.setEnabled(false);
				imgbtnprev.setEnabled(true);
			}
			else if (position <= 0)
			{
				imgbtnnext.setEnabled(true);
				imgbtnprev.setEnabled(false);
			}
			else
			{
				imgbtnnext.setEnabled(true);
				imgbtnprev.setEnabled(true);
			}
			visibility(position);	
			if(position==0)
			{
				bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.stagetitle_amazon);
				ibtnTitle.setImageBitmap(bitmap);
				bitmapOrg =bitmap;
			}
			else if(position==1)
			{
				bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.stagetitle_arctic);
				ibtnTitle.setImageBitmap(bitmap);
				bitmapOrg=bitmap;
			}
			else if(position==2)
			{
				bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.stagetitle_industrial);
				ibtnTitle.setImageBitmap(bitmap);
				bitmapOrg=bitmap;
			}
			else if(position==3)
			{
				bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.stagetitle_tropical);
				ibtnTitle.setImageBitmap(bitmap);
				bitmapOrg=bitmap;
			
			}
			break;
		default:
			break;
		}
	}
	public void onNothingSelected(AdapterView<?> arg0) {
	}
}

