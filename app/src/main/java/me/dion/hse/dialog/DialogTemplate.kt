package me.dion.hse.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class DialogTemplate(private val title: String, private val message: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok") {
                        dialog, _ -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("empty activity")
    }
}