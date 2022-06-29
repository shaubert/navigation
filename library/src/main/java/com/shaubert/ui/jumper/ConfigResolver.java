package com.shaubert.ui.jumper;

import android.content.Intent;

interface ConfigResolver {
    <T extends Config> T getConfig(Intent intent);
}
