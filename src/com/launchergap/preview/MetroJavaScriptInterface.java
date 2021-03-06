/*
 * Copyright (C) 2012 Moko365 Inc.
 * Copyright (C) 2014 Launcher Gap
 * 
 * Author: jollen <jollen@jollen.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.launchergap.preview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
  
public class MetroJavaScriptInterface {
	
	private MainActivity mContext;
	private String TAG = "MetroJavaScriptInterface";
	
	public MetroJavaScriptInterface(Context context) {
		mContext = (MainActivity) context;
	}

	public void startActivityByMessage(Message msg) {
		Handler handler = mContext.getHandler();
		
		// If we are in edit mode, rewrite the message
		if (mContext.getConfiguration().getEditMode() == true) {
			msg.what = MainActivity.MSG_START_EDIT_DIALOG;
		}
		handler.sendMessage(msg);	
	}

	/**
	 * JavaScript to Java method call �䴩�h�A
	 * 
	 * @param tileIDStr
	 * @param packageName
	 * @param activityName
	 */
	public void startActivity(String tileIDStr, String packageName, String activityName) {
		//Log.i(TAG, "startActivityWithID: [" + tileIDStr + ", " + packageName + "], [" + activityName + "]");
		int tileID = Integer.valueOf(tileIDStr);
		
		Message msg = Message.obtain();
		Bundle data = new Bundle();
		
		data.putString("packageName", packageName);
		data.putString("activityName", activityName);
		data.putInt("_ID", tileID);
		msg.setData(data);
		
		msg.what = MainActivity.MSG_START_ACTIVITY;
		
		startActivityByMessage(msg);
	}
	
	/**
	 * @param moduleName
	 */
	public void startActivity(String tileIDStr, String moduleName) 
	{
		//Log.i(TAG, "Start module: " + moduleName);
		int tileID = Integer.valueOf(tileIDStr);
	
		Message msg = Message.obtain();
		Bundle data = new Bundle();
		
		data.putString("moduleName", moduleName);
		data.putInt("_ID", tileID);		
		msg.setData(data);
		
		msg.what = MainActivity.MSG_START_MODULE;
		
		startActivityByMessage(msg);
	}
		
	public void lauchPackageManager() {
		Intent i = new Intent();
		
		i.setAction("metromenu.intent.action.SETTINGS");
    		mContext.startActivity(i);
	}
	
	public void updateOrder(String idStr, String orderStr) {
		int id = Integer.valueOf(idStr);
		int order = Integer.valueOf(orderStr);
		
		//Log.i(TAG, "ID: " + id + ", order: " + order);
		mContext.updateOrderByID(id, order);
	}

	public void deleteTileByID(String idStr) {
		int id = Integer.valueOf(idStr);
		
		//Log.i(TAG, "Delete tile with ID: " + id);
		mContext.deleteTileDialog(id);
	}
	
	/**
	 * @deprecated
	 */
	public void updateOrderDone() {
	}
	
	public void debug(String message) {
		Log.i(TAG, message);
	}
}