1{cycle(X,Y) : edge(X,Y)}1 :- node(X).

reach(Y):-cycle(1,Y).
reach(Y):-reach(X),cycle(X,Y),X!=1.
:-node(Y), not reach(Y).
#show cycle/2.