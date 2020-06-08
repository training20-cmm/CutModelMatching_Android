package training20.tcmobile.mvvm.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class BottomNavigationViewPager: ViewPager {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

//    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
//        return false
//    }
//
//    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        return false
//    }

}