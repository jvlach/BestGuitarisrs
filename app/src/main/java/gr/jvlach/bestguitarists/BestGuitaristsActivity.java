package gr.jvlach.bestguitarists;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.apache.cordova.CordovaActivity;

public class BestGuitaristsActivity extends CordovaActivity {
	private AdView adView;
	private InterstitialAd interstitial;
	private static final String PREFS_NAME
    = "gr.jvlach.bestguitarists.BestGuitaristsActivity";
	private boolean isNetworkConnected() {
		 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(
		        Context.CONNECTIVITY_SERVICE);

		    NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    if (wifiNetwork != null && wifiNetwork.isConnected()) {
		      return true;
		    }

		    NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    if (mobileNetwork != null && mobileNetwork.isConnected()) {
		      return true;
		    }

		    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		    if (activeNetwork != null && activeNetwork.isConnected()) {
		      return true;
		    }

		    return false;
		  
		  
		  
		 }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String appname=getResources().getString(R.string.app_name);
        
        if (appname.startsWith("71")){
        	super.loadUrl("file:///android_asset/www/index_en.html");
        }else{
        	super.loadUrl("file:///android_asset/www/index.html");
        }

        

        // Create the interstitial.
   	    interstitial = new InterstitialAd(this);
   	    interstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id_inter));
   	    
           SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
       	int shown=settings.getInt("shown", 0);
       	//Log.d("shown",""+shown);
       	SharedPreferences.Editor editor = settings.edit();
       	if (shown>1){//load ad
       		
       		loadInterstitial();
       		editor.putInt("shown", 0);
       		
       	}else{
       		shown++;
       		
               editor.putInt("shown", shown);
               
       	}
       	editor.commit();
        
        
    }
    
    
    
    @Override
	public void onResume(){
		super.onResume();
		
	    if (adView != null) {
	    	adView.resume();
	    }
	  }

	  @Override
	  public void onPause() {
	    if (adView != null) {
	    	adView.pause();
	    }
	    super.onPause();
	  }
	  
	  @Override
	  public void onDestroy() {
	    // Destroy the AdView.
	    if (adView != null) {
	    	adView.destroy();
	    }
	    displayInterstitial();
	    super.onDestroy();
	  }
	  
	  
	//v. 2.4:
		public void loadInterstitial() {
		    
			if (!isNetworkConnected()){
				return;
			}
			
		    // Check the logcat output for your hashed device ID to get test ads on a physical device.
		    AdRequest adRequest = new AdRequest.Builder()
		        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		        .build();
		    	//.addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
		    // Load the interstitial ad.
		    interstitial.loadAd(adRequest);
		    //Log.d("interstitial","interstitial loaded");
		  }
		
		
		// Invoke displayInterstitial() when you are ready to display an interstitial.
		 public void displayInterstitial() {
		    if (interstitial.isLoaded()) {
		      interstitial.show();
		      Log.d("interstitial","interstitial show");
		    }else{
		    	Log.d("interstitial","interstitial not shown");
		    }
		    
		  }
		 @Override
		 public void onWindowFocusChanged (boolean hasFocus){
			 Log.e("best guitar ads", "focus="+hasFocus);
			 if (!hasFocus){
				return; 
			 }
			 if (adView == null){
				 return;
			 }
			 
		 }
    
    
    
}