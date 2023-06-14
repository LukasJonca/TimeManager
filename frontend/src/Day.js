import React from 'react';

function Day({ day }) {
  return (
    <div style ={{paddingLeft:'15px'}}>
      <h2>{day.type}</h2>
      <p>Date: {day.date}</p>
      <p>Total Hours: {day.totalHours}</p>
      <p>Busy Hours: {day.busyHours}</p>
    </div>
  );
}

export default Day;
