package com.foc.activities;

import com.foc.model.Store;
import com.foc.tarea4.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class AddProductActivity extends Activity {
	
	private Store<?> store;
	private EditText editText_Name;
	private EditText editText_Price;
	private EditText editText_Desciption;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		setupActionBar();
		
		store = (Store<?>) getIntent().getSerializableExtra("store");
		
		spinner = (Spinner) findViewById(R.id.spinner_ProductCategory_input);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.product_category, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		editText_Name = (EditText) findViewById(R.id.editText_ProductName_input);
		editText_Price = (EditText) findViewById(R.id.editText_ProductPrice_input);
		editText_Desciption = (EditText) findViewById(R.id.editText_ProductDescription_input);
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
            addProduct();
            return true;
		case R.id.action_cancel:
            closeActivity();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void closeActivity() {
		try {
			finish();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void addProduct() {
		String name = editText_Name.getText().toString();
		double price = Double.parseDouble(editText_Price.getText().toString());
		String description = editText_Desciption.getText().toString();
		String icon = spinner.getSelectedItem().toString();
		Log.e("AQUIIII", "llamada al a�adir producto");
		store.addProduct(name, price, description, icon);
		closeActivity();
	}

}