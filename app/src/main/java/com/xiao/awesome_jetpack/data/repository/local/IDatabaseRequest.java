package com.xiao.awesome_jetpack.data.repository.local;


import com.xiao.awesome_jetpack.data.repository.local.database.Student;

/**
 * 数据库获取标准接口（在仓库里面） 也就是数据库的数据读取（包括数据库数据，等）
 * 只为 LocalRepository 服务
 */
public interface IDatabaseRequest {

    void insertStudnets(Student... students);

    void updateStudnets(Student... students);

    void deleteStudents(Student... students);

    void deleteAllStudnets();

    // TODO 可扩展 ...

}
