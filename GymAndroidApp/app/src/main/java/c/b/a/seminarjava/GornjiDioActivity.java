package c.b.a.seminarjava;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class GornjiDioActivity extends AppCompatActivity {
//---------------------------deklaracija------------------------------------------------------------
	Button startChronometerButton;
	Button pauseChronometerButton;
	Button resetChronometerButton;
	Chronometer timeWaitingChronometer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gornji_dio);

		startChronometerButton = (Button)findViewById(R.id.startChronometerButton);
		pauseChronometerButton = (Button)findViewById(R.id.pauseChronometerButton);
		resetChronometerButton = (Button)findViewById(R.id.resetChronometerButton);

		setButtonOnClickListeners();

		timeWaitingChronometer = (Chronometer)findViewById(R.id.timeWaitingChronometer);
	}


	private void setButtonOnClickListeners() {
		//botuni START PAUSE RESET --> mjenjaju chronometer
		startChronometerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//kad se stisne START BUTTON --> izvodi se:
//dobivanje stringa iz chronometer
				String chronoText = timeWaitingChronometer.getText().toString();
				String array[] = chronoText.split(":");
				timeWaitingChronometer.start(); // start chronometer

			}
		});
		pauseChronometerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//kad je PAUSE button kliknut
				timeWaitingChronometer.stop();
			}
		});

		resetChronometerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//kad je RESET button kliknut - elapsedRealtime - Returns milliseconds since boot, including time spent in sleep.
				timeWaitingChronometer.setBase(SystemClock.elapsedRealtime()); // set back to 0
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_gornji_dio, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
