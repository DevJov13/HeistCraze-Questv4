package dev.joven.nosdk1.libs;

import android.content.Context;

public interface IWebView {
    Context getContext();

    void loadUrl(String url);
}
