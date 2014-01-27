package com.foc.RendererPattern;

import android.view.View.OnClickListener;

public interface OnClickListenerProvider {
	
	public OnClickListener getOnClickListener(int elementCode);

}
