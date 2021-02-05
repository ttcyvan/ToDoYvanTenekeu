package com.example.todoyvantenekeu.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoyvantenekeu.R
import java.util.*

class TaskListFragment : Fragment(){
    //private val taskList = listOf("Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3","Task 1", "Task 2", "Task 3")
    private val taskList = mutableListOf(
            Task(id = "id_1", title = "Task 1", description = "description 1"),
            Task(id = "id_2", title = "Task 2"),
            Task(id = "id_3", title = "Task 3")
    )
    private val adapter = TaskListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val floating_button = view.findViewById<RecyclerView>(R.id.add_task)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = TaskListAdapter(taskList)
        floating_button.setOnClickListener {
            taskList.add(Task(id = UUID.randomUUID().toString(), title = "Task ${taskList + 1}"))
            adapter.submitList(taskList.toList())
        }
    }
}

class TaskListAdapter(private val taskList: List<Task>): RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>(){
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(taskTitle: List<Task>) {
            itemView.apply {
                val tv = itemView.findViewById<TextView>(R.id.task_title)
                tv.text = taskTitle.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent,false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList)
    }

}

