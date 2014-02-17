package com.foc.RendererPattern;

import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import com.foc.adapters.Adapter;
import com.foc.model.ProductType;
import com.foc.tarea4.R;

public class ProductListView extends ListView implements OnItemClickListener, OnItemLongClickListener, OnClickListenerProvider{
	
	private ArrayList<ProductType> data;
	private ArrayList<Integer> productCodeSelected;
	private Adapter adapter;
	private ProductListObserver observer;
	private int idContextualMenu;
	
	public ProductListView(Context context) {
		super(context);
	}
	
	public ProductListView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public ProductListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}

	public void init(ArrayList<ProductType> data, ProductListObserver observer, int idContextualMenu){;
		this.observer = observer;
		this.data = data;
		this.idContextualMenu = idContextualMenu;
		initialize();
	}
	
	private void initialize(){
		adapter = new Adapter(getContext(), data, this);
		productCodeSelected = new ArrayList<Integer>();
		setAdapter(adapter);
		setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				clearSelection();
				deselectItems();
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				clearSelection();
				mode.getMenuInflater().inflate(idContextualMenu, menu);
				return true;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				observer.actionItemClicked(item.getItemId());
				close(mode);
				return false;
			}
			
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
				// funciona este checked o necesito preguntar si la posicion esta chaked o no
				if(checked)
					setNewSelection(position);
				else
					removeSelection(position);
				mode.setTitle(getSelectedClount() +" seleccionados");
			}
			
			private void close(ActionMode mode){
				clearSelection();
				mode.finish();
			}
		});
		setOnItemClickListener(this);
		setOnItemLongClickListener(this);
	}
	
	public ArrayList<Integer> getProductCodeSelected() {
		return productCodeSelected;
	}

	protected void setNewSelection(int code) {
		productCodeSelected.add(code);
	}

	protected void clearSelection() {
		productCodeSelected.clear();
		deselectItems();
	}
	
	protected void removeSelection(int code){
		for(Integer i : productCodeSelected)
			if(i == code)
				productCodeSelected.remove(i);
	}
	
	protected int getSelectedClount(){
		return productCodeSelected.size();
	}
	
	protected boolean isPositionChecked(int position){
		return productCodeSelected.contains(position);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int position,long arg3) {
		itemChecked(view, position);
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ProductType product = (ProductType) adapter.getItem(position);
		observer.onListItemClick(product.getProductCode());
	}
	
	@Override
	public OnClickListener getOnClickListener(int elementCode) {
		return new ImageViewClickListener(elementCode);
	}
	
	private void itemChecked(View view, int position) {
		setItemChecked(position, !isPositionChecked(position));
		if(!isPositionChecked(position))
			uncheckItem(view);
		else
			checkItem(view);
	}
	
	public void notifyChanges(ArrayList<ProductType> list){
		data = list;
		adapter.notifyDataSetChanged();
		deselectItems();
	}
	
	private void deselectItems(){
		for(int i = 0; i < getChildCount(); i++)
			uncheckItem(getChildAt(i));
	}
	
	private void checkItem(View view){
		view.setBackgroundColor(getResources().getColor(R.color.checked_color));
	}
	
	private void uncheckItem(View view){
		view.setBackgroundColor(getResources().getColor(android.R.color.transparent));
	}
	
	private class ImageViewClickListener implements OnClickListener{
		
		private int elementPosition;
		
		public ImageViewClickListener (int elementPosition){
			this.elementPosition = elementPosition;
		}
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.product_icon:
				itemChecked((View) v.getParent(), elementPosition);
				break;
			}
		}
		
	}

}