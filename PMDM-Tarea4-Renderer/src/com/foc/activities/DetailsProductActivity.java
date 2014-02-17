package com.foc.activities;

import java.io.Serializable;
import com.foc.model.ProductType;
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
	
	private ProductType product;
	private TextView name;
	private TextView price;
	private TextView description;
	private TextView category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_product);
		setupActionBar();
		
		ProductType productType = (ProductType) getIntent().getSerializableExtra("productType");
		product = productType.getStore().findProduct(productType.getProductCode());
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
		name.setText(product.getProductName());
		price.setText(String.valueOf(product.getProductPrice()));
		description.setText(product.getProductDescription());
		category.setText(product.getProductImage());
	}
	
	private void deleteProduct(){
		product.getStore().removeProduct(product.getProductCode());
		finish();
	}

	private void openModifyActivity() {
		Intent intent = new Intent(this, ModifyActivity.class);
		intent.putExtra("productType", (Serializable) product);
		startActivity(intent);
	}

}