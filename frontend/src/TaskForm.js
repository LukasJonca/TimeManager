import React, { useState } from 'react';

function TaskForm({ tasks, setTasks }) {
  const [name, setName] = useState('');
  const [totalHours, setTotalHours] = useState('');
  const [dueDate, setDueDate] = useState('');
  

  const handleSubmit = (event) => {
    event.preventDefault();
    
    const task = {
      name,
      totalHours: Number(totalHours),
      dueDate,
    };

    setTasks([...tasks, task]);

    // Here you can call your API to add a new task
    //console.log(task);

    setName('');
    setTotalHours('');
    setDueDate('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Task Name:
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
      </label>

      <label>
        Duration (in hours):
        <input type="number" value={totalHours} onChange={(e) => setTotalHours(e.target.value)} />
      </label>

      <label>
        Due Date:
        <input type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} />
      </label>

      <button type="submit">Add Task</button>
    </form>
  );
}

export default TaskForm;
