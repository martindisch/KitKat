package com.martin.kitkat;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvLorem, tvA, tvB;
	private ViewGroup container;
	private Button changeScene;
	private Scene current;
	private Scene other;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		container = (ViewGroup) findViewById(R.id.container);

		current = Scene.getSceneForLayout(container, R.layout.text01, this);
		other = Scene.getSceneForLayout(container, R.layout.text02, this);

		current.enter();

		changeScene = (Button) findViewById(R.id.bChange);
		changeScene.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Scene tmp = other;
				other = current;
				current = tmp;

				TransitionManager.go(current);
				tvA = (TextView) current.getSceneRoot().findViewById(R.id.textA);
				tvB = (TextView) current.getSceneRoot().findViewById(R.id.textB);
				
				tvA.setTypeface(Typefaces.get(getApplicationContext(), "fonts/Rufina-Regular.ttf"));
				tvB.setTypeface(Typefaces.get(getApplicationContext(), "fonts/Rufina-Regular.ttf"));
			}
		});
		tvA = (TextView) container.findViewById(R.id.textA);
		tvB = (TextView) container.findViewById(R.id.textB);
		tvLorem = (TextView) findViewById(R.id.tvLorem);
		tvLorem.setTypeface(Typefaces.get(this, "fonts/Rufina-Regular.ttf"));
		tvA.setTypeface(Typefaces.get(this, "fonts/Rufina-Regular.ttf"));
		tvB.setTypeface(Typefaces.get(this, "fonts/Rufina-Regular.ttf"));
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
