This app is made using WorkManager in Android, implementing 
Worker class : class used that runs the particular in background thread instead of the main UI thread;
The worker generally does updation of database or loading data but in our case we have a simple scenario
we just used to Log in logcat to show that the even if the app is cleared the worker completes it task before
it's destoryed/ end's it executed.
WorkRequest: a OneTimeWorkRequest is used to call the worker only once in one lifecycle of the app, and,
Implemented constraints where the request be only made when the Constraints are satisfied using Constarints.Builder().
This requested is called by using enqueue method run by getting an instance of WorkManager.
WorkManager itself: to know about the status of our workerRequest whether it is running, enqueued, 
finished or failed, The app displays the real-time status of the worker request, ensuring seamless background processing.
Implemented by getting a instance of the workmanager and attaching  WorkInfoByLiveData from workerRequest id and observe inner class
to capture the changes the states of the request.
Additionally, I incorporated data sending and receiving between the app and the worker, enhancing overall functionality 
using setInputData, getInputData and getOutputData & Result.success() method. 

<video src="https://github.com/sanjuray/WorkManagerApp/assets/94555333/8c33d0c3-c0c4-497a-8a9c-1afdedc62a5a" height=550 width=400/>
