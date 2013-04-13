package com.aprendiendodeandroid.maps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MiLocationListener implements LocationListener{
	private Marker actual;
    private GoogleMap mapa;
    
    public void onLocationChanged(Location location) {
        // aca vamos a realizar una accion al tomar un nuevo valor
        float lat = (float) (location.getLatitude());
        float lng = (float) (location.getLongitude());
        LatLng latLng = new LatLng(lat, lng);
        mapa = MainActivity.mapa;
        actual = mapa.addMarker(new MarkerOptions().position(latLng).title(
        		"En esta parte de Rosario te encuentras RED"));

        Log.d("Localizacion", "Valor: " + location.toString() + " latitud: " + lat +
              " logitud: " + lng + "Valor al mapa" + latLng);
        
        CameraPosition cameraPosition = new CameraPosition.Builder()
  		.target(latLng)      // seteamos el centro del mapa en la posicion actual
  		.zoom(13)                   // configuramos el zoom
  		.bearing(90)                // seteamos la orientacion hacia el este
  		.tilt(30)                   // configuramos el angulo de la camara a 30 grados
  		.build();                   // Una vez seteado los parametros, construimos el objetos
  	      
        // ubicamos nuestra posicion en el mapa, para que sea centrado ahi, con un angulo y vista
        mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
  	
    }
    
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderDisabled(String provider) {
        // vamos a intentar cambiar con otros proveedores
        Log.d("Localizacion", "Esta desabilitado el proveedor");
    }
    public void onProviderEnabled(String s) {
        // vamos a intentar cambiar con otros proveedores
        Log.d("Localizacion", "Esta habilitado el proveedor");
    }

    /** esta criteria va a tener menos precision, consumir menos energia, y un menor costo */
    public static Criteria crearCriteriaRed() {
     
      Criteria c = new Criteria();
      c.setAccuracy(Criteria.ACCURACY_COARSE);
      c.setAltitudeRequired(false);
      c.setBearingRequired(false);
      c.setSpeedRequired(false);
      c.setCostAllowed(true);
      c.setPowerRequirement(Criteria.POWER_HIGH);
      return c;
    }
    
    public Marker getActual() {
        return actual;
    }

    public void setActual(Marker actual) {
        this.actual = actual;
    }

    public GoogleMap getMapa() {
        return mapa;
    }

    public void setMapa(GoogleMap mapa) {
        this.mapa = mapa;
    }
}
