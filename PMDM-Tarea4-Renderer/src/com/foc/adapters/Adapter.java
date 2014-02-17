package com.foc.adapters;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.foc.RendererPattern.OnClickListenerProvider;
import com.foc.model.ProductType;
import com.foc.tarea4.R;

public class Adapter extends ArrayAdapter<ProductType>{
	
//	private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();
	private OnClickListenerProvider onClickListenerProvider;
	
	public Adapter(Context context, ArrayList<ProductType> datos, OnClickListenerProvider onClickListenerProvider) {
		super(context, R.layout.list_product_view, datos);
		this.onClickListenerProvider = onClickListenerProvider;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ProductType product = getItem(position);
		product.setContext((Activity) getContext());
		product.setView(convertView);
		product.setListener(onClickListenerProvider.getOnClickListener(position));
		return product.render();
	}
	
	@Override
	public int getItemViewType(int position) {
		return IGNORE_ITEM_VIEW_TYPE;
	}
	
	@Override
	public int getViewTypeCount() {
		return getCount();
	}
	
//	public void setNewSelection(int position, boolean value) {
//        mSelection.put(position, value);
//    }

//    public boolean isPositionChecked(int position) {
//        Boolean result = mSelection.get(position);
//        return result == null ? false : result;
//    }

//    public Set<Integer> getCurrentCheckedPosition() {
//        return mSelection.keySet();
//    }
//
//    public void removeSelection(int position) {
//        mSelection.remove(position);
//    }
//
//    public void clearSelection() {
//        mSelection = new HashMap<Integer, Boolean>();
//    }

}