package com.foc.fragments;

import static utilities.IntentFragmentLauncher.openActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.foc.RendererPattern.ProductListObserver;
import com.foc.RendererPattern.ProductListView;
import com.foc.activities.DetailsProductActivity;
import com.foc.model.Bought_Product;
import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.StoreProvider;
import com.foc.tarea4.R;

public class BoughtProducts_Fragment extends Fragment implements ProductListObserver{
	
	private ProductListView lview;
	
	public BoughtProducts_Fragment() {}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		setHasOptionsMenu(true);
		
		View view = inflater.inflate(R.layout.fragment_bought_products, container, false);
		lview = (ProductListView) view.findViewById(R.id.listView_bought_products);
		lview.init(StoreProvider.getBoughtProductStore(getActivity()).getList(), this, R.menu.delete);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		lview.notifyChanges(StoreProvider.getBoughtProductStore(getActivity()).getList());
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
	    super.onCreateOptionsMenu(menu,inflater);
	}

	@Override
	public void onListItemClick(int productCode) {
		ProductType productType = new Bought_Product();
		productType.setProduct(new Product(productCode));
		
		openActivity(getActivity(), DetailsProductActivity.class, productType);
	}

	@Override
	public void actionItemClicked(int itemId) {
		switch (itemId) {
		case R.id.action_delete_contextual:
			StoreProvider.getBoughtProductStore(getActivity()).remove(lview.getProductCodeSelected());
			break;
		default:
			break;
		}
	}

}