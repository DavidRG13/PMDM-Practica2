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
import com.foc.model.General_Product;
import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.StoreProvider;
import com.foc.tarea4.R;

public class AllProducts_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	
	public AllProducts_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);
		
		View view = inflater.inflate(R.layout.fragment_all_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_all_products);
		lview.init(StoreProvider.getProductStore(getActivity()).getList(), this, R.menu.delete_buy);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.notifyChanges(StoreProvider.getProductStore(getActivity()).getList());
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
			StoreProvider.getProductStore(getActivity()).remove(lview.getProductCodeSelected());
			break;
		case R.id.action_tobuy_contextual:
			ArrayList<ProductType> list = StoreProvider.getProductStore(getActivity()).getPositions(lview.getProductCodeSelected());
			StoreProvider.getToBuyProductStore(getActivity()).addProduct(list);
			Toast.makeText(getActivity(), "Se han apuntado los productos seleccionados para comprar.", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

}