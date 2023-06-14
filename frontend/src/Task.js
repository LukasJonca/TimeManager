import React from 'react';

function Task({ task }) {
  return (
    <div>
      <h2>TEST</h2>
      <p>Hours: {task.duration}</p>
      <p>Deadline: {task.dueDate}</p>
    </div>
  );
}

export default Task;
