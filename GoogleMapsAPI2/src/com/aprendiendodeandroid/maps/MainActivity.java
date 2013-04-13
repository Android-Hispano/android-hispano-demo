package com.aprendiendodeandroid.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.content.Context;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

/**
 * Esta clase es la principal que va a cargar nuestro layout, donde se encuentra el fragment que
 * va a poseer el mapa, extendemos de FragmentActivity pero de la version de libreria de soporte
 * para que funciones con versiones anteriore a Android 11
 * @author gabriel
 *
 */
public class MainActivity extends FragmentActivity {

	public static GoogleMap mapa;
	private LocationManager locationManager;
	private MiLocationListener locationListener = new MiLocationListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        
		// obtenemos el mapa desde el fragment
		mapa = (((SupportMapFragment)getSupportFragmentManager().
		        findFragmentById(R.id.map)).getMap());
		
		// seteamos para que este el boton de buscar nuestra ubicacion activado
		mapa.setMyLocationEnabled(true);
				
		iniciamosLaEscucha();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** 
	 hay que asegurarse de usar esta funcion en el hilo principal, y no en un hilo en background 
	 hay que asegurarse de remover las actualizaciones cuando no se use mas
	*/
	public void iniciamosLaEscucha(){
	  // baja precision
	  LocationProvider low=
	    locationManager.getProvider(locationManager.getBestProvider(MiLocationListener.
	            crearCriteriaRed(),false));
	 
		  // usamos el proveedor de baja precision... y le ponemos el listener para que lo actualice
		  locationManager.requestLocationUpdates(low.getName(), 0, 0f, locationListener);  
	}

	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de actualizacion	
	 */
	@Override
	protected void onStop() {
		super.onStop();
		locationManager.removeUpdates(locationListener);
	}
	
	/**
	 * Vamos a detener la escucha de nuestro listener y todo tipo de actualizacion 
	 */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(locationListener);
	}
	
	/**
	 * Volvemos a arrancar las actualizaciones
	 */
	@Override
	protected void onResume() {
		super.onResume();
		iniciamosLaEscucha();
	}  

}
