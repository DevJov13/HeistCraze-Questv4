package dev.joven.nosdk1.js;

import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.Map;

import dev.joven.nosdk1.libs.BridgeWebView;
import dev.joven.nosdk1.libs.OnBridgeCallback;

public class AppJSInterface extends BridgeWebView.BaseJavascriptInterface {
    private BridgeWebView mWebView;

    public AppJSInterface(Map<String, OnBridgeCallback> callbacks, BridgeWebView webView) {
        super(callbacks);
        mWebView = webView;
    }

    public AppJSInterface(Map<String, OnBridgeCallback> callbacks) {
        super(callbacks);
    }

    @Override
    public String send(String data) {
        return "it is default response";
    }

    @JavascriptInterface
    public void submitFromWeb(String data, String callbackId) {
        Log.d("AppJSInterface", data + ", callbackId: " + callbackId + " " + Thread.currentThread().getName());
        mWebView.sendResponse("submitFromWeb response", callbackId);
    }
}
