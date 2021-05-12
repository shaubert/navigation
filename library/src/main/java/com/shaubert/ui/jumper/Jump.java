package com.shaubert.ui.jumper;

import android.content.Intent;

public interface Jump<ARGS extends Args, CONFIG extends Config> extends BlindJump<ARGS, CONFIG> {

    Intent getIntent();

}