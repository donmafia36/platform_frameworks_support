/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.webkit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.os.Build;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.WebSettings;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class WebSettingsCompatTest {
    WebViewOnUiThread mWebViewOnUiThread;

    @Before
    public void setUp() {
        mWebViewOnUiThread = new androidx.webkit.WebViewOnUiThread();
    }

    @Test
    public void testOffscreenPreRaster() {
        // TODO(gsennton) activate this test for pre-M devices when we can pre-install a WebView APK
        // containing support for the WebView Support Library, see b/73454652.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;

        assertFalse(WebSettingsCompat.getOffscreenPreRaster(mWebViewOnUiThread.getSettings()));

        WebSettingsCompat.setOffscreenPreRaster(mWebViewOnUiThread.getSettings(), true);
        assertTrue(WebSettingsCompat.getOffscreenPreRaster(mWebViewOnUiThread.getSettings()));
    }

    @Test
    public void testEnableSafeBrowsing() throws Throwable {
        // TODO(gsennton) activate this test for old devices when we can pre-install a WebView APK
        // containing support for the WebView Support Library, see b/73454652.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        WebSettingsCompat.setSafeBrowsingEnabled(mWebViewOnUiThread.getSettings(), false);
        assertFalse(WebSettingsCompat.getSafeBrowsingEnabled(mWebViewOnUiThread.getSettings()));
    }

    @Test
    public void testDisabledActionModeMenuItems() throws Throwable {
        // TODO(gsennton) activate this test for old devices when we can pre-install a WebView APK
        // containing support for the WebView Support Library, see b/73454652.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return;

        assertEquals(WebSettings.MENU_ITEM_NONE,
                WebSettingsCompat.getDisabledActionModeMenuItems(mWebViewOnUiThread.getSettings()));

        WebSettingsCompat.setDisabledActionModeMenuItems(mWebViewOnUiThread.getSettings(),
                WebSettings.MENU_ITEM_SHARE);
        assertEquals(WebSettings.MENU_ITEM_SHARE,
                WebSettingsCompat.getDisabledActionModeMenuItems(mWebViewOnUiThread.getSettings()));

        WebSettingsCompat.setDisabledActionModeMenuItems(mWebViewOnUiThread.getSettings(),
                WebSettings.MENU_ITEM_PROCESS_TEXT | WebSettings.MENU_ITEM_WEB_SEARCH);
        assertEquals(WebSettings.MENU_ITEM_PROCESS_TEXT | WebSettings.MENU_ITEM_WEB_SEARCH,
                WebSettingsCompat.getDisabledActionModeMenuItems(mWebViewOnUiThread.getSettings()));
    }
}
