package com.shaubert.ui.jumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;

public abstract class AbstractJump<ARGS extends Args, CONFIG extends Config> implements Jump<ARGS, CONFIG> {

    private Starter starter;
    private Class<?> actClass;

    private ActivityOptionsCompat activityOptions;
    private Bundle extras;
    private ARGS args;
    private CONFIG config;

    protected AbstractJump(Starter starter, Class<?> actClass) {
        this.starter = starter;
        this.actClass = actClass;
    }

    public Class<?> getActClass() {
        return actClass;
    }

    @Override
    public Jump<ARGS, CONFIG> withExtras(Bundle extras) {
        this.extras = extras;
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withArgs(ARGS args) {
        this.args = args;
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withConfig(CONFIG config) {
        this.config = config;
        return this;
    }

    @Override
    public Jump<ARGS, CONFIG> withActivityOptions(ActivityOptionsCompat activityOptions) {
        this.activityOptions = activityOptions;
        return this;
    }


    @Override
    public void jump() {
        jumpForResult(-1);
    }

    @Override
    public void jumpForResult(int requestCode) {
        Bundle extras = createExtras();
        Intent intent = createIntent(extras);
        Bundle activityOptionsBundle = activityOptions == null ? null : activityOptions.toBundle();
        if (requestCode >= 0) {
            starter.startActivityForResult(intent, requestCode, activityOptionsBundle);
        } else {
            starter.startActivity(intent, activityOptionsBundle);
        }

        if (config != null) {
            JumpAnimations animations = config.getJumpAnimations();
            if (animations != null) {
                Context context = starter.getContext();
                if (context instanceof Activity) {
                    animations.setupForForwardTransition((Activity) context);
                }
            }
        }
    }

    public Bundle createExtras() {
        Bundle result = null;
        if (extras != null) {
            result = new Bundle();
            result.putAll(extras);
        }

        if (args != null) {
            result = args.toBundle(result);
        }

        Config resultConfig = getParentConfig();
        if (config != null) {
            if (resultConfig != null) {
                config.mergeParentConfig(resultConfig);
            }
            resultConfig = config;
        }

        if (resultConfig != null) {
            Bundle configBundle = new Bundle();
            resultConfig.toBundle(configBundle);

            if (result == null) {
                result = new Bundle();
            }
            result.putBundle(Config.CONFIG_ARGS, configBundle);
        }

        return result;
    }

    private Config getParentConfig() {
        Config currentConfig = starter.getCurrentConfig();
        if (currentConfig != null) {
            return currentConfig.createChildActivityConfig();
        }
        return null;
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