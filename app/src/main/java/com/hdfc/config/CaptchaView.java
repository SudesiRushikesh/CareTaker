package com.hdfc.config;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CaptchaView extends ImageView {

	private static String mCaptchaAsText;
	private Paint mPaint;

	public CaptchaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CaptchaView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CaptchaView(Context context) {
		super(context);
		init();
	}

	private static void initCaptcha() {
		mCaptchaAsText = "";
		for (int i = 0; i < 4; i++) {
			int number = (int) (Math.random() * 10);
			mCaptchaAsText += number;
		}
	}

	public static boolean match(String value) {
		if (value.equals(mCaptchaAsText)) {
			return true;
		} else {
			if (value != null && value.length() > 0)
				initCaptcha();

			return false;
		}
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);

		initCaptcha();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int desiredWidth = 300;
		int desiredHeight = 80;

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width;
		int height;

		// Measure Width
		if (widthMode == MeasureSpec.EXACTLY) {
			// Must be this size
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			width = Math.min(desiredWidth, widthSize);
		} else {
			// Be whatever you want
			width = desiredWidth;
		}

		// Measure Height
		if (heightMode == MeasureSpec.EXACTLY) {
			// Must be this size
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			// Can't be bigger than...
			height = Math.min(desiredHeight, heightSize);
		} else {
			// Be whatever you want
			height = desiredHeight;
		}

		// MUST CALL THIS
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		mPaint.setTextSize(getMeasuredHeight());
		canvas.drawText(mCaptchaAsText,
				((canvas.getWidth() - mPaint.measureText(mCaptchaAsText)) / 2),
				getMeasuredHeight(), mPaint);
	}
}