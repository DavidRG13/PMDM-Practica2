import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricShadowOfLevel16;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.foc.activities.AddProductActivity;
import com.foc.activities.MainActivity;
import com.foc.model.Product;
import com.foc.model.ProductStore;
import com.foc.model.Store;
import com.foc.tarea4.R;

@RunWith(RobolectricTestRunner.class)
public class StoreTest {
	
	private Store store;
	
	@Before
	public void setUp(){
		store = ProductStore.getStore();
	}

	@Test
	public void add_the_fith_product(){
		store.addProduct("producto 5", 12.3, "descripcion 5", "food");
		Product expected = new Product(5,"producto 5", 12.3, "descripcion 5", "food");
		ArrayList<Product> lista = store.getList();
		Product inserted = lista.get(lista.size() - 1);
		
		assertEquals(expected.getCode(), inserted.getCode());
	}
	
	@Test
	public void check_insert(){
		int previous = ProductStore.getStore().getList().size();
		
		ProductStore.getStore().addProduct("productoTest", 12.3, "descripcionTest", "comida");
		
		int actual = ProductStore.getStore().getList().size();
		
		assertEquals(previous, actual - 1);
	}
	
	@Test
	public void mainActivity_shown(){
		Activity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
		assertNotNull(mainActivity);
	}
	
	@Test
	public void addProduct_on_addProductActivity(){
		Intent intent = new Intent(Robolectric.getShadowApplication().getApplicationContext(), AddProductActivity.class);
		intent.putExtra("store", (Serializable) store);
		
		Activity addProductActivity = Robolectric.buildActivity(AddProductActivity.class)
		    .withIntent(intent)
		    .create(null)
		    .start()
		    .resume()
		    .visible()
		    .get();
		
		Spinner spinner = (Spinner) addProductActivity.findViewById(R.id.spinner_ProductCategory_input);
		
		EditText editText_Name = (EditText) addProductActivity.findViewById(R.id.editText_ProductName_input);
		EditText editText_Price = (EditText) addProductActivity.findViewById(R.id.editText_ProductPrice_input);
		EditText editText_Desciption = (EditText) addProductActivity.findViewById(R.id.editText_ProductDescription_input);
		
		editText_Name.setText("ProductoTest");
		editText_Price.setText(String.valueOf("12.65"));
		editText_Desciption.setText("descripocion test");
		spinner.setSelection(0); 
		
		int previous = store.getList().size();
		
		MenuItem addMenuItem = (MenuItem) addProductActivity.findViewById(R.id.action_add);
		addProductActivity.onOptionsItemSelected(addMenuItem);
		
		int actual = store.getList().size();
		
		assertEquals(previous, actual);
	}

}