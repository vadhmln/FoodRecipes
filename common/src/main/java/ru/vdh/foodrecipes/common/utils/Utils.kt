package ru.vdh.foodrecipes.common.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import coil.load
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import org.jsoup.Jsoup
import ru.vdh.cleanarch.common.R

fun hideKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl) {
        crossfade(600)
        error(ru.vdh.cleanarch.core.ui.R.drawable.ic_error_placeholder)
    }
}

fun applyVeganColor(view: View, vegan: Boolean) {
    if (vegan) {
        when (view) {
            is TextView -> {
                view.setTextColor(
                    ContextCompat.getColor(
                        view.context,
                        ru.vdh.cleanarch.core.ui.R.color.green
                    )
                )
            }

            is ImageView -> {
                view.setColorFilter(
                    ContextCompat.getColor(
                        view.context,
                        ru.vdh.cleanarch.core.ui.R.color.green
                    )
                )
            }
        }
    }
}

fun parseHtml(textView: TextView, description: String?) {
    if (description != null) {
        val desc = Jsoup.parse(description).text()
        textView.text = desc
    }
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

@OptIn(FlowPreview::class)
suspend fun <T> Flow<List<T>>.flattenToList() =
    flatMapConcat { it.asFlow() }.toList()
