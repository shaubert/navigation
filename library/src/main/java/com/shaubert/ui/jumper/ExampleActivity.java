package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Use as a template for your root activity
 */
class ExampleActivity extends AppCompatActivity {

    private Jumper jumper;
    private Config config = new Config();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getJumper().dispatchOnCreate(savedInstanceState, null);
        setupConfig(getIntent());

        onNewIntent(getIntent());
    }

    private void setupConfig(Intent intent) {
        Jumper jumper = getJumper();
        config = jumper.getConfig(intent);
        if (config == null) {
            config = new Config();
        }
    }

    public Jumper getJumper() {
        if (jumper == null) {
            //Create jumper;
        }
        return jumper;
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getJumper().handleIntent(intent);
        setupConfig(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getJumper().dispatchOnPause(isFinishing());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getJumper().dispatchOnResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getJumper().dispatchOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getJumper().dispatchOnSaveInstanceState(outState);
    }

}