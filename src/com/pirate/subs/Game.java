package com.pirate.subs;

import java.util.HashMap;
import java.util.Map;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class Game extends BaseGameActivity implements OnGestureListener,IOnSceneTouchListener{

	boolean mynewFlag = false;
	MediaPlayer mediaPlayer;
	boolean FlagLifeKilledShip = false, FlagLifeKilledShip1 = false;
	int RandomCheck = -1;
	boolean FlagBird = false, FlagShip1 = false, FlagShip = false;
	private Map<Integer, String> mapRandom = new HashMap<Integer, String>(); 
	private Handler handler;
	private Runnable runnable;
	private int sky1;
	
	private boolean onGameLoad = true;
	private int tapCount = 0,swipeCount = 0;
	private int ScoreIncrement = 1;
	int LifeLineDead = 0;
	int ShipY = 0;
	boolean FlagTouchBlast = false,FlagTouchBlast1 = false,FlagCollideBlast = false, FlagCollideBlast1 = false,FlagBirdCollide = false;
	int angleofRotationShip1 = 0, angleofRotationShip = 0;
	int AlphaValue = 20;
	boolean BallUpRotate = false, BallUpRotate1 = false;;
	private static boolean flagBallUp = false, flagBallUp1 = false;
	private static float ballUpstartPointY = 0, ballUpstartPointY1 = 0;
	
	private IUpdateHandler myhandlerBackGround,myhandlerblastbackground,myhandlerBackGround1;
	private IUpdateHandler myhandlership, myhandlership1;
	private IUpdateHandler myhandlerMissile;
	private IUpdateHandler myhandlerBlast, myhandlerBlast1,myhandlerSubEscaped,myhandlerBirdKilled;
	private IUpdateHandler myhandlerWaves;
	private IUpdateHandler myhandlerWaves1;
	private IUpdateHandler myhandlerBird;
	private IUpdateHandler myhandlerlifeboat;
	private IUpdateHandler myhandlerTap, myhandlerSwipe;
	
	static final int SWIPE_MIN_DISTANCE = 120;
	static final int SWIPE_MAX_OFF_PATH = 250;
	static final int SWIPE_THRESHOLD_VELOCITY = 200;
	static int CAMERA_WIDTH;
	static int CAMERA_HEIGHT;
	
	private Scene scene;
	private ZoomCamera mCamera;
	
	/*************************************  Texture Declaration **********************/
	private int arc = 85;
	private Texture mTextureSecondShip;
	private TiledTextureRegion mFaceSecondShip;
	
	private Texture mTextureSwipe;
	private TiledTextureRegion mFaceTextureRegionSwipe;
	
	private Texture mTextureTap;
	private TiledTextureRegion mFaceTextureRegionTap;
	
	private Texture mTextureLifeBoat;
	private TiledTextureRegion mFaceTextureRegionLifeBoat;
	
	private Texture mTextureBirdKilled;
	private TiledTextureRegion mFaceTextureRegionBirdKilled;
	
	private Texture mTextureSubEscaped;
	private TiledTextureRegion mFaceTextureRegionSubEscaped;
	
	private Texture myTextureFont;
	private Font myFont;
	
	private Texture mTextureGameOver;
	private TiledTextureRegion mFaceTextureRegionGameOver;
	
	private Texture mTextureBird;
	private TiledTextureRegion mFaceTextureRegionBird;
	
	private Texture mTextureLifeLine, mTextureLifeLine1, mTextureLifeLine2, mTextureLifeLine3;
	private TiledTextureRegion mFaceTextureRegionLifeLine, mFaceTextureRegionLifeLine1, mFaceTextureRegionLifeLine2, mFaceTextureRegionLifeLine3;
	
	private Texture mTextureBlastBackGround;
	private TiledTextureRegion mFaceTextureRegionBlastBackGround;
	
	private Texture mTextureBestScore;
	private TiledTextureRegion mFaceTextureRegionBestScore;
	
	private Texture mTextureScore;
	private TiledTextureRegion mFaceTextureRegionScore;
	
	private Texture mTextureBackGround, mTextureBackGroundOpposite;
	private TiledTextureRegion mFaceTextureRegionBackGround, mFaceTextureRegionBackGroundOpposite;
	
	private Texture mTextureWaves, mTextureWaves1;
	private TiledTextureRegion mFaceTextureRegionWaves, mFaceTextureRegionWaves1;
	
	private Texture mTextureSub;
	private TiledTextureRegion mFaceTextureRegionSub;
	
	private Texture mTextureMissileLeftToRight;
	private TiledTextureRegion mFaceTextureRegionMissileLeftToRight;
	
	private Texture mTextureBlast;
	private TiledTextureRegion mFaceTextureRegionBlast;
	
	/*************************************  Texture Declaration **********************/
	
	private BackGroundImage mBackGroundImage = null;
	private BackGroundImage1 mBackGroundImage1 = null;
	private Waves myWaves =  null;
	private Waves1 myWaves1 =  null;
	private ShipSub ship = null;
	private ShipSub1 ship1 = null;
	private Missile missile = null;
	private Score score = null;
	private BestScore bestscore = null;
	private BlastBackGround blastbackground = null;
	private AnimatedSprite lifeline = null, lifeline1 = null,lifeline2 = null,lifeline3 = null, GameOver = null;
	private LifeBoat_Animate lifeBoat_animate = null;
	private Bird bird = null;
	private BlastOnCollideShip blastoncollide = null;
	private BlastOnCollideShip1 blastoncollide1 = null;
	private SubEscaped subescaped = null;
	private BirdKilled birdkilled = null;
	private Tap tap = null;
	private Swipe swipe = null;
	private GestureDetector mGestureDetector = new GestureDetector(this);
	

	/*
	 * CLASSES
	*/
	
	private class Tap extends AnimatedSprite
	{
		public Tap(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerTap = new IUpdateHandler() {
				
				public void onUpdate(float pSecondsElapsed) {

					if(tap.getCurrentTileIndex() >= tapCount - 1){
						this.reset();
						tap.reset();
					
						if(onGameLoad){
							new Thread(new Runnable() {

								public void run() {
									try {
										Thread.sleep(2000);
										swipe.setPosition(CAMERA_WIDTH/2, CAMERA_HEIGHT/4);
										scene.getTopLayer().addEntity(swipe);
										scene.registerUpdateHandler(myhandlerSwipe);
									} 
									catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								
							}).start();
						}
					}
				}

				public void reset() {
					scene.unregisterUpdateHandler(myhandlerTap);
					}
			};
		}
	}
	
	private class Swipe extends AnimatedSprite
	{
		public Swipe(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerSwipe = new IUpdateHandler() {
				
				public void onUpdate(float pSecondsElapsed) {

					if(swipe.getCurrentTileIndex() >= swipeCount - 2){
						this.reset();
						swipe.reset();
						
						if(onGameLoad){
							
							new Thread(new Runnable() {

								public void run() {
									try {
										Thread.sleep(2000);
//										lifeBoat_animate.animate(100);
//										lifeBoat_animate.setVelocity(-50, 0);
										
										handler.post(runnable);
//										startShip();
//										flyBird();
										
										scene.setOnSceneTouchListener(Game.this);
										onGameLoad = false;
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									
								}
								
							}).start();
						}
					}
				}

				public void reset() {
					scene.unregisterUpdateHandler(myhandlerSwipe);
					}
			};
		}
	}
	
	
	private class LifeBoat_Animate extends AnimatedSprite
	{
		public LifeBoat_Animate(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerlifeboat = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerlifeboat);
					}

				public void onUpdate(float pSecondsElapsed) {
					
					if(mX < 0){
						this.reset();
						lifeBoat_animate.reset();
						mapRandom.put(3, "d");
						this.reset();
					}
				}
			};
		}
	}
	
	private class Bird extends AnimatedSprite
	{
		public Bird(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBird = new IUpdateHandler() {
				
				public void onUpdate(float pSecondsElapsed) {
					
//					if(LifeLineDead == -1){
//						this.reset();
//					}
					if(mX + bird.getWidth() >= CAMERA_WIDTH){
						
						mapRandom.put(0, "a");
						FlagBirdCollide = false;
					}
				}

				public void reset() {
					scene.unregisterUpdateHandler(myhandlerBird);
					}
				
			};
			scene.registerUpdateHandler(myhandlerBird);
		}
	}
	
	private class BlastBackGround extends AnimatedSprite
	{
		public BlastBackGround(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerblastbackground = new IUpdateHandler() {
				
				public void onUpdate(float pSecondsElapsed) {
					if(blastbackground.getCurrentTileIndex() >= 23){
						blastbackground.reset();
						this.reset();
						scene.setOnSceneTouchListener(Game.this);
					}
				}

				public void reset() {
                    scene.unregisterUpdateHandler(myhandlerblastbackground);
    				}
			};
		}
	}
	
	private class BestScore extends AnimatedSprite
	{
		public BestScore(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
		}
	}
	
	private class Score extends AnimatedSprite
	{
		public Score(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
		}
	}
	
	private class Waves extends AnimatedSprite
	{

		public Waves(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerWaves = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerWaves);
					}

				public void onUpdate(float pSecondsElapsed) {
					if(mX >= 0){
						myWaves1.setPosition(-(mFaceTextureRegionWaves1.getWidth() - 10), CAMERA_HEIGHT/2);
						myWaves1.setVelocity(25, 0);
						scene.registerUpdateHandler(myhandlerWaves1);
						this.reset();
					}
				}
			};
		}
	}
	
	private class Waves1 extends AnimatedSprite
	{
		public Waves1(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerWaves1 = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerWaves1);
					}

				public void onUpdate(float pSecondsElapsed) {
					
					if(mX >= 0){
						myWaves.setPosition(-(mFaceTextureRegionWaves.getWidth() - 10), CAMERA_HEIGHT/2);
						myWaves.setVelocity(25, 0);
						scene.registerUpdateHandler(myhandlerWaves);
						this.reset();
					}
				}
			};
		}
	}
	
	private class BackGroundImage extends AnimatedSprite
	{
		public BackGroundImage(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBackGround = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerBackGround);
					}

				public void onUpdate(float pSecondsElapsed) {
					
					if(mX + mBackGroundImage.getWidth() <= CAMERA_WIDTH){
						mBackGroundImage1.reset();
						mBackGroundImage1.setPosition(CAMERA_WIDTH - 5, 0);
						mBackGroundImage1.setVelocity(-25, 0);
						scene.registerUpdateHandler(myhandlerBackGround1);
						this.reset();
					}
				}
			};
		}
	}
	
	private class BackGroundImage1 extends AnimatedSprite
	{
		public BackGroundImage1(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBackGround1 = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerBackGround1);
					}

				public void onUpdate(float pSecondsElapsed) {
					
					if(mX + mBackGroundImage1.getWidth() <= CAMERA_WIDTH){
						mBackGroundImage.setPosition(CAMERA_WIDTH - 5, 0);
						mBackGroundImage.setVelocity(-25, 0);
						scene.registerUpdateHandler(myhandlerBackGround);
						this.reset();
					}
				}
			};
		}
	}
	private class ShipSub extends AnimatedSprite
	{
		public ShipSub(float pX, float pY, TiledTextureRegion mFaceTextureRegion) {
			super(pX, pY, mFaceTextureRegion);
			
			myhandlership = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlership);
					}

				public void onUpdate(float pSecondsElapsed) {

					if(BallUpRotate == true){
						// sets the rotation angles of the object
						
						angleofRotationShip = angleofRotationShip + 3;
						ship.setRotation(angleofRotationShip);
					}
					
					// ball starts again from the beginning
					if(mX > CAMERA_WIDTH){
						
						System.out.println("LifeLineDead - ship "+LifeLineDead);
						
						if(LifeLineDead < 2){
							subescaped.setPosition((CAMERA_WIDTH/2)-(mFaceTextureRegionSubEscaped.getWidth())/2, 20);
							scene.registerUpdateHandler(myhandlerSubEscaped);
						}
						
						if(FlagLifeKilledShip == false){
							LifeLineDeadMethod();
							FlagLifeKilledShip = true;
						}
						this.reset();
						mapRandom.put(1, "b");
					}
					
					// ball returns from top
					if(mY <= 0) {
						mynewFlag = true;
						setVelocityY(100.0f);
					}
					//ball moves in the direction it was before going up.
					
					if(mynewFlag == true){
						if(mY > (CAMERA_HEIGHT/2)){
							
							ship.setRotation(arc);
							ship.setVelocity(40, 0);
							arc = arc - 5;
							if(arc==0){
								BallUpRotate = false;
								ship.setVelocity(200, 0);
								mynewFlag = false;
							}
							//stop animation when it reaches back to the position from where it started moving UP.
							
						}	
					}
				}
			};
			scene.registerUpdateHandler(myhandlership);
		}
	}
	
	
	private class ShipSub1 extends AnimatedSprite
	{
		public ShipSub1(float pX, float pY, TiledTextureRegion mFaceTextureRegion) {
			super(pX, pY, mFaceTextureRegion);
			
			myhandlership1 = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlership1);
					}

				public void onUpdate(float pSecondsElapsed) {

					if(BallUpRotate1 == true){
						// sets the rotation angles of the object
						angleofRotationShip1 = angleofRotationShip1 + 3;
						ship1.setRotation(angleofRotationShip1);
					}
					
					// ball starts again from the beginning
					if(mX > CAMERA_WIDTH){
						
						System.out.println("LifeLineDead - ship "+LifeLineDead);
						
						if(LifeLineDead < 2){
							subescaped.setPosition((CAMERA_WIDTH/2)-(mFaceTextureRegionSubEscaped.getWidth())/2, 20);
							scene.registerUpdateHandler(myhandlerSubEscaped);
						}
						if(FlagLifeKilledShip1 == false){
							LifeLineDeadMethod();
							FlagLifeKilledShip1 = true;
						}
						this.reset();
						mapRandom.put(2, "c");
					}
					
					// ball returns from top
					if(mY <= 0) {
						setVelocityY(100.0f);
					}
					//ball moves in the direction it was before going up.
					if(mY > ballUpstartPointY1){
						
						//stop animation when it reaches back to the position from where it started moving UP.
						BallUpRotate1 = false;
						ship1.setVelocity(50, 0);
					}
				}
			};
			scene.registerUpdateHandler(myhandlership1);
		}
	}
	
	
	private class Missile extends AnimatedSprite
	{
		public Missile(float pX, float pY, TiledTextureRegion mFaceTextureRegion) {
			super(pX, pY, mFaceTextureRegion);
			
			final MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.grenade_explosion);
			
			if(myhandlerMissile == null){
				myhandlerMissile = new IUpdateHandler() {
					
					public void onUpdate(float pSecondsElapsed) {

						if(lifeBoat_animate.collidesWith(missile)){
							
							mPlayer.start();
							mapRandom.put(3, "d");
							scene.registerUpdateHandler(myhandlerlifeboat);
							
							blastoncollide.setPosition(lifeBoat_animate.getX(), lifeBoat_animate.getY());
							blastoncollide.animate(150);
							lifeBoat_animate.reset();
							scene.registerUpdateHandler(myhandlerBlast);
						}
						
						if(bird.collidesWith(missile)){
							
							if(FlagBirdCollide == false){
								
								mPlayer.start();
								mapRandom.put(0, "a");
								LifeLineDeadMethod();
								birdkilled.setPosition((CAMERA_WIDTH/2)-(mFaceTextureRegionSubEscaped.getWidth())/2, 20);
								bird.setVisible(false);
								scene.registerUpdateHandler(myhandlerBirdKilled);
								FlagBirdCollide = true;
							}
						}
						
						if(flagBallUp1 == true){
							
							if(missile.collidesWith(ship1)){

								if(FlagCollideBlast1 == false){

									mPlayer.start();
									mapRandom.put(2, "c");
									blastoncollide1.setPosition(ship1.getX(), ship1.getY());
									blastoncollide1.animate(150);
									scene.registerUpdateHandler(myhandlerBlast1);
									FlagCollideBlast1 = true;
								}
								ResetShip1onBlast();
							}	
						}
						
						
						// checking that it should blast when the object is UP.
						if(flagBallUp == true){
						
							if(missile.collidesWith(ship)){

								if(FlagCollideBlast == false){

									mPlayer.start();
									mapRandom.put(1, "b");
									blastoncollide.setPosition(ship.getX() + 30, ship.getY());
									blastoncollide.animate(150);
									scene.registerUpdateHandler(myhandlerBlast);
									FlagCollideBlast = true;
								}
								ResetShiponBlast();
							}	
						}
						// Resetting the launcher for left to right fling
						if (mX + getWidth() > CAMERA_WIDTH) {
							this.reset();
						}
						// Resetting the launcher for right to left fling
						if (mX < 0) {
							this.reset();
						}
						// Resetting the launcher for the top to bottom
						if(mY >= CAMERA_HEIGHT){
							this.reset();
						}
						// Resetting the launcher for the bottom to top
						if(mY <= 0){
							this.reset();
						}
					}

					public void reset() {
						scene.unregisterUpdateHandler(myhandlerMissile);
						myhandlerMissile = null;
					}
					
				};
				scene.registerUpdateHandler(myhandlerMissile);
			}
		}
	}
	
	private class BlastOnCollideShip extends AnimatedSprite
	{
		public BlastOnCollideShip(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBlast = new IUpdateHandler() {
				
				public void reset() {
                    scene.unregisterUpdateHandler(myhandlerBlast);
    				}

				public void onUpdate(float pSecondsElapsed) {
					
				// checking the current Tile, that changes accordingly.
				if(blastoncollide.getCurrentTileIndex() >= blastoncollide.getTextureRegion().getTileCount() - 2){
					blastoncollide.reset();
					this.reset();
				}
			}
		};
	}
}
	
	private class BlastOnCollideShip1 extends AnimatedSprite
	{
		public BlastOnCollideShip1(float pX, float pY, TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBlast1 = new IUpdateHandler() {
				
				public void reset() {
                    scene.unregisterUpdateHandler(myhandlerBlast1);
    				}

				public void onUpdate(float pSecondsElapsed) {
					
				// checking the current Tile, that changes accordingly.
				if(blastoncollide1.getCurrentTileIndex() >= blastoncollide1.getTextureRegion().getTileCount() - 2){
					blastoncollide1.reset();
					this.reset();
				}
			}
		};
	}
}
	
	
	private class BirdKilled extends AnimatedSprite
	{
		public BirdKilled(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerBirdKilled = new IUpdateHandler() {
				
				public void reset() {
					scene.unregisterUpdateHandler(myhandlerBirdKilled);
					}

				public void onUpdate(float pSecondsElapsed) {
					
					// Setting the Alpha
					setAlpha(AlphaValue);
					if(AlphaValue == 0){
						this.reset();
						AlphaValue = 20;
					}
					else{
						AlphaValue = AlphaValue - 4;
					}
				}
			};
		}
	}
	
	private class SubEscaped extends AnimatedSprite
	{
		public SubEscaped(float pX, float pY,
				TiledTextureRegion pTiledTextureRegion) {
			super(pX, pY, pTiledTextureRegion);
			
			myhandlerSubEscaped = new IUpdateHandler() {
				

				public void onUpdate(float pSecondsElapsed) {
					setAlpha(AlphaValue);
					if(AlphaValue == 0){
						this.reset();
						AlphaValue = 20;
					}
					else{
						AlphaValue = AlphaValue - 4;
					}
				}

				public void reset() {
					scene.unregisterUpdateHandler(myhandlerSubEscaped);
					}
			};
		}
	}
	
	// LifeLine Killed and Updates
	private void LifeLineDeadMethod() {

		LifeLineDead++;
		switch (LifeLineDead) {

		case 1:
			scene.getTopLayer().replaceEntity(9, lifeline1);
			break;
		case 2:
			scene.getTopLayer().replaceEntity(9, lifeline2);
			break;
		case 3:
			scene.getTopLayer().replaceEntity(9, lifeline3);
			LifeLineDead = -1;

			ship.setVisible(false);
			ship1.setVisible(false);
			bird.setVisible(false);
			lifeBoat_animate.setVisible(false);

			handler.removeCallbacks(runnable);
			ship.reset();
			ship1.reset();
			scene.unregisterTouchArea(ship);
			scene.unregisterTouchArea(ship1);
			scene.setOnSceneTouchListener(null);
			scene.getTopLayer().addEntity(GameOver);
			
			new Thread(new Runnable() {

				public void run() {
				
					try {
						Thread.sleep(2000);
						Intent intent = new Intent(Game.this,gameoverscreen.class);
						intent.putExtra("currentscore", ScoreIncrement);
						intent.putExtra("subkilled", 5);
						intent.putExtra("timePlayed",1.15);
						startActivity(intent);
						Game.this.finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					};
				}
				
			}).start();


			break;
		}
	}
	
	// Resetting the Sprite's on Collide of Ship and Missile.
	private void ResetShiponBlast() {

		ship.reset();
		scene.unregisterUpdateHandler(myhandlership);
		scene.registerTouchArea(ship);
		BallUpRotate = false;
		flagBallUp = false;
		FlagTouchBlast = false;
		FlagCollideBlast = false;
		FlagCollideBlast1 = false;

		ScoreIncrement++;
		final Text scorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, mFaceTextureRegionScore.getHeight()+ 20, this.myFont, ScoreIncrement+"");
		scene.getTopLayer().replaceEntity(5, scorevalue);
		final Text bestscorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, 10, this.myFont, ScoreIncrement+"");
		scene.getTopLayer().replaceEntity(6, bestscorevalue);
	}
	
	private void ResetShip1onBlast()
	{
		ship1.reset();
		scene.unregisterUpdateHandler(myhandlership1);
		scene.registerTouchArea(ship);
		BallUpRotate1 = false;
		flagBallUp1 = false;
		FlagTouchBlast1 = false;
		
		ScoreIncrement++;
		final Text scorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, mFaceTextureRegionScore.getHeight()+ 20, this.myFont, ScoreIncrement+"");
		scene.getTopLayer().replaceEntity(5, scorevalue);
		final Text bestscorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, 10, this.myFont, ScoreIncrement+"");
		scene.getTopLayer().replaceEntity(6, bestscorevalue);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		handler.removeCallbacks(runnable);
		finish();
		System.gc();
	}

	public void onLoadComplete() {
		
		mediaPlayer = MediaPlayer
				.create(getApplicationContext(), R.raw.impact1);
		try {
			mediaPlayer.start();
			mediaPlayer.setLooping(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// setting Listener for scene.
		
		tap.setPosition(CAMERA_WIDTH/2,(CAMERA_HEIGHT/2));
		scene.getTopLayer().addEntity(tap);
		scene.registerUpdateHandler(myhandlerTap);

		scene.setOnSceneTouchListener(this);
		scene.registerUpdateHandler(myhandlerBackGround);
		scene.registerUpdateHandler(myhandlerWaves);
	
		// TODO Auto-generated method stub
		
	}

	public Engine onLoadEngine() {
		
		mapRandom.put(0, "a");
		mapRandom.put(1, "b");
		mapRandom.put(2, "c");
		mapRandom.put(3, "d");
		
		handler = new Handler();
		runnable = new Runnable() {

			public void run() {
				sky1 = (int) (Math.random()*4);
				System.out.println("On first BackGround Image - "+sky1);
				
				switch (sky1) {
				case 0:
						/**
						 *  BIRD
						 */
						if(RandomCheck != sky1){
							
							RandomCheck = sky1;	
							if(mapRandom.get(sky1).equalsIgnoreCase("bird")){
							}
							else{
								mapRandom.put(sky1, "bird");
								if(FlagBird == false){
									scene.registerTouchArea(bird);
									scene.getTopLayer().addEntity(bird);	
									FlagBird = true;
								}
								bird.reset();
								bird.setPosition(0, 100);
								bird.animate(100);
								bird.setVelocity(100, 0);
							}
						}
					break;
				case 1:
					
					/**
					 *  SHIP
					 */
					
					arc = 85;
					scene.registerTouchArea(ship);
					angleofRotationShip = 0;
					scene.registerUpdateHandler(myhandlership);
					if(RandomCheck != sky1){
						RandomCheck = sky1;
						if(mapRandom.get(sky1).equalsIgnoreCase("ship")){
							
						}
						else{
							mapRandom.put(sky1, "ship");
							if(FlagShip == false){
								scene.getTopLayer().addEntity(ship);
								FlagShip = true;
							}
							ship.reset();
							ship.setVelocity(50,0);
							FlagLifeKilledShip = false;
						}
					}
					break;
				case 2:
					
					/**
					 *  SHIP1
					 */
					
					scene.registerTouchArea(ship1);
					angleofRotationShip1 = 0;
					scene.registerUpdateHandler(myhandlership1);
					if(RandomCheck != sky1){
						RandomCheck = sky1;
						if(mapRandom.get(sky1).equalsIgnoreCase("ship1")){
							
						}
						else{
							mapRandom.put(sky1, "ship1");
							if(FlagShip1 == false){
								scene.getTopLayer().addEntity(ship1);
								FlagShip1 = true;
							}
							ship1.reset();
							ship1.setVelocity(100,0);
							FlagLifeKilledShip1 = false;
						}
					}
					break;
				case 3:

					/**
					 *  LIFEBOAT
					 */
					scene.registerUpdateHandler(myhandlerlifeboat);
					if(RandomCheck != sky1){
						
						RandomCheck = sky1;	
						if(mapRandom.get(sky1).equalsIgnoreCase("lifeboat")){
						}
						else{
							mapRandom.put(sky1, "lifeboat");
							lifeBoat_animate.reset();
							lifeBoat_animate.animate(100);
							lifeBoat_animate.setVelocity(-50, 0);
						}
					}
					break;
				default:
					break;
				}
				handler.removeCallbacks(runnable);
				handler.postDelayed(runnable, 3000);
				
			}
			
		};

		CAMERA_HEIGHT = getWindowManager().getDefaultDisplay().getHeight();
		CAMERA_WIDTH = getWindowManager().getDefaultDisplay().getWidth();
				
		int Half_Screen = (CAMERA_HEIGHT/2);
		ShipY = Half_Screen + (Half_Screen/2);
		System.out.println(CAMERA_WIDTH+" "+CAMERA_HEIGHT);
		
		this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

	public void onLoadResources() { 
		// Don't give height & width less than the height of the Image of BuildableTexture

		this.myTextureFont = new Texture(256, 256, TextureOptions.BILINEAR);
		
		if(CAMERA_WIDTH <= 320){
			//Set Font for small Screen
			this.myFont = new Font(this.myTextureFont, Typeface.DEFAULT_BOLD, 12, true,Color.YELLOW);
		}else {
			//Set Font for Large screen
			this.myFont = new Font(this.myTextureFont, Typeface.DEFAULT_BOLD, 24, true,Color.YELLOW);	
		}
		
		this.mEngine.getTextureManager().loadTexture(myTextureFont);
		this.mEngine.getFontManager().loadFont(this.myFont);
		
		this.mTextureSecondShip = new Texture(256, 64, TextureOptions.BILINEAR);
		this.mFaceSecondShip = TextureRegionFactory.createTiledFromResource(mTextureSecondShip, this, R.drawable.secondship, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureSecondShip);
		
		this.mTextureSwipe = new Texture(512, 256, TextureOptions.BILINEAR);
		this.mFaceTextureRegionSwipe = TextureRegionFactory.createTiledFromResource(mTextureSwipe, this, R.drawable.swipe, 0, 0, 3, 3);
		this.mEngine.getTextureManager().loadTexture(this.mTextureSwipe);
		
		this.mTextureTap = new Texture(512, 512, TextureOptions.BILINEAR);
		this.mFaceTextureRegionTap = TextureRegionFactory.createTiledFromResource(mTextureTap, this, R.drawable.tap, 0, 0, 3, 3);
		this.mEngine.getTextureManager().loadTexture(this.mTextureTap);
		
		this.mTextureLifeBoat = new Texture(512, 512, TextureOptions.BILINEAR);
		this.mFaceTextureRegionLifeBoat = TextureRegionFactory.createTiledFromResource(mTextureLifeBoat, this, R.drawable.lifeboat, 0, 0, 4, 5);
		this.mEngine.getTextureManager().loadTexture(this.mTextureLifeBoat);
		
		this.mTextureBirdKilled = new Texture(128, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBirdKilled = TextureRegionFactory.createTiledFromResource(mTextureBirdKilled, this, R.drawable.birdkilled, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBirdKilled);
		
		this.mTextureSubEscaped = new Texture(128, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionSubEscaped = TextureRegionFactory.createTiledFromResource(mTextureSubEscaped, this, R.drawable.whaleescaped, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureSubEscaped);
		
		this.mTextureGameOver = new Texture(512, 256, TextureOptions.BILINEAR);
		this.mFaceTextureRegionGameOver = TextureRegionFactory.createTiledFromResource(mTextureGameOver, this, R.drawable.gameover, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureGameOver);
		
		this.mTextureBird = new Texture(512, 256, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBird = TextureRegionFactory.createTiledFromResource(mTextureBird, this, R.drawable.birdcopy, 0, 0, 5, 4);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBird);
		
		this.mTextureLifeLine = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionLifeLine = TextureRegionFactory.createTiledFromResource(mTextureLifeLine, this, R.drawable.lifeline, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureLifeLine);
		
		this.mTextureLifeLine1 = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionLifeLine1 = TextureRegionFactory.createTiledFromResource(mTextureLifeLine1, this, R.drawable.lifeline1, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureLifeLine1);
		
		this.mTextureLifeLine2 = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionLifeLine2 = TextureRegionFactory.createTiledFromResource(mTextureLifeLine2, this, R.drawable.lifeline2, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureLifeLine2);
		
		this.mTextureLifeLine3 = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionLifeLine3 = TextureRegionFactory.createTiledFromResource(mTextureLifeLine3, this, R.drawable.lifeline3, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureLifeLine3);
		
		this.mTextureBlastBackGround = new Texture(1024, 1024, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBlastBackGround = TextureRegionFactory.createTiledFromResource(this.mTextureBlastBackGround, this, R.drawable.nuke, 0, 0, 5, 5);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBlastBackGround);
		
		this.mTextureBestScore = new Texture(64, 16, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBestScore = TextureRegionFactory.createTiledFromResource(this.mTextureBestScore, this, R.drawable.bestscore, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBestScore);
		
		this.mTextureScore = new Texture(64, 64, TextureOptions.BILINEAR);
		this.mFaceTextureRegionScore = TextureRegionFactory.createTiledFromResource(this.mTextureScore, this, R.drawable.scoreicon, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureScore);
		
		this.mTextureBackGround = new Texture(1024, 512, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBackGround = TextureRegionFactory.createTiledFromResource(this.mTextureBackGround, this,R.drawable.amazon_background, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBackGround);
		
		this.mTextureWaves = new Texture(1024, 32, TextureOptions.BILINEAR);
		this.mFaceTextureRegionWaves = TextureRegionFactory.createTiledFromResource(this.mTextureWaves, this,R.drawable.firstwave, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureWaves);
		
		this.mTextureWaves1 = new Texture(1024, 32, TextureOptions.BILINEAR);
		this.mFaceTextureRegionWaves1 = TextureRegionFactory.createTiledFromResource(this.mTextureWaves1, this,R.drawable.secondwave, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureWaves1);
		
		this.mTextureBackGroundOpposite = new Texture(1024, 512, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBackGroundOpposite = TextureRegionFactory.createTiledFromResource(this.mTextureBackGroundOpposite, this,R.drawable.opposite, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBackGroundOpposite);
		
		this.mTextureSub = new Texture(256, 128, TextureOptions.BILINEAR);
		this.mFaceTextureRegionSub = TextureRegionFactory.createTiledFromResource(this.mTextureSub, this, R.drawable.mybluetarget, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureSub);
		
		this.mTextureMissileLeftToRight = new Texture(256, 32, TextureOptions.BILINEAR);
		this.mFaceTextureRegionMissileLeftToRight = TextureRegionFactory.createTiledFromResource(this.mTextureMissileLeftToRight, this, R.drawable.harpoonlefttoright, 0, 0, 1, 1);
		this.mEngine.getTextureManager().loadTexture(this.mTextureMissileLeftToRight);
		
		this.mTextureBlast = new Texture(512, 512, TextureOptions.BILINEAR);
		this.mFaceTextureRegionBlast = TextureRegionFactory.createTiledFromResource(this.mTextureBlast, this, R.drawable.explosion_a, 0, 0, 4, 4);
		this.mEngine.getTextureManager().loadTexture(this.mTextureBlast);
	}

	public Scene onLoadScene() {
		
		this.mEngine.registerUpdateHandler(new FPSLogger());
		scene = new Scene(1);
		
		// Initializing the Sprites and Text's 
		final Text scorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, 10, this.myFont, ScoreIncrement+"");
		final Text bestscorevalue = new Text(mFaceTextureRegionScore.getWidth() + 25, mFaceTextureRegionScore.getHeight()+ 15, this.myFont, ScoreIncrement+"");
		
		swipe = new Swipe(-1000, -1000, mFaceTextureRegionSwipe);
		swipeCount = swipe.getTextureRegion().getTileCount();
		swipe.animate(100);
		
		tap = new Tap(-1000, -1000, mFaceTextureRegionTap);
		tapCount = tap.getTextureRegion().getTileCount();
		tap.animate(100);
		
		lifeBoat_animate = new LifeBoat_Animate(CAMERA_WIDTH, (CAMERA_HEIGHT/2) - mFaceTextureRegionWaves.getHeight(), mFaceTextureRegionLifeBoat);
		bird = new Bird(0, -1000, this.mFaceTextureRegionBird);
		birdkilled = new BirdKilled(-1000, -1000, mFaceTextureRegionBirdKilled);
		subescaped = new SubEscaped(-1000,-1000, mFaceTextureRegionSubEscaped);
		blastoncollide = new BlastOnCollideShip(-1000, -1000, mFaceTextureRegionBlast);
		blastoncollide1 = new BlastOnCollideShip1(-1000, -1000, mFaceTextureRegionBlast);
		blastbackground = new BlastBackGround(-1000, -1000, mFaceTextureRegionBlastBackGround);
		GameOver = new AnimatedSprite((CAMERA_WIDTH/2)-(mFaceTextureRegionGameOver.getWidth())/2,(CAMERA_HEIGHT/2)-(mFaceTextureRegionGameOver.getHeight()/2) , mFaceTextureRegionGameOver);
		lifeline = new AnimatedSprite(CAMERA_WIDTH - (mFaceTextureRegionLifeLine.getWidth() + 10) , 5, mFaceTextureRegionLifeLine);
		lifeline1 = new AnimatedSprite(CAMERA_WIDTH - (mFaceTextureRegionLifeLine1.getWidth() + 10) , 5, mFaceTextureRegionLifeLine1);
		lifeline2 = new AnimatedSprite(CAMERA_WIDTH - (mFaceTextureRegionLifeLine1.getWidth() + 10) , 5, mFaceTextureRegionLifeLine2);
		lifeline3 = new AnimatedSprite(CAMERA_WIDTH - (mFaceTextureRegionLifeLine1.getWidth() + 10) , 5, mFaceTextureRegionLifeLine3);
		score = new Score(10, 10, mFaceTextureRegionScore);
		bestscore = new BestScore(10, mFaceTextureRegionScore.getHeight()+ 25, mFaceTextureRegionBestScore);
		mBackGroundImage1 = new BackGroundImage1(CAMERA_WIDTH - 5, 0, mFaceTextureRegionBackGround);
		mBackGroundImage = new BackGroundImage(0, 0, this.mFaceTextureRegionBackGroundOpposite);
		myWaves = new Waves(0, CAMERA_HEIGHT/2, mFaceTextureRegionWaves);
		myWaves1 = new Waves1(-(mFaceTextureRegionWaves1.getWidth() - 10),  CAMERA_HEIGHT/2, mFaceTextureRegionWaves1);
		myWaves.setVelocity(25, 0);
		mBackGroundImage.setVelocity(-25, 0);

		
		// Adding the Sprites and Text's 
		scene.getTopLayer().addEntity(mBackGroundImage1);
		scene.getTopLayer().addEntity(mBackGroundImage);
		scene.getTopLayer().addEntity(lifeBoat_animate);
		scene.getTopLayer().addEntity(myWaves);
		scene.getTopLayer().addEntity(myWaves1);
		scene.getTopLayer().addEntity(scorevalue);
		scene.getTopLayer().addEntity(bestscorevalue);
		scene.getTopLayer().addEntity(score);
		scene.getTopLayer().addEntity(bestscore);
		scene.getTopLayer().addEntity(lifeline);
		scene.getTopLayer().addEntity(blastbackground);
		scene.getTopLayer().addEntity(blastoncollide);
		scene.getTopLayer().addEntity(blastoncollide1);
		scene.getTopLayer().addEntity(subescaped);
		scene.getTopLayer().addEntity(birdkilled);
		
		ship = new ShipSub(0 - this.mFaceTextureRegionSub.getWidth(), ShipY, this.mFaceTextureRegionSub)
		{
			@Override
			public boolean onAreaTouched(TouchEvent event,float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// Checks that the ship has been touched for the first time.
				if(FlagTouchBlast == false){
					scene.setOnSceneTouchListener(null);
					angleofRotationShip = 0;
					blastbackground.setPosition(ship.getX() + 100, ship.getY() - 50);
					blastbackground.animate(100);
					scene.registerUpdateHandler(myhandlerblastbackground);
					FlagTouchBlast = true;
				}
				
				// store the starting point of TAP
				ballUpstartPointY = event.getY();
				// make the flag true for object moving UP
				flagBallUp = true;
				ship.setPosition(event.getX() - 50, event.getY() - 120);
				ship.setVelocity(0,-50f);
				
				//flag to rotate the object on TAP
				BallUpRotate = true;
				
				// setting OnTouchEvent along with Fling
				Game ballDemo = new Game();
				ballDemo.onTouchEvent(event.getMotionEvent());
				scene.unregisterTouchArea(ship);
				
				return true;
			}
		};
		
		ship1 = new ShipSub1(0 - this.mFaceSecondShip.getWidth(), ShipY - 60, this.mFaceSecondShip){
			
			@Override
			public boolean onAreaTouched(TouchEvent event,float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// Checks that the ship has been touched for the first time.
				if(FlagTouchBlast1 == false){
					scene.setOnSceneTouchListener(null);
					blastbackground.setPosition(ship1.getX() + 60, ship1.getY() - 50);
					blastbackground.animate(100);
					scene.registerUpdateHandler(myhandlerblastbackground);
					FlagTouchBlast1 = true;
				}
				
				// store the starting point of TAP
				ballUpstartPointY1 = event.getY();
				// make the flag true for object moving UP
				flagBallUp1 = true;
				ship1.setPosition(event.getX() - 50, event.getY() - 80);
				ship1.setVelocity(0,-50f);
				
				//flag to rotate the object on TAP
				BallUpRotate1 = true;
				
				// setting OnTouchEvent along with Fling
				Game ballDemo = new Game();
				ballDemo.onTouchEvent(event.getMotionEvent());
				scene.unregisterTouchArea(ship1);
				
				return true;
			}
		};
		return scene;
	}

	public boolean onSingleTapUp(MotionEvent event) {

		if (LifeLineDead != -1) {
			if (event.getY() <= (CAMERA_HEIGHT / 2)) {
				swipe.setPosition(event.getX(), event.getY());
				scene.getTopLayer().addEntity(swipe);
				scene.registerUpdateHandler(myhandlerSwipe);
			}
		}
		return false;
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent event) {

		// Checks if the scene touched is below the water level.
		if (event.getY() >= (CAMERA_HEIGHT / 2)) {

			// LifeLifeDead = -1 Means GameOver so there will be no effect on
			// Scene Touch.
			if (LifeLineDead != -1) {
				
				MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.splash);
				mediaPlayer.start();
				
				blastbackground.setPosition(event.getX() - 50, event.getY() - 100);
				blastbackground.animate(100);
				scene.registerUpdateHandler(myhandlerblastbackground);
			}
		}
		return false;
	}

	public void onLongPress(MotionEvent e) {
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	public void onShowPress(MotionEvent e) {
		
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.harpoon_whoosh);
		mPlayer.start();
		
		float c;
		// Checks if LifeLine is there or not and enable or disable Fling
		// Operation.
		if (LifeLineDead != -1) {

			if (e1.getY() <= (CAMERA_HEIGHT / 2)) {

				try {

					float sx = 0, sy = 0;
					float x1 = e1.getX();
					float y1 = e1.getY();

					float x2 = e2.getX();
					float y2 = e2.getY();

					float slope = (y2 - y1) / (x2 - x1);
					float angle = (float) Math.atan(slope);
					float angleInDegree = (float) Math.toDegrees(angle);

					c = y1 - (slope * x1);

					/**
					 * bottom right to left top
					 */
					if (x1 > x2 && y1 > y2) {

						Log.i("FLING", "bottom right to left top");

						sx = e2.getX();
						sy = (slope * sx) + c;

						missile = new Missile(sx, sy,
								this.mFaceTextureRegionMissileLeftToRight);
						missile.setVelocity(-(float) (600 * (Math.cos(angle))),
								-(float) (600 * (Math.sin(angle))));

						scene.getTopLayer().addEntity(missile);

						missile.setRotation(angleInDegree + 180);

					}
					/**
					 * left top corner to right
					 */
					else if (x2 > x1 && y2 > y1) {

						Log.i("FLING", "left top corner to right");

						sx = e1.getX();
						sy = (slope * sx) + c;
						missile = new Missile(sx, sy,
								this.mFaceTextureRegionMissileLeftToRight);
						missile.setVelocity((float) (300 * (Math.cos(angle))),
								(float) (300 * (Math.sin(angle))));
						scene.getTopLayer().addEntity(missile);
						missile.setRotation(angleInDegree);
					}
					/**
					 * left bottom corner to right up
					 */
					else if (x2 > x1 && y1 > y2) {

						Log.i("FLING", "left bottom corner to right up");

						sx = e1.getX();
						sy = (slope * sx) + c;
						missile = new Missile(sx, sy,
								this.mFaceTextureRegionMissileLeftToRight);
						missile.setVelocity((float) (300 * (Math.cos(angle))),
								(float) (300 * (Math.sin(angle))));
						scene.getTopLayer().addEntity(missile);
						missile.setRotation(angleInDegree);
					}

					/**
					 * top Right to left bottom down
					 */
					else if (x1 > x2 && y2 > y1) {

						Log.i("FLING", "Right corner to left bottom down");

						sx = e2.getX();
						sy = (slope * sx) + c;
						missile = new Missile(sx, sy,
								this.mFaceTextureRegionMissileLeftToRight);
						missile.setVelocity((float) (-600 * (Math.cos(angle))),
								-(float) (600 * (Math.sin(angle))));
						scene.getTopLayer().addEntity(missile);
						missile.setRotation(angleInDegree + 180);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				tap.setPosition(e1.getX(), e1.getY());
				scene.getTopLayer().addEntity(tap);
				scene.registerUpdateHandler(myhandlerTap);
			}
		}
		return false;
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}