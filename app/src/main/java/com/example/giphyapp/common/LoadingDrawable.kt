package com.example.giphyapp.common

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable

class LoadingDrawable : Drawable(), ValueAnimator.AnimatorUpdateListener {

    private val paint = Paint().apply {
        color = Color.MAGENTA
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val animator: ValueAnimator = ValueAnimator.ofFloat(20.0f, 60f)
    private var currentSize = 50f

    init {
        animator.addUpdateListener(this)
        animator.duration = 500
        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.start()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(bounds.width() / 2f, bounds.height() / 2f, currentSize, paint)
    }

    override fun setAlpha(p0: Int) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setColorFilter(p0: ColorFilter?) {
        paint.colorFilter = p0
    }

    override fun onAnimationUpdate(p0: ValueAnimator?) {
        currentSize = p0?.animatedValue as Float
        invalidateSelf()
    }
}
