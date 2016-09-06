package c.b.a.seminarjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText visinaEditText;
	private EditText tezinaEditText;
	private EditText godineEditText;
	private Button izracunajBMIButton;
	private TextView BMITextView;
	private TextView BMIStatusTexView;

	private double visina = 0;
	private double tezina = 0;

	//public final static String EXTRA_MESSAGE = "c.b.a.seminarjava.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inicijalizirajApp();
	}


	private void inicijalizirajApp(){


// -------create text area and place it in new view or new activity-------------------------------------
		tezinaEditText = (EditText) findViewById(R.id.tezinaEditText);
		visinaEditText = (EditText) findViewById(R.id.visinaEditText);
		godineEditText = (EditText) findViewById(R.id.godineEditText);
		BMITextView = (TextView) findViewById(R.id.BMITextView);
		BMIStatusTexView = (TextView) findViewById(R.id.BMIStatusTextView);
		izracunajBMIButton = (Button) findViewById(R.id.izracunajBMIButton);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
//------------------------------------OTVARA DisplayMenuActivity---------------------------------
// kreniButton
	public void kreniButton(View view){//funkcija definirana u xml -->button-->onClick

		Intent intent = new Intent(this,DisplayMenuActivity.class);//this je referenca na MainActivity

//create LinearLayout area and place it in new view or new activity
//LinearLayout linearLayout =(LinearLayout) findViewById(R.id.opcije);
		startActivity(intent);
	}


	public void izracunajBMI(View view){
		String status = null;
		//Log.i("IzracunajBMI", "pressed Button");
		//prvo retreive data from fields
		try{
			visina = (Double.parseDouble(visinaEditText.getText().toString())/100);
			tezina = Double.parseDouble(tezinaEditText.getText().toString());
		}catch (Exception e){
			e.printStackTrace();
		}
		double bmi = (double) (tezina/(visina * visina)); //*703 za lb
		String result = String.format("%.2f", bmi);
		Log.d("MainActivity", result);


//PROMJENA VRIJEDNOSTI BMITextView-a
// TextView.BufferType.NORMAL sadrzi kojeg tipa je data poslana na ekran
		BMITextView.setText(result, TextView.BufferType.NORMAL);

		if (bmi < 16.0){
			status = "Serously Underweight";
		}else if (bmi >= 16.0 && bmi < 18.5){
			status = "Underweight";
		}else if (bmi >=18.5 && bmi < 25.0){
			status = "Normal";
		}else if (bmi >= 25 && bmi < 30.0){
			status ="Overweight";
		}else if (bmi >= 30 && bmi <= 35){
			status = "Seriously Overweight";
		}else if (bmi > 35){
			status = "Gravely Overweight";
		}
		BMIStatusTexView.setText(status);
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
