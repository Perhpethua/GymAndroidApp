package c.b.a.seminarjava;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static c.b.a.seminarjava.KardioActivity.*;


public class NogeActivity extends AppCompatActivity{

	private TextView jsonTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noge);

		jsonTextView = (TextView) findViewById(R.id.jsonTextView); //desni klik->refactor->extract->field
		new JSONTask().execute("https://raw.githubusercontent.com/Perhpethua/json/master/legsJson");

	}
	private class JSONTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... urls) {

			//spajanje na server
			HttpURLConnection connection = null;
			BufferedReader bufferedReader = null; //inicjalizacija da bi se vidio u finally bloku
			//url
			try {
				URL url = new URL(urls[0]); //new URL(urls[0]); gleda prvi ulazni vrijednost od AsyncTask i doInBackground
				connection = (HttpURLConnection) url.openConnection();//otvaranje veze po zadanom url
				connection.connect();//spajanje

				//spremanje
				InputStream inputStream = connection.getInputStream();
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

				String line="";
				StringBuffer stringBuffer = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null){
					stringBuffer.append(line);
				}
//----------------------------------parsingJSON----------------------------------------------------------------------------
				String finalJSON = stringBuffer.toString();
//----------------------------------KREIRANJE OBJEKATA I NIZOVA------------------------------------------------------------
				//u JSON fileu dohvacamo json object->create
				JSONObject parentObject = new JSONObject(finalJSON);//prva vitičasta zagrada
				// UNUTAR JE niz
				JSONArray parentArray = parentObject.getJSONArray("noge");
				//unutar niza je novi object
				JSONObject finalObject = parentArray.getJSONObject(0);
				//--------------------------------podatci koje dohvaća-------------------------------------------------------------
				String vjezbaZa = finalObject.getString("Vježba_za");//u navodnicima-->key
				String brojPonavljanja = finalObject.getString("Broj_ponavljanja");
				String spravaKorištenje = finalObject.getString("Sprava_korištenje");
				String tezina = finalObject.getString("Težina");
				String pazitiNa = finalObject.getString("Paziti na");
				String tip = finalObject.getString("Tip");
				String puls = finalObject.getString("Puls");
				//ispis
				return vjezbaZa + "\n" + brojPonavljanja + "\n" + spravaKorištenje + "\n" + tezina + "\n" + pazitiNa + "\n" + tip + "\n" + puls; // da bi ispisao podatke -> šalje se u onPostExecute
//------------------------------------endParsingJson------------------------------------------------------------------------
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {//provjera da je veza uspostavljena
					connection.disconnect();
				}
				if (bufferedReader != null) {//ako je nula--> trebalo bi ga inicjalizirat -->nijw potrebno zatvarati
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {//izvodi se u main thread
			super.onPostExecute(result);

//--------------------------dohvacanje teksta iz buffera->konverzija------------------------------------------------
			jsonTextView.setText(result);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_noge, menu);
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
