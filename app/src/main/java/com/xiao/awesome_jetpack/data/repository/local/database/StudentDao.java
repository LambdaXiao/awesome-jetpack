package com.xiao.awesome_jetpack.data.repository.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * 描述：表数据访问对象
 */
@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student... students);

    @Update
    void updateStudent(Student... students);

    @Delete
    void deleteStudent(Student... students);

    @Query("DELETE FROM student")
    void deleteAllStudent();

    @Query("SELECT * FROM student ORDER BY ID DESC")
    public LiveData<List<Student>> getAllStudentLiveData();
}
