package com.shaubert.ui.jumper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Use as a template for your root activity
 */
public class BaseActivity extends ActionBarActivity {

    private boolean suppressUpNavigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onNewIntent(getIntent());
        setupActionBar();
    }

    public void suppressUpNavigation() {
        this.suppressUpNavigation = true;
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        if (intent.hasExtra(Extras.CLEAN_UP_TASK_AND_FINISH_EXTRA)) {
            finish();
        }
        if (intent.hasExtra(Extras.OPEN_INTENT_EXTRA)) {
            Intent openIntent = intent.getParcelableExtra(Extras.OPEN_INTENT_EXTRA);
            if (openIntent != null) {
                startActivity(openIntent);
            }
        }
    }

    @Override
    public void setSupportActionBar(Toolbar toolbar) {
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null
                && !suppressUpNavigation
                && !isSuppressUpNavigationInIntent()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                boolean superResult = !isUseUpAsFinish() && super.onOptionsItemSelected(item);
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

    protected boolean isUseUpAsFinish() {
        return getIntent() != null && getIntent().getBooleanExtra(Extras.UP_AS_FINISH_NAVIGATION_EXTRA, false);
    }

    protected boolean isSuppressUpNavigationInIntent() {
        return getIntent() != null && getIntent().getBooleanExtra(Extras.SUPPRESS_UP_NAVIGATION_EXTRA, false);
    }

    @Override
    public void startActivityFromChild(Activity child, Intent intent, int requestCode) {
        handleActivityStart(intent, requestCode);
        super.startActivityFromChild(child, intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        handleActivityStart(intent, requestCode);
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, Bundle options) {
        handleActivityStart(intent, requestCode);
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    @Override
    public void startActivityFromFragment(android.support.v4.app.Fragment fragment, Intent intent, int requestCode) {
        handleActivityStart(intent, requestCode);
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        handleActivityStart(intent, requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    private void handleActivityStart(Intent intent, int requestCode) {
        if (isUseUpAsFinish()) {
            intent.putExtra(Extras.UP_AS_FINISH_NAVIGATION_EXTRA, true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing()) {
            JumpAnimations animations = Extras.getBackwardAnimations(this);
            if (animations != null) {
                animations.setupForBackwardTransition(this);
            }
        }
    }

}