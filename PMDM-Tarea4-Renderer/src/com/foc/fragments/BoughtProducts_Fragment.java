package com.foc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.model.BoughtProductStore;
import com.foc.model.Store;
import com.foc.tarea4.R;

public class BoughtProducts_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	private Store<?> store;
	
	public BoughtProducts_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);  
		store = BoughtProductStore.getStore();
		
		View view = inflater.inflate(R.layout.fragment_bought_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_bought_products);
		lview.init(store.getList(), this, R.menu.delete);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.notifyChanges(store.getList());
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
	    super.onCreateOptionsMenu(menu,inflater);
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
		default:
			break;
		}
	}

}