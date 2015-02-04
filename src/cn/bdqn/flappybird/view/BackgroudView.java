package cn.bdqn.flappybird.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import cn.bdqn.flappybird.R;

public class BackgroudView extends View {

	public BackgroudView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private Bitmap srcBackground = null;
	private int offsetX = 0;
	private boolean isStart = true;
	
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			if(Math.abs(offsetX) >= srcBackground.getWidth()){
				offsetX += srcBackground.getWidth();
			}
			offsetX -= 5;
			postInvalidate();
			handler.postDelayed(runnable, 50);
		}
	};
	
	public void startScroll(){
		if(isStart){
			handler.post(runnable);
			isStart = false;
		}else{
			handler.removeCallbacks(runnable);
			isStart = true;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(srcBackground == null){
			srcBackground = BitmapFactory.decodeResource(getResources(), R.raw.background_block, null);
			float height = (float)getMeasuredHeight();
			
			float zoom = height / srcBackground.getHeight();
			Matrix matrix = new Matrix(); 
			matrix.postScale(zoom,zoom);
			srcBackground = Bitmap.createBitmap(srcBackground, 0, 0,
					srcBackground.getWidth(), srcBackground.getHeight(), matrix, true);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Rect srcRect = new Rect(0, 0 , srcBackground.getWidth(), srcBackground.getHeight());
		
		int width = getMeasuredWidth();
		int count = (int)Math.ceil((double)width / srcRect.width());
		for(int i = 0; i <= count; i++){
			Rect dstRect = new Rect(srcRect);
			int picWidth = srcRect.width();
			dstRect.offset((offsetX + (picWidth * i)), 0);
			canvas.drawBitmap(srcBackground, srcRect, dstRect, null);
		}	
	}	
}
