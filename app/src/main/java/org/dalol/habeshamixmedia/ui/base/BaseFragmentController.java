package org.dalol.habeshamixmedia.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.dalol.habeshamixmedia.common.callback.OnBackListener;
import org.dalol.habeshamixmedia.R;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:05.
 */
public abstract class BaseFragmentController extends Fragment implements OnBackListener {

    private Fragment mCurrentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null && mCurrentFragment == null) {
            mCurrentFragment = createFragment();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_controller, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getChildFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_container, mCurrentFragment)
                .commit();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isResumed()) {
            FragmentManager manager = getChildFragmentManager();
            List<Fragment> fragments = manager.getFragments();
            if (fragments != null) {
                for (Fragment fragment : fragments) {
                    fragment.setUserVisibleHint(isVisibleToUser);
                }
            }
        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        mCurrentFragment = childFragment;
    }

    @Override
    public boolean onBack() {
        if (isAdded()) {
            //KeyboardUtils.hideNativeKeyboard(getActivity(), getView());
            FragmentManager manager = getChildFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                manager.popBackStack();
                return true;
            }
        }
        return false;
    }

    protected abstract Fragment createFragment();
}
