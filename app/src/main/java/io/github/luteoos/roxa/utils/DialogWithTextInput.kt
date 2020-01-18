package io.github.luteoos.roxa.utils

import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import io.github.luteoos.roxa.R
import kotlinx.android.synthetic.main.dialog_with_edit_text.view.*

class DialogWithTextInput(private val context: Context) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.DialogActivity))
    private var title: String? = null
    private var message: String? = null

    private var hideEditText : Boolean = false
    private var editTextContent: String? = null
    private var view: View? = null


    fun getEditTextContent(): String {
        return if (view != null) {
            view?.editText?.text.toString()
        } else
            ""
    }

    fun show(): AlertDialog {
        if (title.isNullOrEmpty() || message.isNullOrEmpty()) {
        }
        view = getView().apply {
            title.text = this@DialogWithTextInput.title
            message.text = this@DialogWithTextInput.message
            editText.setText(editTextContent, TextView.BufferType.EDITABLE)
            editText.visibility = if(hideEditText) View.GONE else View.VISIBLE
        }
        builder.setView(view)
        return builder.show()
    }

    fun setPositiveButton(@StringRes text: Int, listener: (DialogInterface, Int) -> Unit) {
        builder.setPositiveButton(text, listener)
    }

    fun setNegativeButton(@StringRes text: Int, listener: (DialogInterface, Int) -> Unit) {
        builder.setNegativeButton(text, listener)
    }

    fun hideEditText(hide: Boolean){
        hideEditText = hide
    }

    fun setCancelable(cancellable: Boolean) {
        builder.setCancelable(cancellable)
    }

    fun setTitle(@StringRes titleId: Int) {
        title = getString(titleId)
    }

    fun setMessage(@StringRes messageId: Int) {
        message = getString(messageId)
    }

    private fun getView(): View {
        return context.layoutInflater.inflate(R.layout.dialog_with_edit_text, null)
    }

    private fun getString(stringId: Int): String {
        return context.getString(stringId)
    }
}