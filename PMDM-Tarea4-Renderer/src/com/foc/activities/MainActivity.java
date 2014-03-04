package com.foc.activities;

import utilities.IntentFragmentLauncher;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.foc.components.Drawer;
import com.foc.components.Drawer.DrawerObserver;
import com.foc.fragments.AllProducts_Fragment;
import com.foc.fragments.BoughtProducts_Fragment;
import com.foc.fragments.ProductsToBuy_Fragment;
import com.foc.tarea4.R;

public class MainActivity extends FragmentActivity implements DrawerObserver{
	
	private Drawer drawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		IntentFragmentLauncher.replaceFragment(this, R.id.main, new ProductsToBuy_Fragment());
		
		drawer = new Drawer(this, this);
		drawer.init();
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawer.configurationChanged(newConfig);
    }
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawer.isOpen();
        for(int i = 0; i < menu.size(); i++)
        	menu.getItem(i).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawer.onOptionsItemSelected(item))
	          return true;
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void positionClicked(int position) {
	    Fragment fragmentActual = IntentFragmentLauncher.getVisibleFragment(this);
	    Fragment fragment = null;
	    switch(position){
	    case 2:
	    	if( ! (fragmentActual instanceof AllProducts_Fragment))
	    		fragment = new AllProducts_Fragment();
	    	break;
	    case 1:
	    	if( ! (fragmentActual instanceof BoughtProducts_Fragment))
	    		fragment = new BoughtProducts_Fragment();
	    	break;
	    case 0:
	    	if( ! (fragmentActual instanceof ProductsToBuy_Fragment))
	    		fragment = new ProductsToBuy_Fragment();
	    	break;
	    }
	    IntentFragmentLauncher.replaceFragment(this, R.id.main, fragment);
	}

	@Override
	public void setTitle(CharSequence title) {
	    getActionBar().setTitle(title);
	}

}