import React, { useState } from 'react';

function DayForm({ days, setDays}) {
  const [date, setDate] = useState('');
  const [totalHours, setTotalHours] = useState('');
  const [busyHours, setBusyHours] = useState('');
  const [type, setType] = useState('WORKDAY');
  

  const handleSubmit = (event) => {
    event.preventDefault();
    
    const day = {
      date,
      totalHours: Number(totalHours),
      busyHours: Number(busyHours),
      type,
    };

    setDays([...days, day]);


  };

  
  return (
    <form onSubmit={handleSubmit}>
      <label>
        Date:
        <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
      </label>

      <label>
        Total Hours (in hours):
        <input type="number" value={totalHours} onChange={(e) => setTotalHours(e.target.value)} />
      </label>

      <label>
        Busy Hours (in hours):
        <input type="number" value={busyHours} onChange={(e) => setBusyHours(e.target.value)} />
      </label>

      <label>
        Type:
        <select value={type} onChange={(e) => setType(e.target.value)}>
            <option value="WORKDAY">Workday</option>
            <option value="WEEKEND">Weekend</option>
            <option value="HOLIDAY">Holiday</option>
        </select>
      </label>


      <button type="submit">Add Day</button>
    </form>
  );
}


export default DayForm;
