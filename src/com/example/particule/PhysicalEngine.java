package com.example.particule;

import java.util.ArrayList;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class PhysicalEngine {


	private ArrayList<Ball> mBallList = null;

	public ArrayList<Ball> getBall() {
		return mBallList;
	}

	public void setBall(ArrayList<Ball> pBallList) {
		this.mBallList = pBallList;
	}

	private MainActivity mActivity = null;

	private SensorManager mManager = null;
	private Sensor mAccelerometer = null;

	SensorEventListener mSensorEventListener = new SensorEventListener(){

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {


		}

		@Override
		public void onSensorChanged(SensorEvent event) {


			// Value of the x axis
			float x = event.values[0];

			// Value of the y axis
			float y = event.values[1];

			ArrayList<Ball> copyBallList = mBallList;
						
			// We update the coordinate of the ball
			for(Ball b : mBallList){
				
				for(Ball c :copyBallList)
				{	
					Ball cb = (Ball) b.clone();
					Ball cc = (Ball) c.clone();
										
					RectF rect_b = cb.putXAndY(x, y);
					RectF rect_c = cc.putXAndY(x, y);					
					
					b.setColisionLeft(rect_b.left > rect_c.right);
					b.setColisionRight(rect_b.right < rect_c.left);
					b.setColisionTop(rect_b.top < rect_c.bottom);
					b.setColisionBottom(rect_b.bottom > rect_c.top);
									
					
				}
				
				b.putXAndY(x, y);
			}


		}

	};

	public PhysicalEngine(MainActivity pView){

		mActivity = pView;
		mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
		mAccelerometer = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	// Reset the position of the ball
	public void reset(){
		for(Ball b : mBallList){
			b.reset();
		}
	}

	// Stop the sensor
	public void stop(){
		mManager.unregisterListener(mSensorEventListener, mAccelerometer);
	}

	// Restart the sensor
	public void resume(){
		mManager.registerListener(mSensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);	
	}



}
