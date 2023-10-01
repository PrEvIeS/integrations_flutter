package com.example.integrations_flutter

import android.content.Intent
import android.widget.TextView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    private val androidViewId = "INTEGRATION_ANDROID"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        flutterEngine.platformViewsController.registry.registerViewFactory(
            androidViewId,
            TextFactory(flutterEngine.dartExecutor.binaryMessenger, Intent())
        )
    }
}
