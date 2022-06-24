package com.rayen.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId:Long, val dao:TaskDao):ViewModel() {
    val task = dao.get(taskId)
    private val _navigatieToList = MutableLiveData<Boolean>(false)
    val navigateToList : LiveData<Boolean> get() = _navigatieToList

    fun updateTask(){
        viewModelScope.launch{
            dao.update(task.value!!)
            _navigatieToList.value=true
        }
    }
    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigatieToList.value = true
        }
    }
    fun onNavigateToList(){
        _navigatieToList.value = false
    }
}