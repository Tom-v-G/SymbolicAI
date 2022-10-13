% Assignment 2 
%
%
%

:-retractall(robot(_)).
:-dynamic robot/1.

%The location of the robot and goal
robot(1).
goal(5).

%The first link as a predicate
link(1,2).
link(2,3).
link(3,4).
link(3,6).
link(6,5).
link(6,7).


