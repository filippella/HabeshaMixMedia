package org.dalol.habeshamixmedia.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.dalol.habeshamixmedia.presenter.BasePresenter;
import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.base.UiData;
import org.dalol.habeshamixmedia.presenter.base.UiView;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 22:01.
 */
public abstract class BaseChildFragment<P extends BasePresenter<? extends UiView, ? extends UiData>> extends BaseFragment<P> {

    public void addChildFragment(Fragment fragment, String tag) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.getChildFragmentManager().beginTransaction()
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out, R.anim.trans_right_in, R.anim.trans_right_out)
//                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragment_container, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        }
    }

    protected boolean popChildFragment() {
        //KeyboardUtils.hideNativeKeyboard(getActivity(), getView());
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            FragmentManager manager = parentFragment.getChildFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                manager.popBackStack();
                return true;
            }
        }
        return false;
    }
}
