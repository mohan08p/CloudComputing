**Cloud Computing simple mini projects**

1. A sftp server using Python
A SFTP server is created using **pysftp** python library.
Install it using
`pip install pysftp`
I have used a public server of Digital Ocean and created a 
sftp server.


2. A Java scoket program which saves a file on a server as soon 
as we save it on the client side
The entire program in in the FileChange folder
# Project description:
It creates a scoket connetion to a server. On the client side , when 
you open the file in the Test dirctory, presently the program points
to the file a.txt in the Test drcetory, you can change this 
in thne program. As soon as you make the change in the file and
click on save , it gets saved on the server.
However, there is a small bug, this works only for the first time.
If you change the file again and save, it doesn't work. You are
welcome to send a pull request and make changes.

Steps:
1. First run the server java program.
2. Run the Client java program
3. Open a.txt in Test directory and make some change and save the file
4. The file gets saved on the server



