package com.xiao.awesome_jetpack.ui.personal;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.base.BaseFragment;
import com.xiao.awesome_jetpack.databinding.PersonalFragmentBinding;
import com.xiao.awesome_jetpack.ui.personal.viewmodel.PersonalViewModel;

public class PersonalFragment extends BaseFragment {

    private PersonalViewModel mViewModel;
    PersonalFragmentBinding mBinding;

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.personal_fragment, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        // TODO: Use the ViewModel
        mBinding.setVm(mViewModel);
        mBinding.setLifecycleOwner(getViewLifecycleOwner());
        mViewModel.getText().setValue("Test");
    }

}
