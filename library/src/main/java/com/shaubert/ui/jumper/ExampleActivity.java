package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Use as a template for your root activity
 */
public class ExampleActivity extends ActionBarActivity {

    private Jumper jumper;
    private Config config = new Config();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getJumper().dispatchOnCreate(savedInstanceState);
        setupConfig(getIntent());

        onNewIntent(getIntent());
        setupActionBar();
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
    public void setSupportActionBar(Toolbar toolbar) {
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !config.isRemoveUpFromActionBar()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                boolean superResult = !config.isUseUpInActionBarAsFinish() && super.onOptionsItemSelected(item);
                if (!superResult && isShowHomeAsUp()) {
                    finish();
                }
                return superResult;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isShowHomeAsUp() {
        ActionBar actionBar = getSupportActionBar();
        return actionBar != null && (actionBar.getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) != 0;
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