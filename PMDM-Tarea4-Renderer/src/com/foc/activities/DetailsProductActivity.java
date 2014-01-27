package com.foc.activities;

import java.io.Serializable;

import com.foc.model.Product;
import com.foc.model.Store;
import com.foc.tarea4.R;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsProductActivity extends Activity {
	
	private int productCode;
	private Product product;
	private Store<?> store;
	private TextView name;
	private TextView price;
	private TextView description;
	private TextView category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_product);
		setupActionBar();
		
		Bundle bundle = getIntent().getExtras();
		productCode = bundle.getInt("productCode");
		store = (Store<?>) getIntent().getSerializableExtra("Store");
		product = store.findProduct(productCode);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		name = (TextView) findViewById(R.id.textView_ProductName_output);
		price = (TextView) findViewById(R.id.textView_ProductPrice_output);
		description = (TextView) findViewById(R.id.textView_ProductDescription_output);
		category = (TextView) findViewById(R.id.textView_ProductCategory_output);
		
		fillFieldsWithData();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.details_product, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_modify:
            openModifyActivity();
            return true;
		case R.id.action_delete:
            deleteProduct();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fillFieldsWithData() {
		name.setText(product.getName());
		price.setText(String.valueOf(product.getPrice()));
		description.setText(product.getDescription());
		category.setText(product.getImage());
	}
	
	private void deleteProduct(){
		store.removeProduct(product.getCode());
		finish();
	}

	private void openModifyActivity() {
		Intent intent = new Intent(this, ModifyActivity.class);
		intent.putExtra("productCode", product.getCode());
		intent.putExtra("store", (Serializable) store);
		startActivity(intent);
	}

}