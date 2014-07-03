package uk.co.happycaffeine.fartingstatues;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView statueImageButton1, statueImageButton2, statueImageButton3,
			statueImageButton4, statueImageButton5, statueImageButton6;
	Button creditsButton;

	static String TAG = "MainActivity";
	public final static String EXTRA_MESSAGE = "uk.co.fartingstatues.MESSAGE"; // string
																				// for
																				// intent
																				// message

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onResume() {
		super.onResume();

		
		creditsButton = (Button) findViewById(R.id.creditsButton);
		
		//BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inJustDecodeBounds = true;
		
		

		statueImageButton1 = (ImageView) findViewById(R.id.statueImageButton1);
		statueImageButton1.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.the_bather_original_small, 200, 200));

		statueImageButton2 = (ImageView) findViewById(R.id.statueImageButton2);
		statueImageButton2.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.moai_rano_raraku_small, 200, 200));

		statueImageButton3 = (ImageView) findViewById(R.id.statueImageButton3);
		statueImageButton3.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.thethinker_small, 200, 200));

		statueImageButton4 = (ImageView) findViewById(R.id.statueImageButton4);
		statueImageButton4.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.dacredolphin_small, 200, 200));

		statueImageButton5 = (ImageView) findViewById(R.id.statueImageButton5);
		statueImageButton5.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.reclining_figure_small, 200, 200));

		statueImageButton6 = (ImageView) findViewById(R.id.statueImageButton6);
		statueImageButton6.setImageBitmap(decodeSampledBitmapFromResource(
				getResources(), R.drawable.montemhat_small, 200, 200));
		
		
		
		creditsButton.setOnClickListener(new  View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
				MainActivity.this.startActivity(intent);
				
				
			}

			
		});

		statueImageButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton1 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "1";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				System.gc();
				MainActivity.this.startActivity(intent);

			}

		});

		statueImageButton2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton2 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "2";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				MainActivity.this.startActivity(intent);

			}

		});

		statueImageButton3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton3 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "3";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				MainActivity.this.startActivity(intent);

			}

		});

		statueImageButton4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton4 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "4";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				MainActivity.this.startActivity(intent);

			}

		});

		statueImageButton5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton5 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "5";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				MainActivity.this.startActivity(intent);

			}

		});

		statueImageButton6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG, "statueImageButton6 pressed");

				Intent intent = new Intent(MainActivity.this, Statue.class);
				String statueSelected = "6";
				intent.putExtra(EXTRA_MESSAGE, statueSelected);

				MainActivity.this.startActivity(intent);

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
