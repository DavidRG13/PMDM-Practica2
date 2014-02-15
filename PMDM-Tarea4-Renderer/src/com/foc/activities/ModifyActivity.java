package com.foc.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foc.model.ProductStore;
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
		
		Bundle bundle = getIntent().getExtras();
		int productCode = bundle.getInt("productCode");
		
		Store store = (Store) getIntent().getSerializableExtra("Store");
		product = store.findProduct(productCode);
		
		Toast.makeText(this, "codigo:"+ productCode, Toast.LENGTH_LONG).show();
		
		name_textView = (EditText) findViewById(R.id.editText_ProductName_input);
		price_textView = (EditText) findViewById(R.id.editText_ProductPrice_input);
		description_textView = (EditText) findViewById(R.id.editText_ProductDescription_input);
		
		category = (Spinner) findViewById(R.id.spinner_ProductCategory_input);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_category, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		category.setAdapter(adapter);
		
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
            closeActivity();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fillFieldsWithData() {
		name_textView.setText(product.getProduct().getName());
		price_textView.setText(String.valueOf(product.getProduct().getPrice()));
		description_textView.setText(product.getProduct().getDescription());
		String cat = product.getProduct().getImage();
		category.setSelection(ProductStore.getStore().getCategoryIndex(cat));
	}
	
	private void closeActivity() {
		try {
			finish();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void modifyProduct() {
		String name = name_textView.getText().toString();
		double price = Double.parseDouble(price_textView.getText().toString());
		String description = description_textView.getText().toString();
		String icon = category.getSelectedItem().toString();
		
		product.getProduct().setName(name);
		product.getProduct().setPrice(price);
		product.getProduct().setDescription(description);
		product.getProduct().setImage(icon);
		closeActivity();
	}

}