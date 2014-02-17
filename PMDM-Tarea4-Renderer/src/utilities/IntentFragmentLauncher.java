package utilities;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.foc.activities.AddProductActivity;
import com.foc.activities.DetailsProductActivity;
import com.foc.model.ProductType;

public class IntentFragmentLauncher {
	
	public static void openAddActivity(Activity context) {
		Intent myIntent = new Intent(context, AddProductActivity.class);
		context.startActivity(myIntent);
	}
	
	public static void openDetailsActivity(Activity context, int productCode) {
		Intent myIntent = new Intent(context, DetailsProductActivity.class);
		myIntent.putExtra("productCode", productCode);
		context.startActivity(myIntent);
	}
	
	public static void replaceFragment(FragmentActivity context, int idContent, Fragment fragment){
		if(fragment != null){
			FragmentManager fragmentManager = context.getSupportFragmentManager();
		    fragmentManager.beginTransaction()
		                   .replace(idContent, fragment)
		                   .commit();
		}
	}
	
	public static Fragment getVisibleFragment(FragmentActivity context){
	    FragmentManager fragmentManager = context.getSupportFragmentManager();
	    List<Fragment> fragments = fragmentManager.getFragments();
	    for(Fragment fragment : fragments)
	        if(fragment.isVisible())
	            return fragment;
	    return null;
	}
	
	public static void openActivity(Activity ctx, Class<?> cls, ProductType product){
		Intent intent = new Intent(ctx, cls);
		intent.putExtra("productType", (Serializable) product);
		ctx.startActivity(intent);
	}

}