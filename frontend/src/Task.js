import React from 'react';

function Task({ task }) {
  return (
    <div style ={{paddingLeft:'15px'}}>
      <h2>{task.name}</h2>
      <p>Hours: {task.totalHours}</p>
      <p>Deadline: {task.dueDate}</p>
    </div>
  );
}

export default Task;
