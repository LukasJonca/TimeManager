import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Task from './Task';
import TaskForm from './TaskForm';
import DayForm from './DayForm';
import Day from './Day';


function App() {
  const [tasks, setTasks] = useState([]);
  const [days, setDays] = useState([]);
  const [isTaskFullyScheduled, setIsTaskFullyScheduled] = useState(false);
  const [responseData, setResponseData] = useState(null);

  /* useEffect(() => {
    axios.get('http://localhost:8080/api/tasks')
      .then(response => {
        setTasks(response.data);
      });
  }, []); */

  const handleSend = async () => {
    const url = 'http://localhost:8080/api/schedule'; // Replace with your API endpoint
    const data = {
        tasks: tasks,
        days: days
    };
    console.log(JSON.stringify(data));
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    const response = await fetch(url, options);

    if (response.ok) {
        const jsonResponse = await response.json();
        setIsTaskFullyScheduled(false);
        setResponseData(null);
        setResponseData(jsonResponse);
        setIsTaskFullyScheduled(jsonResponse.isTaskFullyScheduled);
        console.log(jsonResponse);
    } else {
        console.error('Failed to send data');
    }
  };

  return (
    <div style ={{padding:'15px'}}>
    
      <TaskForm tasks={tasks} setTasks={setTasks} />
      <DayForm days = {days} setDays = {setDays} />

      <h2>Tasks</h2>
      {tasks.map((task, index) => <Task key={index} task={task} />)}

      <h2>Days</h2>
      <div style={{display: 'flex', flexDirection: 'row'}}>
        {days.map((day, index) => <Day key={index} day={day} />)}
      </div>
      
      <button onClick={handleSend}>Send</button>

      <h2>Response</h2>
      <div style={{display: 'flex', flexDirection: 'row'}}>
        {responseData && responseData.days.map((day, index) => <Day key={index} day={day} />)}
      </div>
      
      <p style={{ color: isTaskFullyScheduled ? 'green' : 'red' }}>
        {isTaskFullyScheduled ? 'Task(s) Completed' : 'Task(s) Not Completed'}
      </p>
    </div>
  );
}

export default App;
