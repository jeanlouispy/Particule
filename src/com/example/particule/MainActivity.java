package com.example.particule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final int[] colorTab = {Color.YELLOW,Color.GREEN,Color.BLUE,Color.MAGENTA,Color.RED};
	private static final int[] colorTab2 = {Color.argb(100,255,0,0),Color.argb(100,0,255,0),Color.argb(100,0,0,255),Color.argb(100,255,255,0),Color.argb(100,0,255,255)};
	private static final int[] colorTab3 = {Color.YELLOW,Color.GREEN,Color.BLUE,Color.MAGENTA,Color.RED,Color.argb(100,255,0,0),Color.argb(100,0,255,0),Color.argb(100,0,0,255),Color.argb(100,255,255,0),Color.argb(100,0,255,255)};
	private static final int[] colorTab4 = {Color.YELLOW,Color.argb(100,0,255,0)};
	
	// Graphical engine of the game
	private GraphicalEngine mGraphicalEngine = null;

	// Physical engine of the game
	private PhysicalEngine mPhysicalEngine = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		mGraphicalEngine = new GraphicalEngine(this);
		
		setContentView(mGraphicalEngine);

		mPhysicalEngine = new PhysicalEngine(this);

		ArrayList<Ball> ballList = new ArrayList<Ball>();

		Date d = new Date();
		Random r = new Random(d.getTime());
		
		
		for(int i = 1 ; i < 50
				; i++)
		{			
			Ball b1 = new Ball();
			//b1.setColor(colorTab4[r.nextInt(2)]);
			//b1.setColor(colorTab2[3]);
//			if(r.nextInt() % 2 == 0)
//			{
//				b1.setColor(Color.argb(r.nextInt(255), 255, r.nextInt(255),0));
//			}else
//			{				
//				b1.setColor(Color.argb(r.nextInt(255), 255, 200, 0));
//			}
			
			b1.setColor(Color.argb(r.nextInt(255), r.nextInt(255), r.nextInt(255),r.nextInt(255)));
						
			b1.mRadius = r.nextInt(35);
			b1.mBounce = r.nextFloat() * 3;
			b1.mSpeed  = r.nextFloat() * 7 ;
			b1.mCompensatory = r.nextFloat() * 20;
			ballList.add(b1);

		}
		
		mGraphicalEngine.setmBall(ballList);
		mPhysicalEngine.setBall(ballList);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mPhysicalEngine.resume();
	}

	@Override
	protected void onPause() {
		super.onStop();
		mPhysicalEngine.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_restart:
	        	finish();
	        	startActivity(getIntent());
	            return true;
	          
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
	

}
