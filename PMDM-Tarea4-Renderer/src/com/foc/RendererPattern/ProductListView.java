package com.foc.RendererPattern;

import java.util.ArrayList;

import com.foc.adapters.Adapter;
import com.foc.model.Product;
import com.foc.tarea4.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class ProductListView extends ListView implements OnItemClickListener, OnItemLongClickListener, OnClickListenerProvider{
	
	private ArrayList data;
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

	public void init(ArrayList data, ProductListObserver observer, int idContextualMenu){;
		this.observer = observer;
		this.data = data;
		this.idContextualMenu = idContextualMenu;
		initialize();
	}
	
	private void initialize(){
		ProductRendererBuilder prb = new ProductRendererBuilder(getContext(), this);
		adapter = new Adapter(prb, data);
		setAdapter(adapter);
		setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			private int selectedCount = 0;
			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				adapter.clearSelection();
				deselectItems();
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				selectedCount = 0;
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
				if(checked){
					selectedCount ++;
					adapter.setNewSelection(position, checked);
				}else{
					selectedCount --;
					adapter.removeSelection(position);
				}
				mode.setTitle(selectedCount +" seleccionados");
			}
			
			private void close(ActionMode mode){
				selectedCount = 0;
				adapter.clearSelection();
				deselectItems();
				mode.finish();
			}
		});
		setOnItemClickListener(this);
		setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View view, int position,long arg3) {
		itemChecked(view, position);
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		Product product = (Product) adapter.getItem(position);
		observer.onListItemClick(product.getCode());
	}
	
	@Override
	public OnClickListener getOnClickListener(int elementCode) {
		return new ImageViewClickListener(elementCode);
	}
	
	private void itemChecked(View view, int position) {
		setItemChecked(position, !adapter.isPositionChecked(position));
		if(!adapter.isPositionChecked(position))
			uncheckItem(view);
		else
			checkItem(view);
	}
	
	public void notifyChanges(ArrayList list){
		data = null;
		data = list;
		Log.e("AQUIII", "rellenado "+ data.size());
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