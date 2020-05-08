package com.xiao.awesome_jetpack.ui.collect;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiao.awesome_jetpack.R;
import com.xiao.awesome_jetpack.base.BaseFragment;
import com.xiao.awesome_jetpack.common.adapter.BaseRecyclerAdapter;
import com.xiao.awesome_jetpack.common.adapter.RecyclerViewHolder;
import com.xiao.awesome_jetpack.data.repository.local.database.Student;
import com.xiao.awesome_jetpack.databinding.CollectFragmentBinding;
import com.xiao.awesome_jetpack.request.RequestCollectViewModel;
import com.xiao.awesome_jetpack.ui.collect.viewmodel.CollectViewModel;

import java.util.ArrayList;
import java.util.List;

public class CollectFragment extends BaseFragment {

    private CollectViewModel mViewModel;
    private CollectFragmentBinding binding;
    private RequestCollectViewModel requestCollectViewModel; // 有请求的  ViewModel 仓库
    private BaseRecyclerAdapter<Student> adapter;
    private List<Student> students;
    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        View root =  inflater.inflate(R.layout.collect_fragment, container, false);
        binding = CollectFragmentBinding.bind(root);
        binding.setVm(mViewModel);
        binding.setClick(new ProxyClick());
        binding.setLifecycleOwner(this);

        students = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<Student>(mActivity,students) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_collect_list;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Student student) {
                holder.getTextView(R.id.tv_id).setText(String.valueOf(position + 1));
                holder.getTextView(R.id.tv_name).setText(student.getName());
                holder.getTextView(R.id.tv_age).setText(String.valueOf(student.getAge()));
            }

        };
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        requestCollectViewModel = new ViewModelProvider(this).get(RequestCollectViewModel.class);
        requestCollectViewModel.getStudentsLive().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                adapter.setList(students);
            }
        });
        return root;
    }


    public class ProxyClick {

        public void installDataAction() {
            String[] names = {
                    "乔峰",
                    "段誉",
                    "虚竹",
                    "慕容复",
                    "张三",
                    "李四",
                    "王五",
                    "赵六",
                    "初七",
                    "杜子腾",
                    "王小二",
                    "李大奇"
            };
            int[] ages = {
                    43,
                    24,
                    19,
                    83,
                    64,
                    21,
                    56,
                    32,
                    17,
                    32,
                    45,
                    14
            };
            for(int i = 0;i< names.length;i++) {
                // 触发一次，数据的刷新，改变，全部 采用 数据驱动起来改变
                requestCollectViewModel.touchOffInsertStudents(new Student(names[i], ages[i]));
            }
        }

        public void clearAllDataAction() {
            requestCollectViewModel.touchOffDeleteAllWords();
        }
    }
}
