%A typical family database.
male(joost).
male(sacha).
male(leon).
male(merlijn).
male(peter).
female(sofie).
female(sandrine).
female(fien).
parent(joost, sacha).
parent(joost, leon).
parent(sandrine, sacha).
parent(sandrine, leon).
parent(fien, sofie).
parent(fien, merlijn).
parent(peter, fien).
parent(peter, joost).

isChild(X):-parent(Y,X).
brother(X, Y):-male(X), parent(Z, X), parent(Z, Y), \+ X = Y.
sister(X, Y):-female(X), parent(Z, X), parent(Z, Y), \+ X = Y.

cousin(X, Y):- (parent(Z, X), parent(W, Y), brother(Z, W)); (parent(Z, X), parent(W, Y), sister(Z, W)).

ancestor(X, Y):- parent(X, Y).
ancestor(X, Y):- parent(X, Z),
		ancestor(Z, Y).

family(X, Y):- (ancestor(Z, X), ancestor(Z, Y)); ancestor(X, Y); ancestor(Y, X).
