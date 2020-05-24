package training20.tcmobile.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.view_tag.view.*
import training20.tcmobile.R
import training20.tcmobile.Tag

class TagsView: FlexboxLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        flexWrap = FlexWrap.WRAP
        justifyContent = JustifyContent.FLEX_START
    }

    fun set(tags: Array<Tag>) {
        removeAllViews()
        tags.forEach { tag ->
            val view = LayoutInflater.from(context).inflate(R.layout.view_tag, this, false)
            view.tagTextView.setBackgroundColor(tag.backgroundColor)
            view.tagTextView.setTextColor(tag.textColor)
            view.tagTextView.text = tag.name
            addView(view)
        }
    }

}