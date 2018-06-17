package org.dalol.habeshamixmedia.ui.features.top;

import android.os.Bundle;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 29/04/2018 at 17:16.
 */
public class TopMusicFragment extends BaseChildFragment {

    public static TopMusicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TopMusicFragment fragment = new TopMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.common_simple_list;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
    }
}
