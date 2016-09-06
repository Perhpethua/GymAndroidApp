package c.b.a.seminarjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayMenuActivity extends Activity {

	private Button zagrijavanjeButton;
	private Button kardioButton;
	private Button gornjiDioButton;
	private Button rukeButton;
	private Button nogeButton;
	private Button trupButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_menu);
		inicijalizirajApp();
	}

	private void inicijalizirajApp(){
		zagrijavanjeButton = (Button)findViewById(R.id.zagrijavanjeButton);
		kardioButton = (Button)findViewById(R.id.kardioButton);
		gornjiDioButton = (Button)findViewById(R.id.gornjiDioButton);
		rukeButton = (Button)findViewById(R.id.rukeButton);
		nogeButton = (Button)findViewById(R.id.nogeButton);
		trupButton = (Button)findViewById(R.id.trupButton);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_display_menu, menu);
		return true;
	}
//---------------------------POZIVANJE ACTIVITIJA------------------------------------------
	public void zagrijavanjeButton(View view){
		Intent intent = new Intent(this, ZagrijavanjeActivity.class);
		startActivity(intent);
	}
	public void kardioButton(View view){
		Intent intent = new Intent(this, KardioActivity.class);
		startActivity(intent);
	}
	public void gornjiDioButton(View view){
		Intent intent = new Intent(this, GornjiDioActivity.class);
		startActivity(intent);
	}
	public void rukeButton(View view){
		Intent intent = new Intent(this, RukeActivity.class);
		startActivity(intent);
	}
	public void nogeButton(View view){
		Intent intent = new Intent(this, NogeActivity.class);
		startActivity(intent);
	}
	public void trupButton(View view){
		Intent intent = new Intent(this, TrupActivity.class);
		startActivity(intent);
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
