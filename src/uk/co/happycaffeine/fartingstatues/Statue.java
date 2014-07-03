package uk.co.happycaffeine.fartingstatues;
 
//import java.util.HashMap;
import java.util.Random;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Statue extends Activity {

	SoundPool mSoundPool;
	AudioManager mAudioManager;
	//HashMap<Integer, Integer> mSoundPoolMap;
	SparseIntArray mSoundPoolMap;
	static int FART_SOUND1 = 1;
	static int FART_SOUND2 = 2;
	final static int LOOP_1_TIME = 0;
	final static int LOOP_3_TIMES = 2;
	String statueSelected;
	String[] statueFacts;
	String[] statueNames;
	String statueNameString;
	String FactString = "oops no facts found";
	
	private AdView mAdview;
	
	ImageButton fart_Button;

	static String TAG = "Statue activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();

		System.gc();
		setContentView(R.layout.activity_statue);

		statueSelected = (intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
		Log.i(TAG, "Statue Selected = " + statueSelected);

		Integer statueId = Integer.parseInt(statueSelected);
		// get a reference to the image in the layout
		
		
		ImageView statueImage = (ImageView) findViewById(R.id.imageStatue);

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.thebather,
				options);
		
		
		
		Resources resources;
		resources = getResources();
		statueNames = resources.getStringArray(R.array.statue_names);
		
		
		TextView statueName  = (TextView) findViewById(R.id.textView1);

		// Dispay the correct statue image

		switch (statueId) {

		case 1:
			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.the_bather_original_small, 200, 200));
			
			statueFacts = resources.getStringArray(R.array.statue_1_facts);
			statueName.setText(statueNames[0]);

			

			break;
		case 2:
			
			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.moai_rano_raraku_small, 200, 200));
			
			
			statueFacts = resources.getStringArray(R.array.statue_2_facts);
			
			statueName.setText(statueNames[1]);
			
			
			
			
			break;
		case 3:
			statueImage.setImageResource(R.drawable.thethinker_small);
			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.thethinker_small, 200, 200));
			
			statueFacts = resources.getStringArray(R.array.statue_3_facts);
			statueName.setText(statueNames[2]);
			
			break;
		case 4:
			
			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.dacredolphin_small, 200, 200));
			
			statueFacts = resources.getStringArray(R.array.statue_4_facts);
			
			statueName.setText(statueNames[3]);
			break;
		case 5:

			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.reclining_figure_small, 200, 200));
			
			statueFacts = resources.getStringArray(R.array.statue_5_facts);
			
			statueName.setText(statueNames[4]);
			break;
		case 6:

			statueImage.setImageBitmap(decodeSampledBitmapFromResource(
					getResources(), R.drawable.montemhat_small, 200, 200));
			
			statueFacts = resources.getStringArray(R.array.statue_6_facts);
			
			statueName.setText(statueNames[5]);
			break;

		}

		fart_Button = (ImageButton) findViewById(R.id.fartButton);

		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		//mSoundPoolMap = new HashMap<Integer, Integer>();
		mSoundPoolMap = new SparseIntArray();
		final Random randomGenerator = new Random();

		mSoundPoolMap.put(FART_SOUND1, mSoundPool.load(this, R.raw.fart1, 1));

		mSoundPoolMap.put(FART_SOUND2, mSoundPool.load(this, R.raw.fart2, 1));

		fart_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				int randomFact = randomGenerator.nextInt(6);
				FactString = statueFacts[randomFact];
				
				

				Log.i(TAG, "random fact chosen  " + randomFact);


				Toast.makeText(getApplicationContext(), FactString,
						Toast.LENGTH_LONG).show();

				float streamVolume = mAudioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				int randomFart = 1 + randomGenerator.nextInt(2);
				Log.i(TAG, "random fart chosen = " + randomFart);
				mSoundPool.play(mSoundPoolMap.get(randomFart), streamVolume,
						streamVolume, 1, LOOP_1_TIME, 1f);

			}
		});
		mAdview = (AdView) findViewById(R.id.adView);
	/*	
		AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
	    .addTestDevice("F86F22095D01E041B8DD1242E1EC9C42") // My Nexus 7 tablet
	    .build();
	   */
		
		AdRequest adRequest = new AdRequest.Builder().build();
		

	    // Start loading the ad in the background.
		mAdview.loadAd(adRequest);

	}
	
	
	

	  @Override
	  public void onResume() {
	    super.onResume();
	    if (mAdview != null) {
	    	mAdview.resume();
	    }
	  }
	  
	  @Override
	  public void onPause() {
	    if (mAdview != null) {
	    	mAdview.pause();
	    }
	    super.onPause();
	  }
	  
	  //** Called before the activity is destroyed. */
	  @Override
	  public void onDestroy() {
		  
		  super.onDestroy();
	    // Destroy the AdView.
	    if (mAdview != null) {
	    	mAdview.destroy();
	    }
	    
	  }

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		// 1st decode with inJustDecodeBounds = true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// calculate inSampleSize;
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode Bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);

	}

}
