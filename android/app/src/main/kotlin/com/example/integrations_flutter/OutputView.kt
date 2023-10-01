package com.example.integrations_flutter

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.platform.PlatformView


internal class OutputView(
    context: Context?,
    id: Int,
    creationParams: Map<String?, Any?>?,
    messenger: BinaryMessenger,
    data: Intent
) : PlatformView, MethodCallHandler {

    private val textView: TextView
    private val intentMessageId = "text"
    private val methodChannel: MethodChannel = MethodChannel(messenger, "CALL_METHOD")
    override fun dispose() {
        TODO("Not yet implemented")
    }

    override fun getView(): View {
        return textView
    }

    private fun setText(methodCall: MethodCall, result: MethodChannel.Result) {
        val text = methodCall.arguments as String
        textView.text = text
        result.success(null)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "setText" -> setText(call, result)
            else -> result.notImplemented()
        }
    }

    init {
        this.textView = TextView(context)
        val intent = Intent(context, MainActivity::class.java)
        var text = intent.getStringExtra(intentMessageId)
        methodChannel.setMethodCallHandler(this)
        if (text.isNullOrEmpty()) {
            text = "Is empty!"
        }
        textView.textSize = 22f
        textView.text = "$text"
    }
}