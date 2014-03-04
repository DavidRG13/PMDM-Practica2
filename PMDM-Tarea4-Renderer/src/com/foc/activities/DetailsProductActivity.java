package com.foc.activities;

import static utilities.IntentFragmentLauncher.openActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.Store;
import com.foc.tarea4.R;

public class DetailsProductActivity extends Activity {
	
	private ProductType product;
	private int actualProductCode;
	private TextView name;
	private TextView price;
	private TextView description;
	private TextView category;
	private Store store;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_product);
		setupActionBar();
		
		ProductType productType = (ProductType) getIntent().getSerializableExtra("productType");
		store = productType.getStore(this);
		actualProductCode = productType.getProductCode();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		product = store.findProduct(actualProductCode);
		name = (TextView) findViewById(R.id.textView_ProductName_output);
		price = (TextView) findViewById(R.id.textView_ProductPrice_output);
		description = (TextView) findViewById(R.id.textView_ProductDescription_output);
		category = (TextView) findViewById(R.id.textView_ProductCategory_output);
		
		fillFieldsWithData();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			getActionBar().setDisplayHomeAsUpEnabled(true);
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
			Product p = product.getProduct();
			product.clear();
			product.setProduct(p);
            openActivity(this, ModifyActivity.class, product);
            return true;
		case R.id.action_delete:
            deleteProduct();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fillFieldsWithData() {
		name.setText(product.getProductName());
		price.setText(String.valueOf(product.getProductPrice()));
		description.setText(product.getProductDescription());
		category.setText(product.getProductImage());
	}
	
	private void deleteProduct(){
		product.getStore(this).remove(product.getProductCode());
		finish();
	}

}