package com.foc.fragments;

import static utilities.IntentFragmentLauncher.openActivity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.activities.AddProductActivity;
import com.foc.activities.DetailsProductActivity;
import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.Store;
import com.foc.model.StoreProvider;
import com.foc.model.ToBuy_Product;
import com.foc.tarea4.R;

public class ProductsToBuy_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	private Store store;
	
	public ProductsToBuy_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);  
		store = StoreProvider.getToBuyProductStore(getActivity());
		
		View view = inflater.inflate(R.layout.fragment_tobuy_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_tobuy_products);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.init(store.getList(), this, R.menu.delete_bought);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	        case R.id.action_add:
	        	openActivity(getActivity(), AddProductActivity.class, new ToBuy_Product());
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onListItemClick(int productCode) {
		ProductType productType = new ToBuy_Product();
		productType.setProduct(new Product(productCode));
		
		openActivity(getActivity(), DetailsProductActivity.class, productType);
	}

	@Override
	public void actionItemClicked(int itemId) {
		switch (itemId) {
		case R.id.action_delete_contextual:
			store.remove(lview.getProductCodeSelected());
			break;
		case R.id.action_bought_contextual:
			ArrayList<ProductType> list = store.getPositions(lview.getProductCodeSelected());
			StoreProvider.getBoughtProductStore(getActivity()).addProduct(list);
			Toast.makeText(getActivity(), "Los productos marcados se han marcado como marcados.", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

}