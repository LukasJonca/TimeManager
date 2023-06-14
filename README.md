# TimeManager
Time manager consist of a frontend and backend built using Java Spring Boot and React.

## Demo
[![Watch the video]]([https://youtu.be/vt5fpE0bzSY](https://youtu.be/3xHxKqgTnB8))


## Backend
The backend of the Time Manager application is built using Spring Boot. The backend receives HTTP POST requests from the frontend, with the a JSON object containing tasks and days.

The received data tasks contain properties like total hours and due date, and days contain properties like date, total hours, busy hours, and type. The tasks are scheduled across the days based on available hours.


### API Using JSON input for post request and output with Postman
All tasks scheduled:

![image](https://github.com/LukasJonca/TimeManager/assets/78755884/08bbc9cf-219e-4672-9ba1-6a011d104807)

All tasks not scheduled:

![image](https://github.com/LukasJonca/TimeManager/assets/78755884/21d44f1f-e89d-410b-ba2e-ce546d1bb4af)

## Frontend

The UI consists of forms for adding tasks and days displaying added tasks and days followed by a button to send the current tasks and days to the backend. Once a response is recieved from the backend the schedule will be displayed with an additional prompt to indicate whether all tasks have been scheduled or not.

![image](https://github.com/LukasJonca/TimeManager/assets/78755884/64666b50-5efe-4424-a97a-9ad2a237ed40)


