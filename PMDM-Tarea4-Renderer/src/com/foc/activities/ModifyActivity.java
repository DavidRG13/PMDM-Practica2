package com.foc.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.Store;
import com.foc.tarea4.R;

public class ModifyActivity extends Activity {
	
	private ProductType product;
	private EditText name_textView;
	private EditText price_textView;
	private EditText description_textView;
	private Spinner category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		setupActionBar();
		
		ProductType productType = (ProductType) getIntent().getSerializableExtra("productType");
		product = productType.getStore().findProduct(productType.getProductCode());
		
		name_textView = (EditText) findViewById(R.id.editText_ProductName_input);
		price_textView = (EditText) findViewById(R.id.editText_ProductPrice_input);
		description_textView = (EditText) findViewById(R.id.editText_ProductDescription_input);
		
		category = (Spinner) findViewById(R.id.spinner_ProductCategory_input);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_category, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(adapter);
		
		fillFieldsWithData();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_product, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_add:
            modifyProduct();
            return true;
		case R.id.action_cancel:
            finish();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fillFieldsWithData() {
		name_textView.setText(product.getProductName());
		price_textView.setText(String.valueOf(product.getProductPrice()));
		description_textView.setText(product.getProductDescription());
		String cat = product.getProductImage();
		category.setSelection(product.getStore().getCategoryIndex(cat));
	}
	
	private void modifyProduct() {
		String name = name_textView.getText().toString();
		double price = Double.parseDouble(price_textView.getText().toString());
		String description = description_textView.getText().toString();
		String icon = category.getSelectedItem().toString();
		Log.d("modifi", name);
		
		product.setProduct(new Product(product.getProductCode(), name, price, description, icon));
		Store store = product.getStore();
		store.updateProduct(product);
		finish();
	}

}