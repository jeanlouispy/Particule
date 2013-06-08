package com.example.particule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphicalEngine extends SurfaceView implements
SurfaceHolder.Callback {

	private ArrayList<Ball> mBallList;


	public ArrayList<Ball> getmBall() {
		return mBallList;
	}

	public void setmBall(ArrayList<Ball> pBallList) {
		this.mBallList = pBallList;
	}

	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;

	Paint mPaint;

	public GraphicalEngine(Context context) {
		super(context);

		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();

		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);

		mBallList = new ArrayList<Ball>();

	}

	@Override
	protected void onDraw(Canvas pCanvas) {

		// We draw the background firth
		pCanvas.drawColor(Color.BLACK);


		//Drawing the ball
		for(Ball b : mBallList){		

			if(b != null){
				mPaint.setColor(b.getColor());
				pCanvas.drawCircle(b.getX(), b.getY(), b.mRadius, mPaint);
			}

		}



	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {

		mThread.keepDrawing = true;
		mThread.start();

		//When we create the ball we indicate the coordinate of the screen
		for(Ball b : mBallList){
			if(b != null){
				b.setHeight(getHeight());
				b.setWidth(getWidth());
			}
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		mThread.keepDrawing = false;
		boolean retry = true;
		while(retry){
			try{
				mThread.join();
				retry = false;
			}catch(InterruptedException e){}
		}

	}


	// Class Thread
	public class DrawingThread extends Thread {

		// use for stop the drawing when we need
		boolean keepDrawing = true;

		public void run() {

			while(keepDrawing){

				Canvas canvas = null;

				try{
					// Getting the canvas for drawing on
					canvas = mSurfaceHolder.lockCanvas();

					// We ensure they are not any other thread can access to the holder (critical section)
					synchronized (mSurfaceHolder){
						onDraw(canvas);
					}

				}finally{
					// Our drawing is finish we freeing the canvas for let draw itself
					if(canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}

				//For drawing at 50 fsp
				try{
					Thread.sleep(20);
				}catch(InterruptedException e){}
			}

		}

	}
	//

}
