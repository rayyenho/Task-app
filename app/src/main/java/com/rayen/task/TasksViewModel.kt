package com.rayen.task

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TasksViewModel(val dao:TaskDao):ViewModel() {
    var newTaskName=""
    val tasks = dao.getAll()
    private val _naviagateToTask = MutableLiveData<Long?>()
    val navigateToTask:LiveData<Long?> get() = _naviagateToTask

    fun addTask(){
        viewModelScope.launch{ val task = Task()
        task.taskName = newTaskName
        dao.insert(task)
    }}
    fun onTaskClicked(taskId: Long){
        _naviagateToTask.value = taskId
    }
    fun onTaskNavigated(){
        _naviagateToTask.value = null
    }

}