package com.xiao.awesome_jetpack.request;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.xiao.awesome_jetpack.data.repository.local.LocalRepository;
import com.xiao.awesome_jetpack.data.repository.local.database.Student;

import java.util.List;

/**
 *  收藏页数据请求ViewModel
 * 描述：提供访问数据层的方法，并返回LiveData，以便UI层可以建立观察者关系。
 */
public class RequestCollectViewModel extends AndroidViewModel {

    private Application application;
    private LiveData<List<Student>> studentsLiveData;//学生表所有数据

    public RequestCollectViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        studentsLiveData = LocalRepository.getInstance(application).getStudentsLiveData();
    }

    public LiveData<List<Student>> getStudentsLive() {
        return studentsLiveData;
    }

    public void touchOffInsertStudents(Student... students) {
        LocalRepository.getInstance(application).insertStudnets(students);
    }

    // 触发仓库  --  DB
    public void touchOffUpdateWords(Student... students) {
        LocalRepository.getInstance(application).updateStudnets(students);
    }

    // 触发仓库  --  DB
    public void touchOffDeleteStudents(Student... students) {
        LocalRepository.getInstance(application).deleteStudents(students);
    }

    // 触发仓库  --  DB
    public void touchOffDeleteAllWords() {
        LocalRepository.getInstance(application).deleteAllStudnets();
    }

}
