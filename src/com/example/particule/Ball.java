
package com.example.particule;

import android.graphics.Color;
import android.graphics.RectF;

public class Ball implements Cloneable {
	
	// Ball's radius
	public int mRadius = 15;

	// Max speed
	public float mSpeed = 20.0f;

	// Compensatory
	public float mCompensatory = 20.0f;

	// Bounce
	public float mBounce = 1.75f;

	// Ball's color
	private int mColor = Color.YELLOW;

	// Collision
	private boolean mColisionLeft 	= false;
	private boolean mColisionRight 	= false;	
	private boolean mColisionTop 	= false;	
	private boolean mColisionBottom = false;	
	
	public int getColor() {
		return mColor;
	}
	
	public void setColor(int pColor){
		this.mColor = pColor;
	}
	
	public boolean getColisionLeft() {
		return mColisionLeft;
	}

	public void setColisionLeft(boolean mColision) {
		this.mColisionLeft = mColision;
	}
	
	public boolean getColisionRight() {
		return mColisionRight;
	}

	public void setColisionRight(boolean mColisionRight) {
		this.mColisionRight = mColisionRight;
	}

	public boolean getColisionTop() {
		return mColisionTop;
	}

	public void setColisionTop(boolean mColisionTop) {
		this.mColisionTop = mColisionTop;
	}

	public boolean getColisionBottom() {
		return mColisionBottom;
	}

	public void setColisionBottom(boolean mColisionBottom) {
		this.mColisionBottom = mColisionBottom;
	}

	// Rectangle which represent the initial position of the ball
	private RectF mInitialRectangle = null;
		
	

	// Until the initial rectangle we define the position of the ball	
	public void setmInitialRectangle(RectF pInitialRectangle) {
		this.mInitialRectangle = pInitialRectangle;
		this.mX = pInitialRectangle.left + this.mRadius;
		this.mY = pInitialRectangle.top + this.mRadius;
	}
	
	// Rectangle to manage the collisions
	private RectF mRectangle = null;

	// Coordinate in x
	private float mX;

	public float getX() {
		return mX;
	}

	public void setPosX(float pPosX) {
		this.mX = pPosX;

		// if the ball go outside of the border, it bounce
		if(mX < mRadius){
			mX = mRadius;
			// We change the direction of the ball
			mSpeedY = -mSpeedY / mBounce;
		}else if(mX > mWidth - mRadius){
			mX = mWidth - mRadius;
			mSpeedY = -mSpeedY / mBounce;
		}
	}

	// Coordinate in y
	private float mY;

	public float getY() {
		return mY;
	}

	public void setPosY(float pPosY) {
		this.mY = pPosY;
		if(mY < mRadius){
			mY = mRadius;
			mSpeedX = -mSpeedX / mBounce;
		}else if(mY > mHeight - mRadius){
			mY = mHeight - mRadius;
			mSpeedX = -mSpeedX / mBounce;
		}
	}

	// Speed on the x axis
	private float mSpeedX = 0;

	// Use when we bounce on the horizontal wall
	public void ChangeXSpeed(){
		mSpeedX = -mSpeedX;
	}

	// Speed on the y axis
	private float mSpeedY = 0;

	// Use when we bounce on the vertical wall
	public void ChangeYSpeed(){
		mSpeedY = -mSpeedY;
	}

	// Height of the screen
	private int mHeight = -1;

	public void setHeight(int pHeight) {
		this.mHeight = pHeight;
	}

	// Width of the screen
	private int mWidth = -1;

	public void setWidth(int pWidth) {
		this.mWidth = pWidth;
	}

	//Builder
	public Ball(){
		mRectangle = new RectF();
	}
		
	// Update the coordinate of the ball
	public RectF putXAndY(float pX, float pY){
			
		mSpeedX += pX / mCompensatory;

		if(mSpeedX > mSpeed)
			mSpeedX = mSpeed;
		if(mSpeedX < -mSpeed)
			mSpeedX = -mSpeed;

		mSpeedY += pY /mCompensatory;

		if(mSpeedY > mSpeed)
			mSpeedY = mSpeed;
		if(mSpeedY < -mSpeed)
			mSpeedY = -mSpeed;

		setPosX(mX + mSpeedY);
		setPosY(mY + mSpeedX);

		// Update the rectangle of collision
		mRectangle.set(mX - mRadius, mY - mRadius, mX + mRadius, mY + mRadius);

		return mRectangle;
	}

	@Override
	protected Object clone(){
		
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		
	}

	// Put the ball at the initial position
	public void reset(){
		mSpeedX = 0;
		mSpeedY = 0;
		this.mX = mInitialRectangle.left + mRadius;
		this.mY = mInitialRectangle.top + mRadius;
	}
	
	

}
