package com.pirate.subs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class mygallery extends Gallery {

	myCallBack mCallBack;
	
    public mygallery(Context ctx, AttributeSet attrSet) {
        super(ctx,attrSet);
        // TODO Auto-generated constructor stub
        mCallBack =(myCallBack) ctx;
        
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
    	System.out.println("Calling onFling event");
    	super.onFling(e1, e2, 5, velocityY);
//    	mCallBack.HideLayout();
    	return false;
    }
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		
		System.out.println("Calling onScrolling event");
		mCallBack.HideLayout();
		return super.onScroll(e1, e2,  distanceX, distanceY);
    	
	}
	public static interface myCallBack
	{
		public void HideLayout();
		
	}
}
