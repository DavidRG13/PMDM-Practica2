package com.foc.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.foc.RendererPattern.Renderer;
import com.foc.RendererPattern.RendererBuilder;
import com.foc.tarea4.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Adapter<T> extends ArrayAdapter<T>{
	
	private RendererBuilder<T> prb;
	private ArrayList<T> datos;
	private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();
	
	public Adapter(RendererBuilder<T> prb, ArrayList<T> datos) {
		super(prb.getContext(), R.layout.list_product_view, datos);
		this.datos = datos;
		this.prb = prb;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		T product = super.getItem(position);
		Renderer productRenderer = prb.build(convertView, product, position);
		return productRenderer.render(product);
	}
	
	@Override
	public int getItemViewType(int position) {
		T product = getItem(position);
		return prb.getViewItemType(product);
	}
	
	@Override
	public int getViewTypeCount() {
		return datos.size();
	}
	
	public void setNewSelection(int position, boolean value) {
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

}