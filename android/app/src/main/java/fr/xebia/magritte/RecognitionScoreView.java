package fr.xebia.magritte;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

import fr.xebia.magritte.Classifier.Recognition;

public class RecognitionScoreView extends View implements ResultsView {

    private static final float TEXT_SIZE_DIP = 24;
    private List<Recognition> results;
    private final Paint fgPaint;
    private final Paint bgPaint;

    public RecognitionScoreView(final Context context, final AttributeSet set) {
        super(context, set);

        float textSizePx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        fgPaint = new Paint();
        fgPaint.setTextSize(textSizePx);
        fgPaint.setColor(Color.WHITE);

        bgPaint = new Paint();
        bgPaint.setColor(ContextCompat.getColor(getContext(), R.color.scoreOverlay));
    }

    @Override
    public void setResults(final List<Recognition> results) {
        this.results = results;
        postInvalidate();
    }

    @Override
    public void onDraw(final Canvas canvas) {
        final int x = 10;
        int y = (int) (fgPaint.getTextSize() * 1.5f);

        canvas.drawPaint(bgPaint);

        if (results != null) {
            for (final Recognition recog : results) {
                canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
                y += fgPaint.getTextSize() * 1.5f;
            }
        }
    }
}
