import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Task from './Task';
import TaskForm from './TaskForm';
import DayForm from './DayForm';

function App() {
  const [tasks, setTasks] = useState([]);

  /* useEffect(() => {
    axios.get('http://localhost:8080/api/tasks')
      .then(response => {
        setTasks(response.data);
      });
  }, []); */

  return (
    <div>
    
      <TaskForm tasks={tasks} setTasks={setTasks} />
      <DayForm />
      {tasks.map((task, index) => <Task key={index} task={task} />)}
     
    </div>
  );
}

export default App;
