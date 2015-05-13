package com.nucleus.activities;




import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint({ "HandlerLeak", "HandlerLeak" })
public class CustomProgressBar extends View {

	private static final double PI = Math.PI;

	// This mode user can't see the loading progress.
	public static final int SPIN_MODE = 1;

	// This mode user can know the loading progress.
	public static final int SWEEP_MODE = 2;

	/* Default data for the spinner progress bar. */

	private int mBarStrokeWidth = 6;

	private int mCircleStrokeWidth = mBarStrokeWidth;

	private float mBarLength = 0.0f;

	private int mBarLengthRatio = 8;

	// In the sweep mode or horizontal style progress bar. all progress will
	// start at 0.
	private int mProgress = 0;



	public void setProgress(int progress) {
		this.mProgress = progress;
	}

}
