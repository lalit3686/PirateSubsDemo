package com.pirate.subs;


import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Photo_Gallery_Adapter extends BaseAdapter{

	List<PhotoGridGetterSetter> list;
	Activity mActivity;
	LayoutInflater inflater;
	
	public Photo_Gallery_Adapter(Activity mActivity,List<PhotoGridGetterSetter> list){
		
		this.mActivity = mActivity;
		this.list = list;
		inflater = LayoutInflater.from(mActivity);
	}

	private class ViewHolder{
		
		private ImageView imgViewGallery;
	}

	public int getCount() {
		return list.size();
		}

	public Object getItem(int position) {
		return position;
		}

	public long getItemId(int position) {
		return position;
		}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder mViewHolder = new ViewHolder();
		if(convertView == null){

			convertView = inflater.inflate(R.layout.row_gallery,null);
			//convertView = inflater.inflate(R.layout.,null);
			
			mViewHolder.imgViewGallery = (ImageView) convertView.findViewById(R.id.imgGallery);
			convertView.setTag(mViewHolder);
		}
		else{
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		mViewHolder.imgViewGallery.setImageResource(list.get(position).getmBitmap());
		return convertView;
	}
}
