# missile-game
Operational description

After client connecting to server, the client will auto join to the lobby and the team that assigned by server.
(In Milestone1, each team will have two players, at this time we just have two teams and the number of the members 
of the two team are the same.) At the time server decides to start the game, clients will receive a GameView and 
a description of how to play the game will be shown in lobby room. Then the targets, geographical names such as 
countries, regions, provinces, cities, etc, will be displayed on the client's game view. Then the players in a team 
will need to chat inside the team to decide where should they click on a map location and right click that location 
to launch. (In Milestone1 player need to select a position and then click button ”launch” to send the launch packet.) 
For each target, each team can only click one time. If they click on the right position, they will get one score. 
If both team has sent the launch packet, the game view will show next target. (Also at this time will add a feature 
that if it is 60 seconds from the target being displayed, the game will go into next round even though one or both 
team have not right clicked to launch.) The game has five round, and at the last, the game view will be closed auto 
and the outcome like “team A win the game!” will be shown in the lobby and team room.
 
 
A user's manual

I. • How to start the program, both the program itself as well as any individual games or processes.
Our program includes client MVC and server MVC. To start the program, firstly, you need to set the project’s 
build path as /bin. Then to start the server, you need to start the server MVC.
To let others play the game, firstly, you need to input your username and port to start the RMI system, at this point,
 your username mush be “USER”. And then wait the clients to connect to you. When you decide to start the game, 
 you need to click button “Let them show map” to let players show the game view and then click “start game” to start the game.

As client, to play the game, you just need to input username and port to start the RMI system and then connect to the server. 
At this time you will auto join in lobby room and the team room. After that, you need do nothing than waiting. When the game 
view pops up and you can see the target in the GUI, you can play the game.

II. • How to connect to other users
For our client MVC, just input your username and port to start the RMI system, and then input your friend’s IP 
and “USER” as friend’s username to connect to others.
• For our server MVC, you can also connect to others. It is the same process as that of client MVC. But if you 
connect to others, the people you connect will not be able to play the game. You can only talk with them in the chatroom.

III. How to use every feature of the program.
• For our client MVC, you need to input your user name and the port, and then click “start” button to start RMI system. 
And after that, you can input a chat room name to create a local chatroom. Also you can input your friend’s IP and “USER”
 as your friend’s username and then click “connect” to connect to other user. If you want connect to the user 
 (includes server) that runs our code, then your friend’s user name is her true username. So that if you run multiples 
 application, you can connect one to the other without considering the problem of bound name like only the first application
 can connect to others. After connecting to others, you can click “Get room” to see the chat room of the friend you choose. 
 And you can choose one chatroom and click “request” to join that room. The system information will be shown in the info 
 panel, and all your chatrooms will be shown in the chatroom panel. And then you can click “exist and quit” to quit the system.
• For out server MVC, most of the features are same as those of the client MVC. But if you want others that run other 
code to be able to connect to you, your username should be USER. And you can click “let them show map” to let all your
 friends to show the game view. And click “start game” to start the game. Please note that only people connect to you 
 can play the game.
 
IV. How to end and exit the program.
• As a client of our game, if you want to stop the game, just close the GUI.
• As for our client MVC, you just need to click the button “Exist and Quit”, you will leave all
the chat room
• As for our server MVC, also you just need to click the button “Exist and Quit”, you will leave
all the chat room and all the game views that run on the client computer will be closed automatically.


Implementation of the API 

Initial Connection

Before joining any teams and downloading any games from other users, a client needs to connect to a remote server
by IP address. Once the connection is established, the client will fetch the lobby (IChatRoom object) from the remote
 server, add itself to the lobby and at the same time notify all the other users in the lobby. This procedure is exactly the
 same as the joining chat room process in HW08 except that it is launched automatically upon connection. Also the server 
 will send a message to the user to let him start the game view.
 
 Message Passing
 
 In our implementation, we do all the game related server-client communication through IUser, intead of IReceiver. 
 But the intra team communication will still use chatroom and IReceiver.
 
Joining a Team
Our API support many ways to join a team.
1. After a user join in a gameRoom, the server will arrange people in different groups, because user has ability to 
receive message, server will send a message directly to the user to tell him the team and the team room. In our game, we do 
automatic team assignment. We will automatically divide the players into two teams: team A and teamB.

2. Also, on the game view, the user can send message to server to require team list and then they can send message
 of ChangeTeam to the server, then the server will notify all the team members and send user list of the new team to that user.

Intra-team Message Passing
The message passing strategy is exactly the same as the one within a chat room in HW08.

Inter-team Message Passing
In our game there's no intra team passing. But the user can see the different scores changing on their screen.
That is achieved by the server sending the update score message to both teams when the score changes.

Leaving a Team
A user can leave a team by doing the same thing in HW08 and then send a message to server to tell it the update the user 
list of that team.

Leaving a Game
Just send a message to the lobby to notify all user you leave. Server will remove you from there local lobby, and server 
will remove you from a team and notify all user in that team to update the user list.

Playing a Game
During the game, all the thing will be sent as message to others, this message may tell other user to call the method of 
cmd2ModelAdapter to put/get data. Or even will tell the user who receive this message to send a message to a specific user 
or server or team by calling the method sendTo of the Cmd2ModelAdapter.
