package com.martin.kitkat;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvLorem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvLorem = (TextView) findViewById(R.id.tvLorem);
		tvLorem.setTypeface(Typefaces.get(this, "fonts/Rufina-Regular.ttf"));
		tvLorem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleHideyBar();
			}
		});

	}

	public void toggleHideyBar() {

		// The UI options currently enabled are represented by a bitfield.
		// getSystemUiVisibility() gives us that bitfield.
		int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
		int newUiOptions = uiOptions;

		// Navigation bar hiding:  Backwards compatible to ICS.
		if (Build.VERSION.SDK_INT >= 14) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		}

		// Status bar hiding: Backwards compatible to Jellybean
		if (Build.VERSION.SDK_INT >= 16) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
		}

		// Immersive mode: Backward compatible to KitKat.
		// Note that this flag doesn't do anything by itself, it only augments the behavior
		// of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
		// all three flags are being toggled together.
		// Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
		// Sticky immersive mode differs in that it makes the navigation and status bars
		// semi-transparent, and the UI flag does not get cleared when the user interacts with
		// the screen.
		if (Build.VERSION.SDK_INT >= 18) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}
		getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
	}

}
