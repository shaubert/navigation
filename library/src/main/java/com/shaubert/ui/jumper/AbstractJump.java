package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;

public abstract class AbstractJump<ARGS extends Args, CONFIG extends Config>
        extends AbstractBlindJump<ARGS, CONFIG>
        implements Jump<ARGS, CONFIG> {

    private Starter starter;
    private Class<?> actClass;

    protected AbstractJump(Starter starter, Class<?> actClass) {
        super(starter);
        this.starter = starter;
        this.actClass = actClass;
    }

    public Class<?> getActClass() {
        return actClass;
    }

    @Override
    public Jump<ARGS, CONFIG> withExtras(Bundle extras) {
        super.withExtras(extras);
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withArgs(ARGS args) {
        super.withArgs(args);
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withConfig(CONFIG config) {
        super.withConfig(config);
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withActivityOptions(ActivityOptionsCompat activityOptions) {
        super.withActivityOptions(activityOptions);
        return this;
    }

    @Override
    public void jumpForResult(int requestCode) {
        Bundle extras = createExtras();
        Intent intent = createIntent(extras);
        jump(requestCode, intent);
    }

    @Override
    public Intent getIntent() {
        return createIntent(createExtras());
    }

    protected Intent createIntent(Bundle extras) {
        Intent intent = new Intent(starter.getContext(), actClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        setupIntent(intent);
        return intent;
    }

    protected abstract void setupIntent(Intent intent);
}