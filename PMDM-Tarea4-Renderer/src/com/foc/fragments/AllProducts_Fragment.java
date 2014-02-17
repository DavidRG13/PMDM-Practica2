package com.foc.fragments;

import static utilities.IntentFragmentLauncher.openActivity;
import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.activities.AddProductActivity;
import com.foc.activities.DetailsProductActivity;
import com.foc.model.General_Product;
import com.foc.model.Product;
import com.foc.model.ProductStore;
import com.foc.model.ProductType;
import com.foc.tarea4.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class AllProducts_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	
	public AllProducts_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);
		
		View view = inflater.inflate(R.layout.fragment_all_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_all_products);
		lview.init(ProductStore.getStore().getList(), this, R.menu.delete_buy);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.notifyChanges(ProductStore.getStore().getList());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_add:
	        	openActivity(getActivity(), AddProductActivity.class, new General_Product());
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onListItemClick(int productCode) {
		ProductType productType = new General_Product();
		productType.setProduct(new Product(productCode));
		
		openActivity(getActivity(), DetailsProductActivity.class, productType);
	}
	
	@Override
	public void actionItemClicked(int itemId) {
		switch (itemId) {
		case R.id.action_delete_contextual:
			ProductStore.getStore().remove(lview.getProductCodeSelected());
			break;
		case R.id.action_buy_contextual:
			//TODO buy products
			break;
		default:
			break;
		}
	}

}