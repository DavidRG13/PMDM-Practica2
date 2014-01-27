package com.foc.fragments;

import java.io.Serializable;
import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.activities.AddProductActivity;
import com.foc.model.ProductStore;
import com.foc.tarea4.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AllProducts_Fragment extends Fragment implements ProductListObserver{
	
	private View view;
	private ProductListView lview;
	
	public AllProducts_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);
		
		view = inflater.inflate(R.layout.fragment_all_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_all_products);
		lview.init(ProductStore.getStore().getList(), this, R.menu.delete_buy);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		Log.e("AQUIIII", "on resume del allFragment");
		Log.e("AQUIIII", "tamanio aqui  "+ ProductStore.getStore().getList().size());
		Log.e("AQUIIII", "instancia "+ ProductStore.getStore().toString());
		lview.notifyChanges(ProductStore.getStore().getList());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_add:
	            openAddActivity();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onListItemClick(int productCode) {
		
	}
	
	@Override
	public void actionItemClicked(int itemId) {
		switch (itemId) {
		case R.id.action_delete_contextual:
			//TODO delete products
			break;
		case R.id.action_buy_contextual:
			//TODO buy products
			break;
		default:
			break;
		}
	}
	
	private void openAddActivity(){
		Intent intent = new Intent(getActivity(), AddProductActivity.class);
		intent.putExtra("store", (Serializable) ProductStore.getStore());
		startActivity(intent);
	}

}