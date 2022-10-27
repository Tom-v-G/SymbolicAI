% Assignment 2 
%
%
%

:-retractall(robot(_)).
:-dynamic robot/1.

%The location of the robot and goal
robot(1).
goal(5).

%The links as a predicate
%link(3, 1).
link(1, 2).
link(2, 3).
link(3, 4).
link(3, 6).
link(6, 5).
link(6, 7).

%Robot functions as predicates
adjacent(L):- robot(X), link(X, L).

move(L):- adjacent(L), retractall(robot(_)), assertz(robot(L)).

% originele suggest voor 3.13
%suggest(L):- goal(L), adjacent(L).

append([], X, X).
append([X|Y], Z, [X|W]):- append(Y, Z, W).


pathlink(X, Y, P):- link(X, Y), P = [Y].
pathlink(X, Y, P):- link(X, Z), P = [Z|Q], pathlink(Z, Y, Q).

path:- pathlink(X, Y, _), robot(X), goal(Y).

path(P):- pathlink(X, Y, P), robot(X), goal(Y).

suggest(L):- path(P), P=[L|_].
