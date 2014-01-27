package com.foc.components;

import com.foc.tarea4.R;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Drawer {
	
	private final Activity parent;
	private final DrawerObserver observer;
	private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence title;
    private String[] elementsTitles;
    
    public Drawer(Activity parent, DrawerObserver observer){
    	this.parent = parent;
    	this.observer = observer;
    }
    
    public void init(){
    	drawerTitle = parent.getTitle();
    	
        elementsTitles = parent.getResources().getStringArray(R.array.drawer_actions);
        title = elementsTitles[0];
        parent.setTitle(title);
        drawerLayout = (DrawerLayout) parent.findViewById(R.id.drawer_layout);
        drawerList = (ListView) parent.findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        drawerList.setAdapter(new ArrayAdapter<String>(parent,R.layout.drawer_list_item, elementsTitles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        parent.getActionBar().setDisplayHomeAsUpEnabled(true);
        parent.getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        drawerToggle = new ActionBarDrawerToggle(
                parent,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
            	parent.setTitle(title);
            	parent.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
            	parent.getActionBar().setTitle(drawerTitle);
            	parent.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }
    
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			selectItem(position);
		}
	}
    
    /** Swaps fragments in the main content view */
	private void selectItem(int position) {
		observer.positionClicked(position);

	    drawerList.setItemChecked(position, true);
	    title = elementsTitles[position];
	    drawerLayout.closeDrawer(drawerList);
	}
	
	public void syncState(){
		drawerToggle.syncState();
	}
	
	public void configurationChanged(Configuration newConfig) {
        drawerToggle.onConfigurationChanged(newConfig);
    }
	
	public boolean isOpen(){
		return drawerLayout.isDrawerOpen(drawerList);
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		return (drawerToggle.onOptionsItemSelected(item))  ? true : false;
	}
	
	public void setSelectItem(int position){
		selectItem(position);
	}
	
	public interface DrawerObserver{
		public void positionClicked(int position);
	}
	
}