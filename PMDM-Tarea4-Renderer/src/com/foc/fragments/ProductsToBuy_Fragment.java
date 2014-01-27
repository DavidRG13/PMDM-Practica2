package com.foc.fragments;

import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.model.Store;
import com.foc.model.ToBuyProductStore;
import com.foc.tarea4.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ProductsToBuy_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	private Store<?> store;
	
	public ProductsToBuy_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);  
		store = ToBuyProductStore.getStore();
		
		View view = inflater.inflate(R.layout.fragment_tobuy_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_tobuy_products);
		lview.init(store.getList(), this, R.menu.delete_bought);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.notifyChanges(store.getList());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	        case R.id.action_add:
	            //IntentFragmentLauncher.openAddActivity(this);
	        	Toast.makeText(getActivity(), "mas pulsado", Toast.LENGTH_LONG).show();
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
		//TODO replace with correct actions
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

}