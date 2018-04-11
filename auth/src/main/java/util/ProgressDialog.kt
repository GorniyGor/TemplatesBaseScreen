package util

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import android.view.Window
import kotlinx.android.synthetic.main.progress_dialog.*
import ru.anvics.templates.auth.R

/**
 * Created by Gor on 20.03.2018.
 */
class ProgressDialog(activity: Activity): AppCompatDialog(activity) {

    var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_dialog)
        setCancelable(false)
        if(message.isNotEmpty()) tvMessage.text = message
    }
}